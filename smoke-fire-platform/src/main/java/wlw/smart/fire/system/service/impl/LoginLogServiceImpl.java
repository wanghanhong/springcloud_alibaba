package wlw.smart.fire.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import wlw.smart.fire.common.utils.AddressUtil;
import wlw.smart.fire.common.utils.HttpContextUtil;
import wlw.smart.fire.common.utils.IPUtil;
import wlw.smart.fire.system.dao.LoginLogMapper;
import wlw.smart.fire.system.domain.po.LoginLog;
import wlw.smart.fire.system.service.LoginLogService;

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
