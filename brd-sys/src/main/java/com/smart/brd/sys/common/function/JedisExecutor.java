package com.smart.brd.sys.common.function;

import com.smart.brd.sys.common.exception.RedisConnectException;

/**
 * @author Pano
 */
@FunctionalInterface
public interface JedisExecutor<T, R> {
    R excute(T t) throws RedisConnectException;
}
