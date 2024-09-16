package com.smart.brd.sys.system.controller;

import cn.hutool.http.HttpStatus;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.smart.brd.sys.common.domain.FebsConstant;
import com.smart.brd.sys.common.domain.FebsResponse;
import com.smart.brd.sys.common.domain.QueryRequest;
import com.smart.brd.sys.common.utils.*;
import com.smart.brd.sys.system.domain.po.Dept;
import com.smart.brd.sys.system.domain.po.User;
import com.smart.brd.sys.system.service.DeptService;
import com.smart.brd.sys.system.service.UserService;
import com.smart.brd.sys.system.usertoken.entity.UserBean;
import com.smart.brd.sys.system.usertoken.service.UserTokenService;
import com.smart.common.utils.EncryptUtil;
import com.smart.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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

    //@GetMapping("/api/v2/user/getUserInfoByToken")
    @ResponseBody
    public FebsResponse getUserInfoByToken(HttpServletRequest request,String token) {
        try {
            if (StringUtils.isBlank(token)) {
                token = request.getHeader("Authorization");
            }
            if(StringUtils.isNotBlank(token)){
                User user =userInfoByToken(token);
                return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(user);
            }else{
                return new FebsResponse().code(HttpStatus.HTTP_INTERNAL_ERROR).message("参数错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new FebsResponse().code(HttpStatus.HTTP_INTERNAL_ERROR).message("参数错误");
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
    public static void main(String[] args) {
        String password = "1234qwer";
        String password2 = MD5Util.encrypt2(password);
        System.out.println(password2);
    }

    @GetMapping("/api/v2/sys/queryCompany")
    @ResponseBody
    public FebsResponse queryCompany(HttpServletRequest request,QueryRequest queryRequest,User user) {
        UserBean userBean = userTokenService.getUserByToken(request);
        Dept dept = new Dept();
        List<Dept> depts = new ArrayList<>();
        try {
            dept.setDeptId(userBean.getDeptId());
            depts = deptService.quetyDeptLists(dept,queryRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(depts);
    }























}
