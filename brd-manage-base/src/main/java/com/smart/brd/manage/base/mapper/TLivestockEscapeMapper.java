package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TLivestockEscape;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.brd.manage.base.entity.dto.EscapeDto;
import com.smart.brd.manage.base.entity.vo.EscapeVo;
import org.apache.ibatis.annotations.Param;
/**
 * @author 
 */

public interface TLivestockEscapeMapper extends BaseMapper<TLivestockEscape> {

    IPage<EscapeDto> tLivestockEscapeList(Page<EscapeVo> page, @Param("vo") EscapeVo vo);

    EscapeDto tLivestockEscapeDetail(@Param("vo") EscapeVo vo);

}
