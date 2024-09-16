package com.smart.brd.manage.base.mapper.area;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.brd.manage.base.entity.area.BsCity;
import com.smart.brd.manage.base.entity.area.TBaseRegion;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author f
 */
public interface BsCityMapper extends BaseMapper<BsCity> {

    List<TBaseRegion> queryCitysByCode(@Param("code") String code);

    TBaseRegion queryCityByCode(@Param("code") String code);
}
