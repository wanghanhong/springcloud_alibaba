package com.smart.device.message.data.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.message.entity.TStrategyAlarm;

import java.util.Map;

/**
 * @author
 */
public interface TStrategyAlarmService extends IService<TStrategyAlarm> {

    IPage<TStrategyAlarm> tStrategyAlarmList(Page page, TStrategyAlarm TStrategyAlarm);

    int tStrategyAlarmAdd(TStrategyAlarm tStrategyAlarm);

    TStrategyAlarm queryStrategyByTypeAndCode(Integer deviceType,String eventCode);

    // 整合短信电话参数，然后放入列队
    void getParamAndMQSend(DeviceCompanyVo companyVo,Integer deviceType,String eventCode);
    void getParamAndMQSendOnly(DeviceCompanyVo companyVo,Integer deviceType,String eventCode);

    Map<Integer,String> queryStrategyMap(Integer deviceType);

}
