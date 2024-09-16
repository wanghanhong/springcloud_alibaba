package com.smart.card.common.dict.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.card.common.dict.entity.DictDto;
import com.smart.card.common.dict.entity.TBaseDictType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 
 */
@Mapper
public interface TBaseDictTypeMapper extends BaseMapper<TBaseDictType> {

    IPage<TBaseDictType> tBaseDictTypeList(Page<TBaseDictType> page, @Param("vo") TBaseDictType vo);

    int findMax();

    List<TBaseDictType> getDictTypeList(@Param("dictTypeName")String dictTypeName);

}
