package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TCardData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TCardDataMapper extends BaseMapper<TCardData> {

    IPage<TCardData> tCardDataList(Page<TCardData> page, @Param("vo")TCardData vo);


}
