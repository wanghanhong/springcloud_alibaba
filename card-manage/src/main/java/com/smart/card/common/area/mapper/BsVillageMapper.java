package com.smart.card.common.area.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.card.common.area.entity.BsRegion;
import com.smart.card.common.area.entity.BsVillage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author f
 */
@Mapper
public interface BsVillageMapper extends BaseMapper<BsVillage> {

    List<BsRegion> queryVillagesByCode(@Param("code") String code);

    BsRegion queryVillageByCode(@Param("code") String code);

}
