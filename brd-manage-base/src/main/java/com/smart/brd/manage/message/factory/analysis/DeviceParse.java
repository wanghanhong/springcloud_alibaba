package com.smart.brd.manage.message.factory.analysis;

/**
 * @author junglelocal
 */
public interface DeviceParse {

    /**
     * 定义数据解析方法
     * 1、按照码表解析数据
     * 2、将数据存储到数据库
     * 3、根据解析的数据
     * 4、定义事件
     *
     * @param data data
     * @return result
     */
    String parse(String data);

    String parseNew(String data);
}
