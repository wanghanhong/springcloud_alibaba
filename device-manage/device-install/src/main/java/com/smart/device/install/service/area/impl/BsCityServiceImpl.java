package com.smart.device.install.service.area.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.device.common.install.entity.TBaseRegion;
import com.smart.device.common.install.entity.area.BsCity;
import com.smart.device.install.mapper.area.BsCityMapper;
import com.smart.device.install.service.area.IBsCityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author f
 */
@Service
public class BsCityServiceImpl extends ServiceImpl<BsCityMapper, BsCity> implements IBsCityService {

    @Resource
    private BsCityMapper bsCityMapper;

    @Override
    public List<TBaseRegion> queryCitysByCode(String code) {
        return bsCityMapper.queryCitysByCode(code);
    }
}
