package com.smart.brd.manage.base.mapper.area;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.brd.manage.base.entity.area.BsVillage;
import com.smart.brd.manage.base.entity.area.TBaseRegion;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author f
 */
public interface BsVillageMapper extends BaseMapper<BsVillage> {

    List<TBaseRegion> queryVillagesByCode(@Param("code") String code);

    TBaseRegion queryVillageByCode(@Param("code") String code);

}
