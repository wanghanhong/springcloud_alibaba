package com.smart.card.manage.mapper;

import com.smart.card.manage.entity.TCardHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TCardHistoryMapper extends BaseMapper<TCardHistory> {

    IPage<TCardHistory> tCardHistoryList(Page<TCardHistory> page, @Param("vo")TCardHistory vo);


}
