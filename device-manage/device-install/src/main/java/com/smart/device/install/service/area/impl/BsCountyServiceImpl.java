package com.smart.device.install.service.area.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.device.common.install.entity.TBaseRegion;
import com.smart.device.common.install.entity.area.BsCounty;
import com.smart.device.install.mapper.area.BsCountyMapper;
import com.smart.device.install.service.area.IBsCountyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author f
 */
@Service
public class BsCountyServiceImpl extends ServiceImpl<BsCountyMapper, BsCounty>
        implements IBsCountyService {

    @Resource
    private BsCountyMapper bsCountyMapper;

    @Override
    public List<TBaseRegion> queryCountysByCode(String code) {
        return bsCountyMapper.queryCountysByCode(code);
    }
    
}
