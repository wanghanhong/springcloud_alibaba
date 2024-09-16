package com.smart.device.access.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.access.entity.TDeviceProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
* @author 
*/
@Mapper
public interface TDeviceProductMapper extends BaseMapper<TDeviceProduct> {

    List<TDeviceProduct> queryProductList();





    String getProductType(@Param("productName") String productName);

    Long getProductId(@Param("productName") String productName);


}
