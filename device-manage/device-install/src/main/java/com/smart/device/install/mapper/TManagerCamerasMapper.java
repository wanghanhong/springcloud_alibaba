package com.smart.device.install.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.device.common.install.entity.TManagerCameras;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author f
 */
//@Mapper
public interface TManagerCamerasMapper extends BaseMapper<TManagerCameras> {

    IPage<TManagerCameras> managerCamerasList(Page<TManagerCameras> page, @Param("vo") TManagerCameras vo);

    TManagerCameras getManagerCameras(@Param("vo") TManagerCameras vo);

    
}
