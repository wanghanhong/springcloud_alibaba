package com.smart.card.common.area.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.card.common.area.entity.BsCounty;
import com.smart.card.common.area.entity.BsRegion;
import com.smart.card.common.area.mapper.BsCountyMapper;
import com.smart.card.common.area.service.IBsCountyService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author f
 */
@Service
public class BsCountyServiceImpl extends ServiceImpl<BsCountyMapper, BsCounty> implements IBsCountyService {

    @Resource
    private BsCountyMapper bsCountyMapper;

    @Override
    public List<BsRegion> queryCountysByCode(String code) {
        return bsCountyMapper.queryCountysByCode(code);
    }
    
}
