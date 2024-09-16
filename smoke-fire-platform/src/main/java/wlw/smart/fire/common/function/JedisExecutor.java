package wlw.smart.fire.common.function;

import wlw.smart.fire.common.exception.RedisConnectException;

/**
 * @author Pano
 */
@FunctionalInterface
public interface JedisExecutor<T, R> {
    R excute(T t) throws RedisConnectException;
}
