package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TCardProductData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.card.manage.entity.vo.TCardProductDataDto;
import com.smart.card.manage.entity.vo.TCardVo;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TCardProductDataMapper extends BaseMapper<TCardProductData> {

    IPage<TCardProductDataDto> tCardProductDataList(Page<TCardProductDataDto> page, @Param("vo")TCardVo vo);


}
