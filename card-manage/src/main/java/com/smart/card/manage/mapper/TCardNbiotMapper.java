package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TCardNbiot;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TCardNbiotMapper extends BaseMapper<TCardNbiot> {

    IPage<TCardNbiot> tCardNbiotList(Page<TCardNbiot> page, @Param("vo")TCardNbiot vo);


}
