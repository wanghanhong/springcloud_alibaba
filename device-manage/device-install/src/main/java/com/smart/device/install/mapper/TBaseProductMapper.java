package com.smart.device.install.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.device.common.install.entity.TBaseProduct;
import org.apache.ibatis.annotations.Param;

/**
 * @author f
 */
public interface TBaseProductMapper extends BaseMapper<TBaseProduct> {

    IPage<TBaseProduct> baseProductList(Page<TBaseProduct> page, @Param("vo") TBaseProduct vo);

}
