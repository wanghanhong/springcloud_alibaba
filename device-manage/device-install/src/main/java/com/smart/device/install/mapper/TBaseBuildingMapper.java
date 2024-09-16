package com.smart.device.install.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.device.common.install.entity.TBaseBuilding;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author f
 */
public interface TBaseBuildingMapper extends BaseMapper<TBaseBuilding> {

    IPage<TBaseBuilding> baseBuildingList(Page<TBaseBuilding> page, @Param("vo") TBaseBuilding vo);

    List<TBaseBuilding> selectBuildings(@Param("vo") TBaseBuilding vo);

    List<TBaseBuilding> baseBuildingAndSonList(@Param("companyId") Long companyId,@Param("buildingId") Long buildingId);

    Integer queryBuildCanDel(@Param("buildingId") Long buildingId);

    Integer updateBuildingCompanyName(@Param("companyId")Long companyId,@Param("companyName")String companyName);


}
