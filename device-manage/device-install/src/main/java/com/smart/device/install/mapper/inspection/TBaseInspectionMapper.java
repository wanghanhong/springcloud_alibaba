package com.smart.device.install.mapper.inspection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.device.common.install.entity.TBaseInspection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author f
 */
public interface TBaseInspectionMapper extends BaseMapper<TBaseInspection> {

    IPage<TBaseInspection> baseInspectionList(Page<TBaseInspection> page, @Param("vo") TBaseInspection vo);

    List<TBaseInspection> insAndInsBuildsList(@Param("vo") TBaseInspection vo);

}
