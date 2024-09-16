package com.smart.common.log.publish;


import com.smart.common.constant.Constants;
import com.smart.common.log.event.SysLoginInfoEvent;
import com.smart.common.utils.AddressUtils;
import com.smart.common.utils.IpUtils;
import com.smart.common.utils.ServletUtils;
import com.smart.common.utils.spring.SpringContextHolder;
import com.smart.system.common.entity.SysLoginInfo;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义发布事件
 *
 * @description: 工厂类
 * @author: SanDuo
 * @date: 2020/5/23 17:20
 * @version: 1.0
 */
public class PublishFactory {
    /**
     * 记录登陆信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息
     * @param args     列表
     */
    public static void recordLoginInfo(final String username, final String status, final String message,
                                       final Object... args) {
        HttpServletRequest request = ServletUtils.getRequest();
        final UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        final String ip = IpUtils.getIpAddr(request);
        // 获取客户端操作系统
        String os = userAgent.getOperatingSystem().getName();
        // 获取客户端浏览器
        String browser = userAgent.getBrowser().getName();
        // 封装对象
        SysLoginInfo loginInfo = new SysLoginInfo();
        loginInfo.setLoginName(username);
        loginInfo.setIpaddr(ip);
        loginInfo.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
        loginInfo.setBrowser(browser);
        loginInfo.setOs(os);
        loginInfo.setMsg(message);
        // 日志状态
        if (Constants.LOGIN_SUCCESS.equals(status) || Constants.LOGOUT.equals(status)) {
            loginInfo.setStatus(Constants.SUCCESS);
        } else if (Constants.LOGIN_FAIL.equals(status)) {
            loginInfo.setStatus(Constants.FAIL);
        }
        // 发布事件
        SpringContextHolder.publishEvent(new SysLoginInfoEvent(loginInfo));
    }
}
