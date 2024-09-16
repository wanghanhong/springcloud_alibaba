package com.smart.card.sys.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.card.sys.system.domain.po.Dept;
import org.apache.ibatis.annotations.Param;
import java.util.List;


public interface DeptMapper extends BaseMapper<Dept> {

    IPage<Dept> findDeptPage(Page page, @Param("vo") Dept dept);

    Dept getDeptIdByDeptName(String deptName);

    Long deptNameQuery(String deptName);

    // 查询下 本部门下的第一层部门，不包括子公司及以下。
    List<Dept> queryDeptSons(@Param("vo") Dept vo);


}