package com.smart.test.demo.aspect;

import com.smart.common.utils.spring.SpringContextHolder;
import com.smart.test.demo.annotation.LogRecord;
import com.smart.test.demo.event.BaseEvent;
import com.smart.test.demo.event.entity.LogEntity;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author 三多
 * @Time 2020/6/12
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    /** 配置织入点*/
    @Pointcut("@annotation(com.smart.test.demo.annotation.LogRecord)")
    public void logPointCut() {
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()")
    public void doAfterReturning(JoinPoint joinPoint) {
        handleLog(joinPoint, null);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e){
        //获取注解
        LogRecord logRecord = getAnnotationLog(joinPoint);
        if(Objects.isNull(logRecord)){
            return;
        }
        //存入数据库
        LogEntity logEntity = new LogEntity();
        logEntity.setIp(logRecord.ip());
        logEntity.setId(logRecord.id());
        logEntity.setRemark(logRecord.remark().name());
        if(Objects.nonNull(e)){
            //存储异常信息入库
        }
        // 发布事件
        SpringContextHolder.publishEvent(new BaseEvent(logEntity));
    }

    private LogRecord getAnnotationLog(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        if(Objects.nonNull(method)){
            return method.getAnnotation(LogRecord.class);
        }
        return null;
    }


}
