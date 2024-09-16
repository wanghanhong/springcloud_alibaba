package wlw.smart.fire.common.function;

/**
 * @author Pano
 */
@FunctionalInterface
public interface CacheSelector<T> {
    T select() throws Exception;
}
