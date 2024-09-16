package com.smart.card.common.area.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.card.common.area.entity.BsRegion;
import com.smart.card.common.area.entity.BsVillage;
import com.smart.card.common.area.mapper.BsVillageMapper;
import com.smart.card.common.area.service.IBsVillageService;
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
    public List<BsRegion> queryVillagesByCode(String code) {
        return bsVillageMapper.queryVillagesByCode(code);
    }

}
