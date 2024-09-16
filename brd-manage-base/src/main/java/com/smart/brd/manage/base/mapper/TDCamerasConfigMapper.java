package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TDCamerasConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.brd.manage.base.entity.vo.DeviceVo;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TDCamerasConfigMapper extends BaseMapper<TDCamerasConfig> {

    IPage<DeviceVo> tDCamerasConfigList(Page<DeviceVo> page, @Param("vo")TDCamerasConfig vo);


}
