package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TCard;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.card.manage.entity.vo.TCardVo;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TCardMapper extends BaseMapper<TCard> {

    IPage<TCardVo> tCardList(Page<TCardVo> page, @Param("vo")TCard vo);

    TCard getCardByAcc(@Param("msisdn") Long acc);

    Long getIccidByAcc(@Param("msisdn") Long acc);
}
