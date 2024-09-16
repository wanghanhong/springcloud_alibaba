package com.smart.device.message.factory.fire.analysis.service;

/**
 * @author 三多
 * @Time 2020/5/9
 */
public interface FireDeviceParse {
    /**
     * 定义数据解析方法
     * 1、按照码表解析数据
     * 2、将数据存储到数据库
     * 3、根据解析的数据
     * 4、定义事件
     *
     * @param data
     * @return
     */
    String parse(String data);
}
