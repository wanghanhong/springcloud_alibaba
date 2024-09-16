package com.smart.card.sys.common.function;

import com.smart.card.sys.common.exception.RedisConnectException;

@FunctionalInterface
public interface JedisExecutor<T, R> {
    R excute(T t) throws RedisConnectException;
}
