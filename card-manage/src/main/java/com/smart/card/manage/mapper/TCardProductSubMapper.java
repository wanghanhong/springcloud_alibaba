package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TCardProductSub;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TCardProductSubMapper extends BaseMapper<TCardProductSub> {

    IPage<TCardProductSub> tCardProductSubList(Page<TCardProductSub> page, @Param("vo")TCardProductSub vo);


}
