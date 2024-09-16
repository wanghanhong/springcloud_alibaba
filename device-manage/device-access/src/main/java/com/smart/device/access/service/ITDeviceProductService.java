package com.smart.device.access.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.device.common.access.entity.TDeviceProduct;
import java.util.List;

public interface ITDeviceProductService extends IService<TDeviceProduct> {

    List<TDeviceProduct> queryProductList();


}
