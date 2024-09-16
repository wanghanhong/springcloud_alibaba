package com.smart.device.install.service.area.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.device.common.install.entity.TBaseRegion;
import com.smart.device.common.install.entity.area.BsVillage;
import com.smart.device.install.mapper.area.BsVillageMapper;
import com.smart.device.install.service.area.IBsVillageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author f
 */
@Service
public class BsVillageServiceImpl extends ServiceImpl<BsVillageMapper, BsVillage> implements IBsVillageService {

    @Resource
    private BsVillageMapper bsVillageMapper;

    @Override
    public List<TBaseRegion> queryVillagesByCode(String code) {
        return bsVillageMapper.queryVillagesByCode(code);
    }

}
