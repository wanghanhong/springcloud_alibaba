package com.smart.common.utils;

import java.io.File;

/**
 * @description: 高频方法集合类
 * @author: SanDuo
 * @date: 2020/5/22 18:28
 * @version: 1.0
 */
public class ToolUtil {
    /**
     * 获取临时目录
     *
     * @return
     */
    public static String getTempPath() {
        return System.getProperty("java.io.tmpdir");
    }

    /**
     * 获取当前项目工作目录
     *
     * @return
     */
    public static String getUserDir() {
        return System.getProperty("user.dir");
    }

    /**
     * 获取临时下载目录
     *
     * @return
     */
    public static String getDownloadPath() {
        return getTempPath() + File.separator + "download" + File.separator;
    }
}
