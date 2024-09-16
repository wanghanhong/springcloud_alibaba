package com.smart.device.install.mapper.screen;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.install.entity.vo.ScreenProvinceVo;
import com.smart.device.common.install.entity.vo.ScreenVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author f
 */
public interface DeviceInstallScreenMapper extends BaseMapper<ScreenVo> {


    List<ScreenVo> deviceStateSmoke(@Param("parentType") Integer parentType);

    List<ScreenVo> deviceStateWaterpress(@Param("parentType") Integer parentType);

    List<ScreenVo> deviceStateElectric(@Param("parentType") Integer parentType);


    List<ScreenVo> deviceTypeSmoke(@Param("parentType") Integer parentType);

    List<ScreenVo> deviceTypeWaterpress(@Param("parentType") Integer parentType);

    List<ScreenVo> deviceTypeElectric(@Param("parentType") Integer parentType);

    /***按照单位统计设备数量********************************************/
    List<ScreenVo> deviceSmokeNumByCompany(@Param("parentType") Integer parentType);

    List<ScreenVo> deviceWaterpressNumByCompany(@Param("parentType") Integer parentType);

    List<ScreenVo> deviceElectricNumByCompany(@Param("parentType") Integer parentType);

    // 查询设备的关联信息
    List<ScreenVo> deviceBuildCompanyElectric(@Param("vo") ScreenVo vo);



}
