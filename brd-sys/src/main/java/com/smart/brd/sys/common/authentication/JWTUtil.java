package com.smart.brd.sys.common.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.smart.brd.sys.system.dao.DeptMapper;
import com.smart.brd.sys.system.domain.po.Dept;
import com.smart.brd.sys.system.domain.po.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import com.smart.brd.sys.common.properties.FebsProperties;
import com.smart.brd.sys.common.utils.SpringContextUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Pano
 */
@Slf4j
public class JWTUtil {

    private static final long EXPIRE_TIME = SpringContextUtil.getBean(FebsProperties.class).getShiro().getJwtTimeOut() * 1000 * 24 * 30;

    /**
     * 校验 token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            verifier.verify(token);
            log.info("token is valid");
            return true;
        } catch (Exception e) {
            log.info("token is invalid{}", e.getMessage());
            return false;
        }
    }

    /**
     * 从 token中获取用户名
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            log.error("error：{}", e.getMessage());
            return null;
        }
    }

    /**
     * 生成 token
     *
     * @param username 用户名
     * @param secret   用户的密码
     * @return token
     */
    public static String sign(String username, String secret) {
        try {
            username = StringUtils.lowerCase(username);
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withClaim("username", username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("error：{}", e.getMessage());
            return null;
        }
    }
    public static String sign(User user,Dept dept,String secret) {
        try {
            String username = user.getUsername();

            username = StringUtils.lowerCase(username);
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withClaim("username", username)
                    .withClaim("userId", user.getUserId())
                    .withClaim("deptId", user.getDeptId())
                    .withClaim("parentId", dept.getParentId())
                    .withClaim("userId", user.getUserId())
                    .withClaim("roleId", user.getRoleId())
                    .withClaim("mobile", user.getMobile())
                    .withClaim("isXcx", user.getIsXcx())
                    .withClaim("province", user.getProvince())
                    .withClaim("city", user.getCity())
                    .withClaim("county", user.getCounty())
                    .withClaim("town", user.getTown())
                    .withClaim("housing", user.getHousing())
                    .withClaim("deptIds",user.getDeptIds())
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("error：{}", e.getMessage());
            return null;
        }
    }
    /**
     * 生成 token
     *
     * @param tenantId 租户ID
     * @param username 用户名
     * @param secret   用户的密码
     * @return token
     */
    public static String sign(Long tenantId,String username, String secret) {
        try {
            username = StringUtils.lowerCase(username);
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withClaim("tenantId",tenantId)
                    .withClaim("username", username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("error：{}", e.getMessage());
            return null;
        }
    }
}
