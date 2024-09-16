package com.smart.system.common.util;

import lombok.experimental.UtilityClass;

/**
 * @author 三多
 * @Time 2020/7/14
 */
@UtilityClass
public class SystemUtils {
    /**
     * 判断是否是超级管理员
     *
     * @param userId
     * @return
     */
    public boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

}
