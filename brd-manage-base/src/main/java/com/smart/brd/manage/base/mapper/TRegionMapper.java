package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TRegion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TRegionMapper extends BaseMapper<TRegion> {

    IPage<TRegion> tRegionList(Page<TRegion> page, @Param("vo")TRegion vo);


}
