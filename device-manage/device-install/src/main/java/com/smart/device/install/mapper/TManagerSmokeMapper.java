package com.smart.device.install.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.device.common.install.entity.TManagerSmoke;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.install.entity.vo.DeviceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author f
 */
//@Mapper
public interface TManagerSmokeMapper extends BaseMapper<TManagerSmoke> {

    IPage<TManagerSmoke> managerSmokeList(Page<TManagerSmoke> page, @Param("vo") TManagerSmoke vo);

    TManagerSmoke getManagerSmoke(@Param("vo") TManagerSmoke vo);

    List<TManagerSmoke> selectSmoke(@Param("vo") TManagerSmoke vo);

    List<DeviceVo> smokeListNoPage(@Param("vo") DeviceVo vo);
    // 根据设备号、单位查询电话
    DeviceCompanyVo smokePerSonByDeviceId(@Param("deviceId")Long deviceId);
    // 根据设备号、人员查询电话
    DeviceCompanyVo smokeUserByDeviceId(@Param("deviceId")Long deviceId);

    void updateSmokeStatus(@Param("vo") TManagerSmoke vo);

    Integer getSmokeNumByFloor(@Param("buildingId") Long buildingId,@Param("buildingFloor") Integer buildingFloor);
}
