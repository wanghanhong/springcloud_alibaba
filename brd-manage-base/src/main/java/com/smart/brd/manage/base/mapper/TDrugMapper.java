package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TDrug;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TDrugMapper extends BaseMapper<TDrug> {

    IPage<TDrug> tDrugList(Page<TDrug> page, @Param("vo")TDrug vo);


}
