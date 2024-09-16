package com.smart.device.install.mapper.area;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.install.entity.TBaseRegion;
import com.smart.device.common.install.entity.area.BsProvince;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author f
 */
public interface BsProvinceMapper extends BaseMapper<BsProvince> {

    List<TBaseRegion> queryProvincesByCode(@Param("code") String code);

}
