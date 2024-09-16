package com.smart.device.install.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.device.common.install.entity.TBasePic;
import org.apache.ibatis.annotations.Param;

/**
 * @author f
 */
public interface TBasePicMapper extends BaseMapper<TBasePic> {

    IPage<TBasePic> basePicList(Page<TBasePic> page, @Param("vo") TBasePic vo);

}
