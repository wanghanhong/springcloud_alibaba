package com.smart.brd.manage.base.mapper;

import com.smart.brd.manage.base.entity.TBrdField;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.brd.manage.base.entity.vo.BrdFieldVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TBrdFieldMapper extends BaseMapper<TBrdField> {

    IPage<BrdFieldVo> tBrdFieldList(Page<BrdFieldVo> page, @Param("vo")TBrdField vo);

    Long selectSysDept(@Param("id")Long id);

    void insertSysDept(@Param("vo")TBrdField vo);

    void updateSysDept(@Param("vo")TBrdField vo);

    void deleteSysDept(@Param("id")Long id);

    List<Long> selectSysDeptSon(@Param("id")Long id);

    Long selectfieldNumber(@Param("county")String county);
}
