package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TCardStatus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TCardStatusMapper extends BaseMapper<TCardStatus> {

    IPage<TCardStatus> tCardStatusList(Page<TCardStatus> page, @Param("vo")TCardStatus vo);


}
