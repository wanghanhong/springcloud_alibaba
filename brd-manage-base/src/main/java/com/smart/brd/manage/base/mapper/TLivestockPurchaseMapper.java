package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TLivestockPurchase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TLivestockPurchaseMapper extends BaseMapper<TLivestockPurchase> {

    IPage<TLivestockPurchase> tLivestockPurchaseList(Page<TLivestockPurchase> page, @Param("vo")TLivestockPurchase vo);


}
