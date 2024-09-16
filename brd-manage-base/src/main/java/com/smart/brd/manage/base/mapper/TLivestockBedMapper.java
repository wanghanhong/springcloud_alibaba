package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.BarnsVo;
import com.smart.brd.manage.base.entity.TLivestockBed;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.brd.manage.base.entity.vo.TLivestockBedVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 
 */

public interface TLivestockBedMapper extends BaseMapper<TLivestockBed> {

    IPage<BarnsVo> tLivestockBedList(Page<BarnsVo> page, @Param("vo")BarnsVo vo);


    List<TLivestockBedVo> queryBreederList(@Param("vo")TLivestockBedVo vo);
}
