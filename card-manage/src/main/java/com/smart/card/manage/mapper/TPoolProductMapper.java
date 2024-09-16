package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TPoolProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TPoolProductMapper extends BaseMapper<TPoolProduct> {

    IPage<TPoolProduct> tPoolProductList(Page<TPoolProduct> page, @Param("vo")TPoolProduct vo);


}
