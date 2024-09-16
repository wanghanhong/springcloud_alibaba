package com.smart.brd.sys.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.brd.sys.system.domain.po.LoginLog;

public interface LoginLogService extends IService<LoginLog> {

    void saveLoginLog(LoginLog loginLog);
}
