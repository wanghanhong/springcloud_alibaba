package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TCardUseCount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TCardUseCountMapper extends BaseMapper<TCardUseCount> {

    IPage<TCardUseCount> tCardUseCountList(Page<TCardUseCount> page, @Param("vo")TCardUseCount vo);


}
