package com.smart.device.message.parse.fire;


/**
 * @author 三多
 * @Time 2020/4/9
 */
public class ParseContext<T> {
    /**
     * 持有一个具体的策略对象
     */
    private ParseStrategy<T> strategy;

    /**
     * 具体的策略对象
     *
     * @param strategy
     */
    public ParseContext(ParseStrategy<T> strategy) {
        this.strategy = strategy;
    }

    /**
     * 解析实体
     *
     * @param
     * @return
     */
    public T parseEntity(String dataStr) {
        return strategy.assemblyData(dataStr);
    }
}
