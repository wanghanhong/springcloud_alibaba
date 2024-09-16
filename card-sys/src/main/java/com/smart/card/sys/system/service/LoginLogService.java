package com.smart.card.sys.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.card.sys.system.domain.po.LoginLog;

public interface LoginLogService extends IService<LoginLog> {

    void saveLoginLog(LoginLog loginLog);
}
