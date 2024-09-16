package com.smart.device.access.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.access.entity.TDeviceDict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
* @author 
*/
@Mapper
public interface TDeviceDictMapper extends BaseMapper<TDeviceDict> {
    //    定时器用，会查找 所有的设备信息
    List<TDeviceDict> queryDeviceTypeALL();
    //  查询所有类型大类
    List<TDeviceDict> queryDeviceParentTypeList();
    //  查询所有类型-详细
    List<TDeviceDict> queryDeviceTypeList();
    TDeviceDict getDictByType(@Param("deviceType") Integer deviceType);




    String getModelType(@Param("deviceModel") String deviceModel);

    List<TDeviceDict> getProjectId(@Param("type") Integer type);

    TDeviceDict getDictLimit(@Param("deviceType") Integer deviceType,
                                   @Param("deviceModel") String deviceModel);

    TDeviceDict getDictBydeviceType(@Param("deviceType") Integer deviceType);



}
