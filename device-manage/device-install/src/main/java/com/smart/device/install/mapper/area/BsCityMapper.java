package com.smart.device.install.mapper.area;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.install.entity.TBaseRegion;
import com.smart.device.common.install.entity.area.BsCity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author f
 */
public interface BsCityMapper extends BaseMapper<BsCity> {

    List<TBaseRegion> queryCitysByCode(@Param("code") String code);

}
