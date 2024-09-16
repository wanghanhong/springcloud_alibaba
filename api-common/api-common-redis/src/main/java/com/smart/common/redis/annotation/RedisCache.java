package com.smart.common.redis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 定义缓存注解
 * @author: SanDuo
 * @date: 2020/5/23 17:47
 * @version: 1.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {
    /**
     * 键名
     *
     * @return
     */
    String key() default "";

    /**
     * 主键
     *
     * @return
     * @author zmr
     */
    String fieldKey();

    /**
     * 过期时间
     *
     * @return
     */
    long expired() default 3600;

    /**
     * 是否为查询操作
     * 如果为写入数据库的操作，该值需置为 false
     *
     * @return
     */
    boolean read() default true;
}