package com.smart.device.install.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.device.common.install.entity.TBaseFirehydrant;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author f
 */
public interface TBaseFirehydrantMapper extends BaseMapper<TBaseFirehydrant> {

    IPage<TBaseFirehydrant> baseFirehydrantList(Page<TBaseFirehydrant> page, @Param("vo") TBaseFirehydrant vo);

    List<TBaseFirehydrant> baseFirehydrantNoPage(@Param("vo") TBaseFirehydrant vo);

    Integer getFirehydrantNumByFloor(@Param("buildingId") Long buildingId, @Param("buildingFloor") Integer buildingFloor);

}
