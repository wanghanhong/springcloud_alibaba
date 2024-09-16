package com.smart.brd.manage.message.factory;

/**
 * @author junglelocal
 */

public interface DeviceParseFactory {

    /**
     * 分析数据
     *
     * @param type 类型（设备）
     * @param data 需要解析的数据
     * @return 结果
     */
    String analysis(String type, String data);

    String analysisNew(String type, String data);
}
