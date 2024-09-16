package com.smart.brd.sys.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.brd.sys.system.domain.po.Dept;
import com.smart.brd.sys.system.domain.po.DeptEx;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptMapperEx extends BaseMapper<DeptEx> {

    IPage<DeptEx> findDeptPage(Page page, @Param("vo") DeptEx dept);

    DeptEx getDeptIdByDeptName(String deptName);

    Long deptNameQuery(@Param("deptName") String deptName,@Param("parentId") Long parentId);

    Long selectBrdField(@Param("vo") DeptEx vo);

    void insertBrdField(@Param("vo") DeptEx vo);

    void updateBrdField(@Param("vo") DeptEx vo);

    Integer deleteBrdField(Long deptId);

    // 查询下 本部门下的第一层部门，不包括子公司及以下。
    List<DeptEx> queryDeptSons(@Param("vo") DeptEx vo);


}
