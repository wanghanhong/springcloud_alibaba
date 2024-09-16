package com.smart.device.message.data.mapper.screen;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.install.entity.vo.ScreenVo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author f
 */
public interface DeviceMessageScreenMapper extends BaseMapper<ScreenVo> {

    // 烟感气感类-按照事件类型统计
    List<ScreenVo> deviceSmokeEventNum(@Param("vo") ScreenVo vo);

    List<ScreenVo> deviceWaterpressEventNum(@Param("vo") ScreenVo vo);

    List<ScreenVo> deviceElectricEventNum(@Param("vo") ScreenVo vo);

    // 烟感气感类-按照单位排行统计-取获取10条
    List<ScreenVo> eventNumByCompanySmoke(@Param("vo") ScreenVo vo);
    List<ScreenVo> eventNumByCompanyWaterpress(@Param("vo") ScreenVo vo);
    List<ScreenVo> eventNumByCompanyElectric(@Param("vo") ScreenVo vo);
    // 烟感气感类-按照设备排行统计-取获取10条
    List<ScreenVo> eventNumByDeviceSmoke(@Param("vo") ScreenVo vo);
    List<ScreenVo> eventNumByDeviceWaterpress(@Param("vo") ScreenVo vo);
    List<ScreenVo> eventNumByDeviceElectric(@Param("vo") ScreenVo vo);

    List<ScreenVo> eventNumByMonthSmoke(@Param("vo") ScreenVo vo);
    List<ScreenVo> eventNumByMonthWaterpress(@Param("vo") ScreenVo vo);
    List<ScreenVo> eventNumByMonthElectric(@Param("vo") ScreenVo vo);

}
