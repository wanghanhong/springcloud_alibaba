package com.smart.brd.sys.common.function;

/**
 * @author Pano
 */
@FunctionalInterface
public interface CacheSelector<T> {
    T select() throws Exception;
}
