package com.smart.device.access.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.access.entity.TDeviceProtocol;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
* @author 
*/
@Mapper
public interface TDeviceProtocolMapper extends BaseMapper<TDeviceProtocol> {

    List<TDeviceProtocol> queryProtocolList();





    String getTypeByName(@Param("protocol") String protocol);



}
