package com.smart.brd.manage.base.service.area.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.brd.manage.base.entity.area.BsVillage;
import com.smart.brd.manage.base.entity.area.TBaseRegion;
import com.smart.brd.manage.base.mapper.area.BsVillageMapper;
import com.smart.brd.manage.base.service.area.IBsVillageService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author f
 */
@Service
public class BsVillageServiceImpl extends ServiceImpl<BsVillageMapper, BsVillage> implements IBsVillageService{

    @Resource
    private BsVillageMapper bsVillageMapper;

    @Override
    public List<TBaseRegion> queryVillagesByCode(String code) {
        return bsVillageMapper.queryVillagesByCode(code);
    }

}
