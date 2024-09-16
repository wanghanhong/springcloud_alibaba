package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TDeviceCameras;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TDeviceCamerasMapper extends BaseMapper<TDeviceCameras> {

    IPage<TDeviceCameras> tDeviceCamerasList(Page<TDeviceCameras> page, @Param("vo")TDeviceCameras vo);


}
