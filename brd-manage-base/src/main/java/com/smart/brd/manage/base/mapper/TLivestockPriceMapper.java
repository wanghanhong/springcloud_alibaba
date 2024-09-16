package com.smart.brd.manage.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.brd.manage.base.entity.TLivestockPrice;
import java.util.List;

/**
 * @author 
 */

public interface TLivestockPriceMapper extends BaseMapper<TLivestockPrice> {

    List<TLivestockPrice> tLivestockPriceList();


}
