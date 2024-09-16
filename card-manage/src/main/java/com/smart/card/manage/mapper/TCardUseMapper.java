package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TCardUse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TCardUseMapper extends BaseMapper<TCardUse> {

    IPage<TCardUse> tCardUseList(Page<TCardUse> page, @Param("vo")TCardUse vo);

    TCardUse getUse(@Param("accNbr") String acc, @Param("date") String date);
}
