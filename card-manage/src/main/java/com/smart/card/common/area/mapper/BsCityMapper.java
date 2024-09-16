package com.smart.card.common.area.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.card.common.area.entity.BsCity;
import com.smart.card.common.area.entity.BsRegion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author f
 */
@Mapper
public interface BsCityMapper extends BaseMapper<BsCity> {

    List<BsRegion> queryCitysByCode(@Param("code") String code);

    BsRegion queryCityByCode(@Param("code") String code);
}
