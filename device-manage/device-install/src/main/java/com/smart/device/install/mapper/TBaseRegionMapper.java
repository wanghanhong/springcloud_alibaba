package com.smart.device.install.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.install.entity.TBaseRegion;
import org.apache.ibatis.annotations.Param;
import java.util.List;


/**
 * @author f
 */
public interface TBaseRegionMapper extends BaseMapper<TBaseRegion> {

    List<TBaseRegion> selectProvinces();

    List<TBaseRegion> selectRegions(@Param("regionCode") String regionCode);

    List<TBaseRegion> mapRegions(@Param("regionCode") String regionCode);

    List<TBaseRegion> selectRegionsByCode(@Param("province") String province, @Param("city") String city,
                                      @Param("county") String county, @Param("town") String town);

}
