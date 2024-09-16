package com.smart.card.sys.system.controllerlogin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.card.sys.common.annotation.Limit;
import com.smart.card.sys.common.authentication.JWTToken;
import com.smart.card.sys.common.authentication.JWTUtil;
import com.smart.card.sys.common.domain.ActiveUser;
import com.smart.card.sys.common.domain.FebsConst;
import com.smart.card.sys.common.properties.FebsProperties;
import com.smart.card.sys.common.service.RedisService;
import com.smart.card.sys.common.utils.*;
import com.smart.card.sys.system.dao.DeptMapper;
import com.smart.card.sys.system.dao.LoginLogMapper;
import com.smart.card.sys.system.domain.po.Dept;
import com.smart.card.sys.system.domain.po.LoginLog;
import com.smart.card.sys.system.domain.po.User;
import com.smart.card.sys.system.domain.po.UserConfig;
import com.smart.card.sys.system.manager.UserManager;
import com.smart.card.sys.system.service.LoginLogService;
import com.smart.card.sys.system.service.UserService;
import com.smart.common.core.domain.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Validated
@RestController
public class LoginController {
    private Integer error_code = 10001;
    private String message;
    @Resource
    private RedisService redisService;
    @Resource
    private UserManager userManager;
    @Resource
    private UserService userService;
    @Resource
    private LoginLogService loginLogService;
    @Resource
    private LoginLogMapper loginLogMapper;
    @Resource
    private FebsProperties properties;
    @Resource
    private ObjectMapper mapper;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private DeptMapper deptMapper;

    /**
     * 时间间隔（分钟）
     */
    private static final int TIME_EXPIRE = 30;
    /**
     * 登录失败重试次数上限
     */
    private static final int FAILED_RETRY_TIMES = 5;
    /**
     * redis记录用户登录失败次数key
     */
    private static final String USER_LOGIN_FAILED_COUNT = "USER:LOGIN:FAILED:COUNT:";


    @PostMapping("/login")
    @Limit(key = "login", period = 60, count = 20, name = "登录接口", prefix = "limit")
    public Result login(
            @NotBlank(message = "{required}") String username,
            @NotBlank(message = "{required}") String password,String verifycode, HttpServletRequest request) throws Exception {
        username = StringUtils.lowerCase(username);
        password = MD5Util.encrypt(username, password);

        String key_failed = USER_LOGIN_FAILED_COUNT + username;
        RedisAtomicInteger counter = getRedisCounter(key_failed);
        if (counter.get() >= FAILED_RETRY_TIMES) {
            message = "登录失败次数已达上限，请稍后再试。";
            return Result.FAIL(message);
        }
        final String errorMessage = "用户名或密码错误";
        String errorCodeMsg = "验证码错误，或已失效";
        User user = this.userManager.getUser(username);
        String key = "";
        if (StringUtils.isBlank(verifycode) ){
            message = "验证码不能为空";
            return Result.FAIL(message);
        }else{
            key = "code_"+verifycode.toUpperCase();
            String code = (String)redisTemplate.opsForValue().get(key);
            if (StringUtils.isBlank(code) ){
                return Result.FAIL(errorCodeMsg);
            }
        }
        if (user == null) {
            return Result.FAIL(errorMessage);
        }
        LocalDateTime dateTime = LocalDateTime.now().minusDays(userService.querySysConfig());
        Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        if (user.getPassTime() == null) {
            message = "初始密码，请重新设置密码。";
            return Result.FAIL(message);
        }else{
            if (user.getPassTime().before(date)) {
                message = "密码过期，请重新设置密码。";
                return Result.FAIL(message);
            }
        }
        if (user.getIsXcx() > 0 ) {
            message = "无法登陆，请联系管理员";
            return Result.FAIL(message);
        }
        if (!StringUtils.equals(user.getPassword(), password)) {
            counter.getAndIncrement();
            return Result.FAIL(errorMessage);
        }
        if (User.STATUS_LOCK.equals(user.getStatus())) {
            message = "账号已被锁定,请联系管理员！";
            return Result.FAIL(message);
        }
        // 更新用户登录时间
        this.userService.updateLoginTime(username);
        //获取该用户下面所有子用户
        String deptIds = userService.getDeptIds(user.getDeptId());
        user.setDeptIds(deptIds);

        // 保存登录记录
        LoginLog loginLog = new LoginLog();
        loginLog.setUsername(username);
        this.loginLogService.saveLoginLog(loginLog);

        List<Dept> list = deptMapper.selectList(new LambdaQueryWrapper<Dept>().eq(Dept::getDeptId,user.getDeptId()));
        Dept dept = new Dept();
        if(list != null && list.size() > 0){
            dept = list.get(0);
        }
        String token = FebsUtil.encryptToken(JWTUtil.sign(user,dept,password));
        LocalDateTime expireTime = LocalDateTime.now().plusSeconds(properties.getShiro().getJwtTimeOut());
        String expireTimeStr = DateUtil.formatFullTime(expireTime);
        JWTToken jwtToken = new JWTToken(token, expireTimeStr);

        String userId = this.saveTokenToRedis(user, jwtToken, request);
        user.setId(userId);

        Map<String, Object> userInfo = this.generateUserInfo(jwtToken, user);
        redisTemplate.delete(key);
        redisTemplate.delete(key_failed);
        return Result.SUCCESS("认证成功",userInfo);
     }

    @GetMapping("/login/index/{username}")
    public Result index(@NotBlank(message = "{required}") @PathVariable String username) {
        Map<String, Object> data = new HashMap<>(16);
        // 获取系统访问记录
        Long totalVisitCount = loginLogMapper.findTotalVisitCount();
        data.put("totalVisitCount", totalVisitCount);
        Long todayVisitCount = loginLogMapper.findTodayVisitCount();
        data.put("todayVisitCount", todayVisitCount);
        Long todayIp = loginLogMapper.findTodayIp();
        data.put("todayIp", todayIp);
        // 获取近期系统访问记录
        List<Map<String, Object>> lastSevenVisitCount = loginLogMapper.findLastSevenDaysVisitCount(null);
        data.put("lastSevenVisitCount", lastSevenVisitCount);
        User param = new User();
        param.setUsername(username);
        List<Map<String, Object>> lastSevenUserVisitCount = loginLogMapper.findLastSevenDaysVisitCount(param);
        data.put("lastSevenUserVisitCount", lastSevenUserVisitCount);
        return Result.SUCCESS(data);
    }

    @RequiresPermissions("user:online")
    @GetMapping("/login/online")
    public Result userOnline(String username) throws Exception {
        String now = DateUtil.formatFullTime(LocalDateTime.now());
        Set<String> userOnlineStringSet = redisService.zrangeByScore(FebsConst.ACTIVE_USERS_ZSET_PREFIX, now, "+inf");
        List<ActiveUser> activeUsers = new ArrayList<>();
        for (String userOnlineString : userOnlineStringSet) {
            ActiveUser activeUser = mapper.readValue(userOnlineString, ActiveUser.class);
            activeUser.setToken(null);
            if (StringUtils.isNotBlank(username)) {
                if (StringUtils.equalsIgnoreCase(username, activeUser.getUsername())) {
                    activeUsers.add(activeUser);
                }
            } else {
                activeUsers.add(activeUser);
            }
        }
        return Result.SUCCESS(activeUsers);
    }

    @GetMapping("/login/kickout/{id}")
    @RequiresPermissions("user:kickout")
    public Result kickout(@NotBlank(message = "{required}") @PathVariable String id) throws Exception {
        String now = DateUtil.formatFullTime(LocalDateTime.now());
        Set<String> userOnlineStringSet = redisService.zrangeByScore(FebsConst.ACTIVE_USERS_ZSET_PREFIX, now, "+inf");
        ActiveUser kickoutUser = null;
        String kickoutUserString = "";
        for (String userOnlineString : userOnlineStringSet) {
            ActiveUser activeUser = mapper.readValue(userOnlineString, ActiveUser.class);
            if (StringUtils.equals(activeUser.getId(), id)) {
                kickoutUser = activeUser;
                kickoutUserString = userOnlineString;
            }
        }
        if (kickoutUser != null && StringUtils.isNotBlank(kickoutUserString)) {
            // 删除 zset中的记录
            redisService.zrem(FebsConst.ACTIVE_USERS_ZSET_PREFIX, kickoutUserString);
            // 删除对应的 token缓存
            redisService.del(FebsConst.TOKEN_CACHE_PREFIX + kickoutUser.getToken() + "." + kickoutUser.getIp());
        }
        return Result.SUCCESS();
    }

    @GetMapping("/login/logout/{id}")
    public Result logout(@NotBlank(message = "{required}") @PathVariable String id) throws Exception {
        this.kickout(id);
        return Result.SUCCESS();
    }

    @PostMapping("/login/register")
    public Result register(
            @NotBlank(message = "{required}") String username,
            @NotBlank(message = "{required}") String password) throws Exception {
        this.userService.register(username, password);
        return Result.SUCCESS();
    }

    private String saveTokenToRedis(User user, JWTToken token, HttpServletRequest request) throws Exception {
        String ip = IPUtil.getIpAddr(request);

        // 构建在线用户
        ActiveUser activeUser = new ActiveUser();
        activeUser.setUsername(user.getUsername());
        activeUser.setIp(ip);
        activeUser.setToken(token.getToken());
        activeUser.setLoginAddress(AddressUtil.getCityInfo(ip));

        // zset 存储登录用户，score 为过期时间戳
        this.redisService.zadd(FebsConst.ACTIVE_USERS_ZSET_PREFIX, Double.valueOf(token.getExipreAt()), mapper.writeValueAsString(activeUser));
        // redis 中存储这个加密 token，key = 前缀 + 加密 token + .ip
        this.redisService.set(FebsConst.TOKEN_CACHE_PREFIX + token.getToken() + StringPool.DOT + ip, token.getToken(), properties.getShiro().getJwtTimeOut() * 1000);

        return activeUser.getId();
    }

    /**
     * 生成前端需要的用户信息，包括：
     * 1. token
     * 2. Vue Router
     * 3. 用户角色
     * 4. 用户权限
     * 5. 前端系统个性化配置信息
     *
     * @param token token
     * @param user  用户信息
     * @return UserInfo
     */
    private Map<String, Object> generateUserInfo(JWTToken token, User user) {
        String username = user.getUsername();
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("token", token.getToken());
        userInfo.put("expireTime", token.getExipreAt());

        Set<String> roles = this.userManager.getUserRoles(username);
        userInfo.put("roles", roles);

        Set<String> permissions = this.userManager.getUserPermissions(username);
        userInfo.put("permissions", permissions);

        UserConfig userConfig = this.userManager.getUserConfig(String.valueOf(user.getUserId()));
        userInfo.put("config", userConfig);

        user.setPassword("it's a secret");
        userInfo.put("user", user);
        return userInfo;
    }
    /**
     * 根据key获取计数器
     *
     * @param key key
     * @return
     */
    private RedisAtomicInteger getRedisCounter(String key) {
        RedisAtomicInteger counter = new RedisAtomicInteger(key, redisTemplate.getConnectionFactory());
        if (counter.get() == 0) {
            // 设置过期时间，30分钟
            counter.expire(TIME_EXPIRE, TimeUnit.MINUTES);
        }
        return counter;
    }

    public static void main(String[] args) {
        String username = StringUtils.lowerCase("testuser");
        String password = MD5Util.encrypt(username, "62c8ad0a15d9d1ca38d5dee762a16e01");
        System.out.println(password);

//      String password1 = "zhwl@2021";// 2402b788887fa5c2ef8952bf308e3532

        String password1 = "1234qwer";// 62c8ad0a15d9d1ca38d5dee762a16e01
        String password2 = MD5Util.encrypt2(password1);
        System.out.println(password2);

    }
}
