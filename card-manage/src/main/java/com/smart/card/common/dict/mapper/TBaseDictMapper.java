package com.smart.card.common.dict.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.card.common.dict.entity.DictDto;
import com.smart.card.common.dict.entity.TBaseDict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author
 */
@Mapper
public interface TBaseDictMapper extends BaseMapper<TBaseDict> {

    IPage<TBaseDict> tBaseDictList(Page<TBaseDict> page, @Param("vo") TBaseDict vo);

    List<DictDto> tBaseDictListNopage(@Param("dictTypeId") String dictTypeId);

    List<TBaseDict> findAll();

    int findMax();

    String selectNameById(String dictId);
}
