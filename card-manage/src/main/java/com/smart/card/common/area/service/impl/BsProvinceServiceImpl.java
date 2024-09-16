package com.smart.card.common.area.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.card.common.area.entity.BsProvince;
import com.smart.card.common.area.entity.BsRegion;
import com.smart.card.common.area.mapper.BsProvinceMapper;
import com.smart.card.common.area.service.IBsProvinceService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author f
 */
@Service
public class BsProvinceServiceImpl extends ServiceImpl<BsProvinceMapper, BsProvince> implements IBsProvinceService {

    @Resource
    private BsProvinceMapper bsProvinceMapper;

    @Override
    public List<BsRegion> queryProvincesByCode(String code) {
        return bsProvinceMapper.queryProvincesByCode(code);
    }
    
}
