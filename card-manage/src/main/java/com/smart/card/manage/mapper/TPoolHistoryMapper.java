package com.smart.card.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.card.manage.entity.TPoolHistory;
import org.apache.ibatis.annotations.Param;

/**
 * @author jungle
 */
public interface TPoolHistoryMapper extends BaseMapper<TPoolHistory> {

    IPage<TPoolHistory> tPoolHistoryList(Page<TPoolHistory> page, @Param("vo")TPoolHistory vo);

}
