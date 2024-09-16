package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TVaccinePrevention;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TVaccinePreventionMapper extends BaseMapper<TVaccinePrevention> {

    IPage<TVaccinePrevention> tVaccinePreventionList(Page<TVaccinePrevention> page, @Param("vo")TVaccinePrevention vo);


}
