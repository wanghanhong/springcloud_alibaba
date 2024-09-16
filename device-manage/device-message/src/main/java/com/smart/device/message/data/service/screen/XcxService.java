package com.smart.device.message.data.service.screen;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.common.core.page.PageDomain;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.message.vo.AlarmVo;

public interface XcxService {

    void handleAlarm(AlarmVo vo);




}
