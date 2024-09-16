package com.smart.brd.sys.system.aspect;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.smart.brd.sys.system.domain.po.Dept;
import com.smart.brd.sys.system.domain.po.User;
import com.smart.common.utils.EncryptUtil;
import com.smart.common.utils.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * userId切面类
 * 对于所有的Controller传入的参数通过解析用户token获取userId-deptids，赋值到Vo中
 * 前端免去了传入userId-deptids的麻烦
 *
 */
@Aspect
@Component
public class UserIdAdvice {

    String TOKEN_CACHE_PREFIX = "febs.cache.token.";
    public static final String TOKEN_EXCEPTION = "Token异常！";
    /**
     * 切入点，定义所有的控制器
     */
    @Pointcut("execution(public * com.smart.brd.sys.system.controller.*.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        //获取上下文request，获取token并解析
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        try {
            if (requestAttributes!=null) {
                HttpServletRequest request = requestAttributes.getRequest();
                String token = request.getHeader("Authorization");
                if (token == null) {
                    throw new SecurityException(TOKEN_EXCEPTION);
                }
                String tokenStr = "";
                // 把加过密的token 解密
                EncryptUtil encryptUtil = new EncryptUtil(TOKEN_CACHE_PREFIX);
                tokenStr = encryptUtil.decrypt(token);
                if (StringUtils.isNotBlank(tokenStr)) {
                    // 从token 中获取附加的各种参数
                    DecodedJWT jwt = JWT.decode(tokenStr);
                    Claim userId = jwt.getClaim("userId");
                    if (userId == null || userId.asLong() == null) {
                        throw new SecurityException(TOKEN_EXCEPTION);
                    }
                    Long deptId = jwt.getClaim("deptId").asLong();
                    String deptIds = jwt.getClaim("deptIds").asString();
                    //获取参数并赋值
                    Object[] args = joinPoint.getArgs();
                    for (Object arg : args) {
                        getAndSetEntity(deptId, deptIds, arg);
                    }
                } else {
                    throw new SecurityException(TOKEN_EXCEPTION);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getAndSetEntity(Long deptId,String deptIds, Object arg) {
        //对于不同的Vo类进行相应的赋值
        if (arg instanceof User) {
            User vo = (User) arg;
//            vo.setCreateDept(deptId);
//            vo.setDeptIds(deptIds);
        }
    }


}
