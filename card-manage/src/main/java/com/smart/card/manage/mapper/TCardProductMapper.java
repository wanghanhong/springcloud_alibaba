package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TCardProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TCardProductMapper extends BaseMapper<TCardProduct> {

    IPage<TCardProduct> tCardProductList(Page<TCardProduct> page, @Param("vo")TCardProduct vo);


}
