package com.smart.device.message.parse.fire;


import com.smart.device.message.common.utils.Base64Utils;

import java.util.List;

/**
 * 设备数据解析策略
 * 下行数据：数据获取
 * 上行数据：数据查询封装
 *
 * @author 三多
 * @Time 2020/4/8
 */
public interface ParseStrategy<T> {

    /**
     * @param
     * @return
     */
    public T assemblyData(String dataStr);

    /**
     * 获取Imei
     *
     * @param hexList
     * @return
     */
    default String getImei(List<String> hexList) {
        StringBuilder imei = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            char[] arr = hexList.get(13 + i).toCharArray();
            imei.append(arr[1]);
            if (i != 7) {
                imei.append(arr[0]);
            }
        }
        return imei.toString();
    }

    /**
     * 华强-烟雾报警器
     * base64Str 解码
     *
     * @param base64Str
     * @return
     */
    default List<String> parseBase64Str(String base64Str) {
        List<String> strings = Base64Utils.decodeBase64To16(base64Str);
        System.out.println("原生：" + strings);
        int start = strings.indexOf("55") + 3;
        return strings.subList(start, strings.size());
    }
}
