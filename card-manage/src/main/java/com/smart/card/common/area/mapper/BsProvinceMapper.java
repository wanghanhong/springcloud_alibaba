package com.smart.card.common.area.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.card.common.area.entity.BsProvince;
import com.smart.card.common.area.entity.BsRegion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author f
 */
@Mapper
public interface BsProvinceMapper extends BaseMapper<BsProvince> {

    List<BsRegion> queryProvincesByCode(@Param("code") String code);

    BsRegion queryProvinceByCode(@Param("code") String code);
}
