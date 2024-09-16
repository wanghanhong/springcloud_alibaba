package com.smart.device.install.mapper.screen;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.install.entity.vo.ScreenProvinceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author f
 */
public interface WholeProvinceMapper extends BaseMapper<ScreenProvinceVo> {


    List<ScreenProvinceVo> deviceSmokeNum(@Param("parentType") Integer parentType);

    List<ScreenProvinceVo> deviceWaterpressNum(@Param("parentType") Integer parentType);

    List<ScreenProvinceVo> deviceElectricNum(@Param("parentType") Integer parentType);

    Integer deviceSmokeAll(@Param("parentType") Integer parentType);

    Integer deviceWaterpressAll(@Param("parentType") Integer parentType);

    Integer deviceElectricAll(@Param("parentType") Integer parentType);

    Integer companyAll();

    Integer fireCompanyAll();

}
