package com.smart.device.message.data.mapper.screen;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.install.entity.vo.ScreenProvinceVo;
import com.smart.device.common.install.entity.vo.ScreenVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author f
 */
public interface MWholeProvinceMapper extends BaseMapper<ScreenProvinceVo> {


    Integer eventSmokeNum(@Param("vo") ScreenVo vo);

    Integer eventWaterpressNum(@Param("vo") ScreenVo vo);

    Integer eventElectricNum(@Param("vo") ScreenVo vo);

    Integer alarmSmokeNum(@Param("vo") ScreenVo vo);

    Integer alarmWaterpressNum(@Param("vo") ScreenVo vo);

    Integer alarmElectricNum(@Param("vo") ScreenVo vo);

}
