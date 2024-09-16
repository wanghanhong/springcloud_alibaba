package com.smart.card.sys.system.controller;

import cn.hutool.http.HttpStatus;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.smart.card.sys.common.domain.FebsConstant;
import com.smart.card.sys.common.domain.QueryRequest;
import com.smart.card.sys.common.utils.MD5Util;
import com.smart.card.sys.system.domain.po.Dept;
import com.smart.card.sys.system.domain.po.User;
import com.smart.card.sys.system.service.DeptService;
import com.smart.card.sys.system.service.UserService;
import com.smart.card.sys.system.usertoken.entity.UserBean;
import com.smart.card.sys.system.usertoken.service.UserTokenService;
import com.smart.common.core.domain.Result;
import com.smart.common.utils.EncryptUtil;
import com.smart.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Validated
@RestController
public class WUserController {
    @Resource
    private UserService userService;
    @Resource
    private DeptService deptService;
    @Resource
    private UserTokenService userTokenService;
    private String message;

    @GetMapping("/api/v1/user/getUserInfoByToken")
    @ResponseBody
    public Result getUserInfoByToken(String token) {
        try {
            if(StringUtils.isNotBlank(token)){
                User user =userInfoByToken(token);
                return Result.SUCCESS(user);
            }else{
                message = "参数错误";
                return Result.FAIL(message);
            }
        } catch (Exception e) {
            message = "参数错误";
            return Result.FAIL(message);
        }
    }
    public User userInfoByToken(String token){
        User user = new User();
        try {
            if(token != null){
                // 把加过密的token 解密
                EncryptUtil encryptUtil = new EncryptUtil(FebsConstant.TOKEN_CACHE_PREFIX);
                String tokenStr = encryptUtil.decrypt(token);
                // 从token 中获取附加的各种参数
                DecodedJWT jwt = JWT.decode(tokenStr);
                Claim userId = jwt.getClaim("userId");
                String username = jwt.getClaim("username").asString();
                user = userService.findByName(username);
                user.setPassword(null);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @GetMapping("/api/v1/sys/queryCompany")
    @ResponseBody
    public Result queryCompany(HttpServletRequest request, QueryRequest queryRequest, User user) {
        UserBean userBean = userTokenService.getUserByToken(request);
        Dept dept = new Dept();
        List<Dept> depts = new ArrayList<>();
        try {
            dept.setDeptId(userBean.getDeptId());
            depts = deptService.quetyDeptLists(dept,queryRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.SUCCESS(depts);
    }


    public static void main(String[] args) {
        String password = "1234qwer";
        String password2 = MD5Util.encrypt2(password);

        String path = "/auths/menu";
        path = path.substring(path.lastIndexOf("/")+1);
        String ss = path+":"+"add";
        System.out.println(ss);

        System.out.println(password2);
    }








}
