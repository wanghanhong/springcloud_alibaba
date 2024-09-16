package com.smart.device.install.service.area.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.device.common.install.entity.TBaseRegion;
import com.smart.device.common.install.entity.area.BsStreet;
import com.smart.device.install.mapper.area.BsStreetMapper;
import com.smart.device.install.service.area.IBsStreetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author f
 */
@Service
public class BsStreetServiceImpl extends ServiceImpl<BsStreetMapper, BsStreet> implements IBsStreetService {

    @Resource
    private BsStreetMapper bsStreetMapper;

    @Override
    public List<TBaseRegion> queryStreetsByCode(String code) {
        return bsStreetMapper.queryStreetsByCode(code);
    }
    
}
