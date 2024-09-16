package com.smart.brd.manage.base.mapper.area;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.brd.manage.base.entity.area.BsStreet;
import com.smart.brd.manage.base.entity.area.TBaseRegion;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author f
 */
public interface BsStreetMapper extends BaseMapper<BsStreet> {

    List<TBaseRegion> queryStreetsByCode(@Param("code") String code);

    TBaseRegion queryStreetByCode(@Param("code") String code);
}
