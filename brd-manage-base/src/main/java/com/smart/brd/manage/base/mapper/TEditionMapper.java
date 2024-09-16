package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TEdition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TEditionMapper extends BaseMapper<TEdition> {

    IPage<TEdition> tEditionList(Page<TEdition> page, @Param("vo") TEdition vo);


}
