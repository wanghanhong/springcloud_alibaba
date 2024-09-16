package com.smart.device.access.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.device.access.mapper.TDeviceProductMapper;
import com.smart.device.access.service.ITDeviceProductService;
import com.smart.device.common.access.entity.TDeviceProduct;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
* @author
*/
@Service
public class TDeviceProductServiceImpl extends ServiceImpl<TDeviceProductMapper,TDeviceProduct> implements ITDeviceProductService {

    @Resource
    private TDeviceProductMapper tDeviceProductMapper;

    @Override
    public List<TDeviceProduct> queryProductList() {
        List<TDeviceProduct> list = tDeviceProductMapper.queryProductList();
        return list;
    }

}
