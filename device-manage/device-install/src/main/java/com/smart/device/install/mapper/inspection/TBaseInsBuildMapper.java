package com.smart.device.install.mapper.inspection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.device.common.install.entity.TBaseInsBuild;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author f
 */
public interface TBaseInsBuildMapper extends BaseMapper<TBaseInsBuild> {

    IPage<TBaseInsBuild> baseInsBuildList(Page<TBaseInsBuild> page, @Param("vo") TBaseInsBuild vo);

    List<TBaseInsBuild> insAndInsFloorByInsId(@Param("inspectionId")Long inspectionId);

    Map<Integer,Integer> getBuildCountGroupByStatus(@Param("inspectionId")Long inspectionId);


}
