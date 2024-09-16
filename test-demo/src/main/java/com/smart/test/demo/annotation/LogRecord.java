package com.smart.test.demo.annotation;

import com.smart.test.demo.enmus.OperatorType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 三多
 * @Time 2020/6/12
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogRecord {
    /**
     * id
     */
    public String id() default "";

    /**
     * ip
     */
    public String ip() default "127.0.0.1";

    /**
     * 操作人类别
     */
    public OperatorType remark() default OperatorType.SAVE;

}
