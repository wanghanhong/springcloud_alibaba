package com.smart.brd.manage.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.brd.manage.base.entity.TLivestockOut;
import com.smart.brd.manage.base.entity.vo.ShedOutAllVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author junglelocal
 */
public interface TLivestockOutMapper extends BaseMapper<TLivestockOut> {

    IPage<TLivestockOut> tLivestockOutList(Page<TLivestockOut> page, @Param("vo") ShedOutAllVo vo);

}
