package com.smart.common.enums;

/**
 * @description: 用户会话
 * @author: SanDuo
 * @date: 2020/5/26 16:34
 * @version: 1.0
 */
public enum OnlineStatusEnum {
    /**
     * 用户状态
     */
    ON_LINE("在线"), OFF_LINE("离线");

    private final String info;

    private OnlineStatusEnum(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
