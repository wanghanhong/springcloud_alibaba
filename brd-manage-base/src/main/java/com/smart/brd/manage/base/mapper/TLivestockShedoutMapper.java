package com.smart.brd.manage.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.brd.manage.base.entity.TLivestockShedout;
import com.smart.brd.manage.base.entity.vo.ShedOutVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author junglelocal
 */
public interface TLivestockShedoutMapper extends BaseMapper<TLivestockShedout> {

    IPage<ShedOutVo> tLivestockShedoutList(Page<ShedOutVo> page, @Param("vo") ShedOutVo vo);

    int selectByOutId(Long id);

    ShedOutVo selectDetailById(Long shedoutId);
}
