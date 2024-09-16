package com.smart.system.common.util;


import com.smart.common.utils.security.Md5Utils;
import com.smart.system.common.entity.SysUser;

/**
 * @description: 密码加密
 * @author: SanDuo
 * @date: 2020/5/23 17:17
 * @version: 1.0
 */
public class PasswordUtil {
    public static boolean matches(SysUser user, String newPassword) {
        return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
    }

    public static String encryptPassword(String username, String password, String salt) {
        return Md5Utils.hash(username + password + salt);
    }
}