package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TBaseDict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.brd.manage.base.entity.dto.DictDto;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author
 */

public interface TBaseDictMapper extends BaseMapper<TBaseDict> {

    IPage<TBaseDict> tBaseDictList(Page<TBaseDict> page, @Param("vo")TBaseDict vo);

    List<DictDto> tBaseDictListNopage(@Param("dictTypeId")String dictTypeId);

    List<TBaseDict> findAll();

    int findMax();

    int selectIdByName(String type);

    String selectNameById(String dictId);
}
