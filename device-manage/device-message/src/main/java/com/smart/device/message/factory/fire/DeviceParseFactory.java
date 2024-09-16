package com.smart.device.message.factory.fire;


/**
 * 定义工厂接口
 *
 * @author 三多
 * @Time 2020/5/9
 */

public interface DeviceParseFactory {
    /**
     * 分析数据
     *
     * @param type 类型（设备）
     * @param data 需要解析的数据
     * @return
     */
    String analysis(String type, String data);
}
