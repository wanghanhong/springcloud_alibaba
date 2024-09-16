package com.smart.device.install.mapper.inspection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.install.entity.TBaseInsBuildFloor;
import com.smart.device.install.entity.vo.InsBuildFloorVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author f
 */
public interface TBaseInsBuildFloorMapper extends BaseMapper<TBaseInsBuildFloor> {


    List<InsBuildFloorVo> selecByInsAndBuildAndFloor(@Param("vo") InsBuildFloorVo vo) ;

    InsBuildFloorVo selecByInsBuildAndFloor(@Param("vo") InsBuildFloorVo vo) ;

    Map<Integer,Integer> getFloorCountGroupByStatus(@Param("insBuildId")Long insBuildId);


}
