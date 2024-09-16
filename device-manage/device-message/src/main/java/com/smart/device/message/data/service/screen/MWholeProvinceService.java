package com.smart.device.message.data.service.screen;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.install.entity.vo.ScreenProvinceVo;

/**
 * @author f
 */
public interface MWholeProvinceService extends IService<ScreenProvinceVo> {

    int alarmSmokeNum(String ids,Integer type);
    int alarmWaterpressNum(String ids,Integer type);
    int alarmElectricNum(String ids,Integer type);


}
