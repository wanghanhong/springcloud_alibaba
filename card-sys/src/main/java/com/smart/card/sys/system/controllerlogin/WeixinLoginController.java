package com.smart.card.sys.system.controllerlogin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.card.sys.common.annotation.Limit;
import com.smart.card.sys.common.authentication.JWTToken;
import com.smart.card.sys.common.authentication.JWTUtil;
import com.smart.card.sys.common.controller.BaseController;
import com.smart.card.sys.common.domain.ActiveUser;
import com.smart.card.sys.common.domain.FebsConst;
import com.smart.card.sys.common.exception.RedisConnectException;
import com.smart.card.sys.common.properties.FebsProperties;
import com.smart.card.sys.common.service.CacheService;
import com.smart.card.sys.common.service.RedisService;
import com.smart.card.sys.common.utils.AddressUtil;
import com.smart.card.sys.common.utils.FebsUtil;
import com.smart.card.sys.common.utils.IPUtil;
import com.smart.card.sys.common.utils.MD5Util;
import com.smart.card.sys.system.dao.DeptMapper;
import com.smart.card.sys.system.domain.po.Dept;
import com.smart.card.sys.system.domain.po.LoginLog;
import com.smart.card.sys.system.domain.po.User;
import com.smart.card.sys.system.domain.po.UserConfig;
import com.smart.card.sys.system.domain.vo.UserVo;
import com.smart.card.sys.system.manager.UserManager;
import com.smart.card.sys.system.service.LoginLogService;
import com.smart.card.sys.system.service.UserConfigService;
import com.smart.card.sys.system.service.UserService;
import com.smart.common.core.domain.Result;
import com.smart.common.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


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
    @Resource
    private CacheService cacheService;

    @Value("${xcxappKey}")
    public String xcxappKey;
    @Value("${xcxappSecret}")
    public String xcxappSecret;

    @GetMapping("/login/xcx/getOpenId")
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

    @PostMapping("/login/xcx/checkLogin")
    public Result checkLogin(HttpServletRequest request, String openId) throws Exception {
        return Result.SUCCESS("登录成功");
    }


    @PostMapping("/login/xcx/login")
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
























}
