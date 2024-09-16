package com.smart.brd.manage.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.brd.manage.base.entity.THeartDevice;
import com.smart.brd.manage.base.entity.vo.AnalysisVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 
 */

public interface THeartDeviceMapper extends BaseMapper<THeartDevice> {

    IPage<THeartDevice> tHeartDeviceList(Page<THeartDevice> page, @Param("vo") THeartDevice vo);


    List<AnalysisVo> temperatureCurve(@Param("deviceCode") String deviceCode);
}
