package com.smart.device.access.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.access.entity.UserInfoType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 
*/
@Mapper
public interface TUserInfoTypeMapper extends BaseMapper<UserInfoType> {

    UserInfoType getDictByDeviceType(@Param("partId") String partId);

}
