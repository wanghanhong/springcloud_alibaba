package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TSupplier;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TSupplierMapper extends BaseMapper<TSupplier> {

    IPage<TSupplier> tSupplierList(Page<TSupplier> page, @Param("vo")TSupplier vo);

    TSupplier getSupplierFromCredit(@Param("creditCode") Long credit);
}
