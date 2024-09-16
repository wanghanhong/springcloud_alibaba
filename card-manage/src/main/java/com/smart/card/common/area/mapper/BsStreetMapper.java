package com.smart.card.common.area.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.card.common.area.entity.BsRegion;
import com.smart.card.common.area.entity.BsStreet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author f
 */
@Mapper
public interface BsStreetMapper extends BaseMapper<BsStreet> {

    List<BsRegion> queryStreetsByCode(@Param("code") String code);

    BsRegion queryStreetByCode(@Param("code") String code);
}
