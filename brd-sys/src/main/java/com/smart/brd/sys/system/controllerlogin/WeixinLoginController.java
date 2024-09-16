package com.smart.brd.sys.system.controllerlogin;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.brd.sys.system.dao.DeptMapper;
import com.smart.brd.sys.system.domain.po.Dept;
import com.smart.brd.sys.system.domain.vo.UserVo;
import com.smart.common.core.domain.Result;
import org.springframework.transaction.annotation.Transactional;
import com.smart.brd.sys.common.annotation.Limit;
import com.smart.brd.sys.common.authentication.JWTToken;
import com.smart.brd.sys.common.authentication.JWTUtil;
import com.smart.brd.sys.common.controller.BaseController;
import com.smart.brd.sys.common.domain.ActiveUser;
import com.smart.brd.sys.common.domain.FebsConst;
import com.smart.brd.sys.common.properties.FebsProperties;
import com.smart.brd.sys.common.service.RedisService;
import com.smart.common.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.smart.brd.sys.common.utils.AddressUtil;
import com.smart.brd.sys.common.utils.FebsUtil;
import com.smart.brd.sys.common.utils.IPUtil;
import com.smart.brd.sys.common.utils.MD5Util;
import com.smart.brd.sys.system.domain.po.LoginLog;
import com.smart.brd.sys.system.domain.po.User;
import com.smart.brd.sys.system.domain.po.UserConfig;
import com.smart.brd.sys.system.manager.UserManager;
import com.smart.brd.sys.system.service.LoginLogService;
import com.smart.brd.sys.system.service.UserConfigService;
import com.smart.brd.sys.system.service.UserService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;


@Slf4j
@Validated
@RestController
public class WeixinLoginController extends BaseController {
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private RedisService redisService;
    @Resource
    private UserManager userManager;
    @Resource
    private UserService userService;
    @Resource
    private LoginLogService loginLogService;
    @Resource
    private FebsProperties properties;
    @Resource
    private ObjectMapper mapper;
    @Resource
    private DeptMapper deptMapper;
    @Resource
    private UserConfigService userConfigService;
    @Value("${xcxappKey}")
    public String xcxappKey;
    @Value("${xcxappSecret}")
    public String xcxappSecret;
    private String message;

    @GetMapping("/api/v1/iot/getOpenId")
    @ResponseBody
    public Result getSessionKeyOpenId(String wxCode){
        Map<String, String> res = new HashMap<>(16);
        log.info("ccc-code is:{}",wxCode);
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session?"
                +"appid="+xcxappKey
                +"&secret="+xcxappSecret
                +"&js_code="+wxCode   //小程序通过wx.login()获取的code
                +"&grant_type=authorization_code";

        ResponseEntity<String> responseEntiry = restTemplate.getForEntity(requestUrl, String.class);
        String body = responseEntiry.getBody();
        //解析数据
        log.info("ccc-body "+ body);
        int errcode = 0;
        try {
            JsonNode desNode = mapper.readTree(body);
            errcode = Integer.parseInt(desNode.get("errcode").toString());
            if (errcode==0){//"0"成功
                String openId = desNode.get("openid").toString();
                log.info("ccc-openid"+openId);
                res.put("openid",openId);
            }else {
                String errmsg = desNode.get("errmsg").toString();
                res.put("errcode",errcode+"");
                res.put("errmsg",errmsg);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            errcode = 0;
            res.put("errcode",errcode+"");
        }
        return Result.SUCCESS(res);
    }

    @PostMapping("/api/v1/checkLogin")
    public Result checkLogin(HttpServletRequest request, String openId) throws Exception {
        return Result.SUCCESS("登录成功");
    }

    @PostMapping("/api/v1/weixinLogin")
    @Limit(key = "login", period = 60, count = 20, name = "微信登录接口", prefix = "limit")
    public Result login(HttpServletRequest request, @RequestBody UserVo vo) throws Exception {
        String username = vo.getUsername();
        String password = vo.getPassword();
        String openId = vo.getOpenId();

        String message = "用户名或密码不能为空";
        if (username == null) {
            return Result.FAIL(message);
        }
        if (password == null) {
            return Result.FAIL(message);
        }
//        msg = "OpenId不能为空";
//        if (openId == null) {
//            throw new FebsException(msg);
//        }
        username = StringUtils.lowerCase(username);
        password = MD5Util.encrypt(username, password);

        final String errorMessage = "用户名或密码错误";
        User user = this.userManager.getUser(username);
        if (user == null) {
            return Result.FAIL(errorMessage);
        }
        if (!StringUtils.equals(user.getPassword(), password)) {
            return Result.FAIL(errorMessage);
        }
        if (User.STATUS_LOCK.equals(user.getStatus())) {
            return Result.FAIL("账号已被锁定,请联系管理员！");
        }
        if (user.getModifyTime() == null) {
            message = "密码过期，请重新设置密码。";
            return Result.FAIL(message);
        }
        // 更新用户登录时间
        this.userService.updateLogin(username,openId);
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
        //openId下发客户端
        userInfo.put("openId",openId);
        return Result.SUCCESS("认证成功",userInfo);
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

    // 小程序端-保存用户
    @PostMapping("/api/v1/saveXCXUser")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Result saveXCXUser(@RequestBody User user){
        if (StringUtils.isNotEmpty(user.getUsername())){
            List<User> users = userService.getBaseMapper().selectList(
                    new LambdaQueryWrapper<User>().eq(User::getUsername,user.getUsername())
            );
            if (!users.isEmpty()){
                message = "用户名重复，请重试";
                return Result.FAIL(message);
            }
        }
        try {
            user.setUsername(user.getUsername());
            user.setPassword(user.getPassword());
            user.setDeptId(null);
            user.setRoleId("1");
            user.setTenantId(1L);
            user.setStatus("1");
            user.setCreateTime(new Date());
            user.setPassTime(new Date());
            user.setUserType(0);
            user.setIsXcx(1);
            user.setIsShow(0);
            createUser(user);
            return Result.SUCCESS();
        } catch (Exception e) {
            log.error(message, e);
            message = "新增用户失败";
            return Result.FAIL(message);
        }
    }


    public void createUser(User user) throws Exception {
        // 创建用户
        user.setCreateTime(new Date());
        user.setPassTime(new Date());
        user.setAvatar(User.DEFAULT_AVATAR);
        user.setPassword(MD5Util.encrypt(user.getUsername(), user.getPassword()) );
        //性别默认未知
        if (ObjectUtil.isNull(user.getGender())) {
            user.setGender(User.SEX_UNKNOW);
        }
        userService.save(user);

        // 保存用户角色
        if (com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotEmpty(user.getRoleId())) {
            String[] roles = user.getRoleId().split(StringPool.COMMA);
            userService.setUserRoles(user, roles);
        }
        // 创建用户默认的个性化配置
        userConfigService.initDefaultUserConfig(String.valueOf(user.getUserId()));
        // 将用户相关信息保存到 Redis中
        userManager.loadUserRedisCache(user);
    }

    public static void main(String[] args) {
        //e10adc3949ba59abbe56e057f20f883e
        //040e57e031a33eab854b3034d6f24b3a
        // c777284c6553c8e1b05f38b383f81434
        String password = "123456";
        String password2 = MD5Util.encrypt2(password);
        System.out.println(password2);

        String password3 = "yf5@ka3d#6";
        String password4 = MD5Util.encrypt2(password3);
        System.out.println(password4);

        String password5 = "yf5@ka3d";
        String password6 = MD5Util.encrypt2(password5);
        System.out.println(password6);

    }

    @PostMapping("/api/v1/password")
    public Result updatePassword(@RequestBody UserVo vo){
        try {
            String username = org.apache.commons.lang3.StringUtils.lowerCase(vo.getUsername());
            String password = MD5Util.encrypt(username, vo.getOldPassword());
            final String errorMessage = "用户名或密码错误";
            User user = this.userManager.getUser(username);
            if (user == null) {
                return Result.FAIL(errorMessage);
            }
            if (!StringUtils.equals(user.getPassword(), password)) {
                return Result.FAIL(errorMessage);
            }
            userService.updatePassword(username, vo.getPassword(),vo.getOldPassword());
            return Result.SUCCESS("操作成功");
        } catch (Exception e) {
            message = "修改密码失败";
            log.error(message, e);
            return Result.FAIL(message);
        }
    }


}
