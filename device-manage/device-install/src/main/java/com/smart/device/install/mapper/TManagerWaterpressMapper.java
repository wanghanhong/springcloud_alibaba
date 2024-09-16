package com.smart.device.install.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.device.common.install.entity.TManagerWaterpress;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.install.entity.vo.DeviceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author f
 */
//@Mapper
public interface TManagerWaterpressMapper extends BaseMapper<TManagerWaterpress> {

    IPage<TManagerWaterpress> managerWaterpressList(Page<TManagerWaterpress> page, @Param("vo") TManagerWaterpress vo);

    TManagerWaterpress getManagerWaterpress(@Param("vo") TManagerWaterpress vo);

    List<TManagerWaterpress> selectWaterpress(@Param("vo") TManagerWaterpress vo);

    List<DeviceVo> wterpressNoPage(@Param("vo") DeviceVo vo);

    DeviceCompanyVo waterpressPerSonByDeviceId(@Param("deviceId")Long deviceId);
    DeviceCompanyVo waterpressUserByDeviceId(@Param("deviceId")Long deviceId);

    void updateWaterpressStatus(@Param("vo") TManagerWaterpress vo);

    Integer getWaterpressNumByFloor(@Param("buildingId")Long buildingId,@Param("buildingFloor")Integer buildingFloor);
}
