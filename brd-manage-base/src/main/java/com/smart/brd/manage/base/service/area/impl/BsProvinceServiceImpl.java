package com.smart.brd.manage.base.service.area.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.brd.manage.base.entity.area.BsProvince;
import com.smart.brd.manage.base.entity.area.TBaseRegion;
import com.smart.brd.manage.base.mapper.area.BsProvinceMapper;
import com.smart.brd.manage.base.service.area.IBsProvinceService;
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
    public List<TBaseRegion> queryProvincesByCode(String code) {
        return bsProvinceMapper.queryProvincesByCode(code);
    }
    
}
