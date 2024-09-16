package com.smart.brd.manage.message.parse;


/**
 * @author 三多
 */
public class ParseContext<T> {
    /**
     * 持有一个具体的策略对象
     */
    private final ParseStrategy<T> strategy;

    /**
     * 具体的策略对象
     *
     * @param strategy s
     */
    public ParseContext(ParseStrategy<T> strategy) {
        this.strategy = strategy;
    }

    /**
     * 解析实体
     *
     * @param dataStr data
     * @return T
     */
    public T parseEntity(String dataStr) {
        return strategy.assemblyData(dataStr);
    }
}
