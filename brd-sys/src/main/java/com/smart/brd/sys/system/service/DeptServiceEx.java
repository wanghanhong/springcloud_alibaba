package com.smart.brd.sys.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.brd.sys.common.domain.QueryRequest;
import com.smart.brd.sys.system.domain.po.Dept;
import com.smart.brd.sys.system.domain.po.DeptEx;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface DeptServiceEx extends IService<DeptEx> {

    Map<String, Object> findDepts(HttpServletRequest request, QueryRequest queryRequest, DeptEx dept);

    List<DeptEx> findDeptLists(HttpServletRequest request,QueryRequest queryRequest,DeptEx dept);

    void createDept(DeptEx dept);

    void updateDept(DeptEx dept);

    void deleteDepts(String[] deptIds);

    DeptEx getDeptIdByDeptName(String deptName);

    IPage<DeptEx> listDept(QueryRequest queryRequest, @Param("dept") DeptEx dept);

    DeptEx selectById(Long deptId);

    List<DeptEx> findDeptLists(DeptEx dept, QueryRequest request);

    List<DeptEx> quetyDeptLists(DeptEx dept, QueryRequest request);

}
