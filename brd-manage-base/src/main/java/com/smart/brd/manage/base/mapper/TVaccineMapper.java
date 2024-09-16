package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TVaccine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TVaccineMapper extends BaseMapper<TVaccine> {

    IPage<TVaccine> tVaccineList(Page<TVaccine> page, @Param("vo")TVaccine vo);


}
