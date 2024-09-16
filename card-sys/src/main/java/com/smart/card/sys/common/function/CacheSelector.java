package com.smart.card.sys.common.function;

@FunctionalInterface
public interface CacheSelector<T> {
    T select() throws Exception;
}
