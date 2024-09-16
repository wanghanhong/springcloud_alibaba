package com.smart.brd.manage.base.mapper.area;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.brd.manage.base.entity.area.BsProvince;
import com.smart.brd.manage.base.entity.area.TBaseRegion;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author f
 */
public interface BsProvinceMapper extends BaseMapper<BsProvince> {

    List<TBaseRegion> queryProvincesByCode(@Param("code") String code);

    TBaseRegion queryProvinceByCode(@Param("code") String code);
}
