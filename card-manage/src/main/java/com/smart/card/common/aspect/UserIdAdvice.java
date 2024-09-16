package com.smart.card.common.aspect;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.smart.card.common.usertoken.constant.RedisConst;
import com.smart.card.manage.entity.TCard;
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

    /**
     * 切入点，定义所有的控制器
     */
    @Pointcut("execution(public * com.smart.card.cardapi.controller.*.*(..))" +
//            "||execution(public * com.smart.card.manage.controller.*.*(..))" +
            "||execution(public * com.smart.card.common.area.controller.*.*(..))" +
            "||execution(public * com.smart.card.common.dict.controller.*.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        //获取上下文request，获取token并解析
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        try {
            HttpServletRequest request = requestAttributes.getRequest();
            String token = request.getHeader("Authorization");
            if(token == null){
                throw new SecurityException("Token异常！");
            }
            String tokenStr = "";
            // 把加过密的token 解密
            EncryptUtil encryptUtil = new EncryptUtil(RedisConst.TOKEN_CACHE_PREFIX);
            tokenStr = encryptUtil.decrypt(token);
            if(StringUtils.isNotBlank(tokenStr)){
                // 从token 中获取附加的各种参数
                DecodedJWT jwt = JWT.decode(tokenStr);
                Claim userId = jwt.getClaim("userId");
                if(userId == null || userId.asLong() == null){
                    throw new SecurityException("Token异常！");
                }
                Long deptId = jwt.getClaim("deptId").asLong();
                Long parentId = jwt.getClaim("parentId").asLong();
                String deptIds = jwt.getClaim("deptIds").asString();
                //获取参数并赋值
                Object[] args = joinPoint.getArgs();
                for (Object arg : args){
                    getAndSetEntity(deptId,parentId,deptIds,arg);
                }
            }else{
                throw new SecurityException("Token异常！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getAndSetEntity(Long deptId,Long parentId,String deptIds, Object arg) {
        //对于不同的Vo类进行相应的赋值
        if (arg instanceof TCard) {
            TCard vo = (TCard) arg;
            vo.setGroupId(deptId);
            vo.setDeptIds(deptIds);
        }
    }

}
