package com.smart.device.install.mapper.area;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.install.entity.TBaseRegion;
import com.smart.device.common.install.entity.area.BsVillage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author f
 */
public interface BsVillageMapper extends BaseMapper<BsVillage> {

    List<TBaseRegion> queryVillagesByCode(@Param("code") String code);

}
