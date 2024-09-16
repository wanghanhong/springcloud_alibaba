package com.smart.card.common.area.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.card.common.area.entity.BsRegion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author f
 */
@Mapper
public interface BsRegionMapper extends BaseMapper<BsRegion> {

    List<BsRegion> selectProvinces();

    List<BsRegion> selectRegions(@Param("regionCode") String regionCode);

    List<BsRegion> mapRegions(@Param("regionCode") String regionCode);

    List<BsRegion> selectRegionsByCode(@Param("province") String province, @Param("city") String city,
                                          @Param("county") String county, @Param("town") String town);

}
