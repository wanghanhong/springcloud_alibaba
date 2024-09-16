package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TDisease;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TDiseaseMapper extends BaseMapper<TDisease> {

    IPage<TDisease> tDiseaseList(Page<TDisease> page, @Param("vo")TDisease vo);

    TDisease getDiseaseByCode(@Param("code") String code);
}
