package com.smart.brd.sys.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.smart.brd.sys.common.utils.AddressUtil;
import com.smart.brd.sys.common.utils.HttpContextUtil;
import com.smart.brd.sys.common.utils.IPUtil;
import com.smart.brd.sys.system.dao.LoginLogMapper;
import com.smart.brd.sys.system.domain.po.LoginLog;
import com.smart.brd.sys.system.service.LoginLogService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Pano
 */
@Service("loginLogService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveLoginLog(LoginLog loginLog) {
        loginLog.setLoginTime(new Date());
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String ip = IPUtil.getIpAddr(request);
        loginLog.setIp(ip);
        loginLog.setLocation(AddressUtil.getCityInfo(ip));
        this.save(loginLog);
    }
}
