package com.smart.brd.manage.base.service.area.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.brd.manage.base.entity.area.BsStreet;
import com.smart.brd.manage.base.entity.area.TBaseRegion;
import com.smart.brd.manage.base.mapper.area.BsStreetMapper;
import com.smart.brd.manage.base.service.area.IBsStreetService;
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
