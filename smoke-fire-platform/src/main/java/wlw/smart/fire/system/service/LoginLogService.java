package wlw.smart.fire.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wlw.smart.fire.system.domain.po.LoginLog;

public interface LoginLogService extends IService<LoginLog> {

    void saveLoginLog(LoginLog loginLog);
}
