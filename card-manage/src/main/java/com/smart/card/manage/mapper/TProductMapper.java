package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TProductMapper extends BaseMapper<TProduct> {

    IPage<TProduct> tProductList(Page<TProduct> page, @Param("vo")TProduct vo);


}
