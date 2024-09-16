package com.smart.card.common.area.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.card.common.area.entity.BsCounty;
import com.smart.card.common.area.entity.BsRegion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author f
 */
@Mapper
public interface BsCountyMapper extends BaseMapper<BsCounty> {

    List<BsRegion> queryCountysByCode(@Param("code") String code);

    BsRegion queryCountyByCode(@Param("code") String code);
}
