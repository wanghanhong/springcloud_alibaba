package com.smart.device.install.mapper.area;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.install.entity.TBaseRegion;
import com.smart.device.common.install.entity.area.BsStreet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author f
 */
public interface BsStreetMapper extends BaseMapper<BsStreet> {

    List<TBaseRegion> queryStreetsByCode(@Param("code") String code);

}
