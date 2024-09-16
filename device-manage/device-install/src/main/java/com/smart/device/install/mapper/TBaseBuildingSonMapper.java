package com.smart.device.install.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.install.entity.TBaseBuildingSon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author f
 */
public interface TBaseBuildingSonMapper extends BaseMapper<TBaseBuildingSon> {

    // 根据 建筑物和楼层 查询某一层的设备。
    List<TBaseBuildingSon> selectBaseBuildingSon(@Param("buildingId")Long buildingId,@Param("floorNum") Integer floorNum);


}
