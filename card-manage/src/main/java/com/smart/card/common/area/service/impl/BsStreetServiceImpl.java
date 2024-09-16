package com.smart.card.common.area.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.card.common.area.entity.BsRegion;
import com.smart.card.common.area.entity.BsStreet;
import com.smart.card.common.area.mapper.BsStreetMapper;
import com.smart.card.common.area.service.IBsStreetService;
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
    public List<BsRegion> queryStreetsByCode(String code) {
        return bsStreetMapper.queryStreetsByCode(code);
    }
    
}
