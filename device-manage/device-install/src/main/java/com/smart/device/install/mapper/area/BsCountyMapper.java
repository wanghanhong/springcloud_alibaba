package com.smart.device.install.mapper.area;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.install.entity.TBaseRegion;
import com.smart.device.common.install.entity.area.BsCounty;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author f
 */
public interface BsCountyMapper extends BaseMapper<BsCounty> {

    List<TBaseRegion> queryCountysByCode(@Param("code") String code);

}
