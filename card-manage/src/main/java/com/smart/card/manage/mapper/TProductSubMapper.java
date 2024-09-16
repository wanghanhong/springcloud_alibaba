package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TProductSub;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TProductSubMapper extends BaseMapper<TProductSub> {

    IPage<TProductSub> tProductSubList(Page<TProductSub> page, @Param("vo")TProductSub vo);


}
