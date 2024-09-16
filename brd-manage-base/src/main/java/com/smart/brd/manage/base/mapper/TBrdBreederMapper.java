package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TBrdBreeder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TBrdBreederMapper extends BaseMapper<TBrdBreeder> {

    IPage<TBrdBreeder> tBrdBreederList(Page<TBrdBreeder> page, @Param("vo")TBrdBreeder vo);


}
