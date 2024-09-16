package com.smart.brd.manage.message.parse;


/**
 * 设备数据解析策略
 * 下行数据：数据获取
 * 上行数据：数据查询封装
 *
 * @author 三多
 */
public interface ParseStrategy<T> {

    /**
     * 整合数据
     * @param dataStr data
     * @return T
     */
    T assemblyData(String dataStr);

}
