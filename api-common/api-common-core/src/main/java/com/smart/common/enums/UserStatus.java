package com.smart.common.enums;

/**
 * @description: 用户状态
 * @author: SanDuo
 * @date: 2020/5/27 10:49
 * @version: 1.0
 */
public enum UserStatus {
    OK("0", "正常"), DISABLE("1", "停用"), DELETED("2", "删除");

    private final String code;
    private final String info;

    UserStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
