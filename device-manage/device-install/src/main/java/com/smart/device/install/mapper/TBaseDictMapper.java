package com.smart.device.install.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.device.common.install.entity.TBaseDict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author f
 */
@Mapper
public interface TBaseDictMapper extends BaseMapper<TBaseDict> {

    List<TBaseDict> dictList(@Param("dictTypeId") String dictTypeId);

    TBaseDict getdictName(@Param("dictTypeId") String dictTypeId,@Param("dictId") String dictId);

}
