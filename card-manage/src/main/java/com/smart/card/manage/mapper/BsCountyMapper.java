package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.BsCounty;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface BsCountyMapper extends BaseMapper<BsCounty> {

    IPage<BsCounty> bsCountyList(Page<BsCounty> page, @Param("vo")BsCounty vo);


}
