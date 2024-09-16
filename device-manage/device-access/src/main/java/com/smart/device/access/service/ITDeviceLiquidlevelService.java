package com.smart.device.access.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.access.entity.TDeviceLiquidlevel;

/**
* @author 
*/
public interface ITDeviceLiquidlevelService extends IService<TDeviceLiquidlevel> {

    TDeviceLiquidlevel deviceLiquidlevelUpdate(TDeviceLiquidlevel entity);

}
