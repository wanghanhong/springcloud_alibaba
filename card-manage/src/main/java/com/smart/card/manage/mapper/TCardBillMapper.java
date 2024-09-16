package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TCardBill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 
 */

public interface TCardBillMapper extends BaseMapper<TCardBill> {

    IPage<TCardBill> tCardBillList(Page<TCardBill> page, @Param("vo")TCardBill vo);

    List<TCardBill> getBillByAcc(@Param("msisdn") Long acc,@Param("cycle") Integer cycle);
}
