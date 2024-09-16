package com.smart.device.install.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.device.common.install.entity.TManagerElectric;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.install.entity.vo.DeviceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 
 */
//@Mapper
public interface TManagerElectricMapper extends BaseMapper<TManagerElectric> {


    List<TManagerElectric> selectElectric(@Param("vo") TManagerElectric vo);

    TManagerElectric getManagerElectric(@Param("vo") TManagerElectric vo);

    IPage<TManagerElectric> managerElectricList(Page<TManagerElectric> page, @Param("vo") TManagerElectric vo);

    List<DeviceVo> electricListNoPage(@Param("vo") DeviceVo vo);

    DeviceCompanyVo electricPerSonByDeviceId(@Param("deviceId")Long deviceId);
    DeviceCompanyVo electricUserByDeviceId(@Param("deviceId")Long deviceId);

    void updateElectricStatus(@Param("vo") TManagerElectric vo);

    Integer getElectricNumByFloor(@Param("buildingId") Long buildingId,@Param("buildingFloor") Integer buildingFloor);
}
