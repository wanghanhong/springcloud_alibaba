package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TLivestockDead;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.brd.manage.base.entity.vo.DeadVo;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TLivestockDeadMapper extends BaseMapper<TLivestockDead> {

    IPage<DeadVo> tLivestockDeadList(Page<DeadVo> page, @Param("vo") DeadVo vo);

    DeadVo tLivestockDeadDetail(@Param("vo") DeadVo vo);
}
