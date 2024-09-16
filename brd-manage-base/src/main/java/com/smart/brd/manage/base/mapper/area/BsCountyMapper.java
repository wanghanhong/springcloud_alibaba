package com.smart.brd.manage.base.mapper.area;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.brd.manage.base.entity.area.BsCounty;
import com.smart.brd.manage.base.entity.area.TBaseRegion;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author f
 */
public interface BsCountyMapper extends BaseMapper<BsCounty> {

    List<TBaseRegion> queryCountysByCode(@Param("code") String code);

    TBaseRegion queryCountyByCode(@Param("code") String code);
}
