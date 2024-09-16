package com.smart.brd.sys.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.brd.sys.common.domain.FebsConst;
import com.smart.brd.sys.common.domain.QueryRequest;
import com.smart.brd.sys.common.domain.Tree;
import com.smart.brd.sys.common.utils.SortUtil;
import com.smart.brd.sys.common.utils.TreeUtil;
import com.smart.brd.sys.system.dao.DeptMapper;
import com.smart.brd.sys.system.dao.DeptMapperEx;
import com.smart.brd.sys.system.domain.po.Dept;
import com.smart.brd.sys.system.domain.po.DeptEx;
import com.smart.brd.sys.system.service.DeptService;
import com.smart.brd.sys.system.service.DeptServiceEx;
import com.smart.brd.sys.system.service.UserService;
import com.smart.brd.sys.system.usertoken.entity.UserBean;
import com.smart.brd.sys.system.usertoken.service.UserTokenService;
import com.smart.common.utils.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@Service("deptServiceEx")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeptServiceImplEx extends ServiceImpl<DeptMapperEx, DeptEx> implements DeptServiceEx {

    @Resource
    private DeptMapperEx deptMapperEx;
    @Resource
    private UserTokenService userTokenService;
    @Resource
    private UserService userService;

    @Override
    public Map<String, Object> findDepts(HttpServletRequest request, QueryRequest queryRequest, DeptEx dept) {
        Map<String, Object> result = new HashMap<>(16);
        try {
            Page<Dept> pg = new Page<>(queryRequest.getPageNum(), queryRequest.getPageSize());
            UserBean userbean = userTokenService.getUserByToken(request);
            String deptIds = userService.getDeptIds(userbean.getDeptId());
            dept.setDeptIds(deptIds);

            IPage<DeptEx> iPage = deptMapperEx.findDeptPage(pg,dept);

            List<DeptEx> depts = iPage.getRecords();

            List<Tree<DeptEx>> trees = new ArrayList<>();
            buildTrees(trees, depts);
            Tree<DeptEx> deptTree = TreeUtil.build_Dept(trees,userbean.getDeptId());
            result.put("depts", deptTree);
            result.put("rows", depts);
            result.put("total", iPage.getTotal());
        } catch (Exception e) {
            log.error("获取部门列表失败", e);
            result.put("rows", null);
            result.put("total", 0);
        }
        return result;
    }

    @Override
    public List<DeptEx> findDeptLists(HttpServletRequest request,QueryRequest queryRequest,DeptEx dept) {
        UserBean userBean = userTokenService.getUserByToken(request);
        QueryWrapper<DeptEx> queryWrapper = new QueryWrapper<>();
        if ( dept.getDeptId() != null ) {
            queryWrapper.lambda().eq(Dept::getDeptId, dept.getDeptId());
        }
        if (userBean.getDeptId() != null ) {
            queryWrapper.lambda().eq(DeptEx::getDeptId, userBean.getDeptId());
        }
        if (StringUtils.isNotBlank(dept.getDeptName())) {
            queryWrapper.lambda().like(DeptEx::getDeptName, dept.getDeptName());
        }
        if (StringUtils.isNotBlank(dept.getProvince())) {
            queryWrapper.lambda().eq(DeptEx::getProvince, dept.getProvince());
        }
        if (StringUtils.isNotBlank(dept.getCounty())) {
            queryWrapper.lambda().eq(DeptEx::getCity, dept.getCounty());
        }
        if (StringUtils.isNotBlank(dept.getTown())) {
            queryWrapper.lambda().eq(DeptEx::getTown, dept.getTown());
        }
        if (StringUtils.isNotBlank(dept.getCity())) {
            queryWrapper.lambda().eq(DeptEx::getCity, dept.getCity());
        }
        if (StringUtils.isNotBlank(dept.getCreateTimeFrom()) && StringUtils.isNotBlank(dept.getCreateTimeTo())) {
            queryWrapper.lambda()
                    .ge(DeptEx::getCreateTime, dept.getCreateTimeFrom())
                    .le(DeptEx::getCreateTime, dept.getCreateTimeTo());
        }
        SortUtil.handleWrapperSort(queryRequest, queryWrapper, "orderNum", FebsConst.ORDER_ASC, true);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDept(DeptEx dept) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();

        setParentDeptName(dept,id);
        dept.setDeptId(id);
        dept.setCreateTime(new Date());
        this.save(dept);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDept(DeptEx dept) {
        setParentDeptName(dept,0L);
        dept.setModifyTime(new Date());
        this.baseMapper.updateById(dept);
    }
    private void setParentDeptName(DeptEx dept,Long id) {
        Long parentId = dept.getParentId();
        if (parentId == null) {
            dept.setParentId(0L);
        }else{
            Dept parent = deptMapperEx.selectById(parentId);
            if(parent != null){
                dept.setParentIdName(parent.getDeptName());
            }
        }
        if(dept.getType() != null && dept.getType()==1){
            if(dept.getDeptId() != null){
                Long fieldId = deptMapperEx.selectBrdField(dept);
                if(fieldId != null){
                    deptMapperEx.updateBrdField(dept);
                }else{
                    dept.setDeptId(id);
                    deptMapperEx.insertBrdField(dept);
                }
            }else{
                dept.setDeptId(id);
                deptMapperEx.insertBrdField(dept);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDepts(String[] deptIds) {
        this.delete(Arrays.asList(deptIds));
        for (int i = 0; i < deptIds.length; i++) {
            if(deptIds[i] != null){
                deptMapperEx.deleteBrdField(Long.parseLong(deptIds[i]));
            }
        }
    }

    private void buildTrees(List<Tree<DeptEx>> trees, List<DeptEx> depts) {
        depts.forEach(dept -> {
            Tree<DeptEx> tree = new Tree<>();
            tree.setId(dept.getDeptId().toString());
            tree.setKey(tree.getId());
            tree.setParentId(String.valueOf(dept.getParentId()));
            tree.setText(dept.getDeptName());
            tree.setCreateTime(dept.getCreateTime());
            tree.setModifyTime(dept.getModifyTime());
            tree.setOrderNum(dept.getOrderNum());
            tree.setTitle(tree.getText());
            tree.setValue(tree.getId());
            trees.add(tree);
        });
    }

    private void delete(List<String> deptIds) {
        removeByIds(deptIds);
        LambdaQueryWrapper<DeptEx> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(DeptEx::getParentId, deptIds);
        List<DeptEx> depts = baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(depts)) {
            List<String> deptIdList = new ArrayList<>();
            depts.forEach(d -> deptIdList.add(String.valueOf(d.getDeptId())));
            this.delete(deptIdList);
        }
    }

    @Override
    public DeptEx getDeptIdByDeptName(String deptName) {
        return deptMapperEx.getDeptIdByDeptName(deptName);
    }

    @Override
    public IPage<DeptEx> listDept(QueryRequest queryRequest,DeptEx dept){
        try {
            LambdaQueryWrapper<DeptEx> queryWrapper = new LambdaQueryWrapper<>();
            //匹配类型名称
            queryWrapper.like(StringUtils.isNotBlank(dept.getDeptName()),DeptEx::getDeptName, dept.getDeptName());
            queryWrapper.orderByAsc(DeptEx::getDeptId);
            Page<DeptEx> page = new Page<>();
            SortUtil.handlePageSort(queryRequest, page, true);
            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取失败", e);
            return null;
        }
    }

    @Override
    public DeptEx selectById(Long deptId){
        DeptEx dept = baseMapper.selectById(deptId);
        return dept;
    }

    @Override
    public List<DeptEx> findDeptLists(DeptEx dept, QueryRequest request){
        QueryWrapper<DeptEx> queryWrapper = new QueryWrapper<>();
        if (dept.getDeptId() != null ) {
            queryWrapper.lambda().eq(Dept::getDeptId, dept.getDeptId());
        }
        if (dept.getType() != null ) {
            queryWrapper.lambda().eq(Dept::getType, dept.getType());
        }
        if (StringUtils.isNotBlank(dept.getDeptName())) {
            queryWrapper.lambda().like(Dept::getDeptName, dept.getDeptName());
        }
        SortUtil.handleWrapperSort(request, queryWrapper, "orderNum", FebsConst.ORDER_ASC, true);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<DeptEx> quetyDeptLists(DeptEx dept, QueryRequest request){
        List<DeptEx> list = deptMapperEx.queryDeptSons(dept);
        if(list == null || list.size() == 0){
            list = deptMapperEx.selectList(new LambdaQueryWrapper<DeptEx>().eq(Dept::getDeptId,dept.getDeptId()));
        }
        return list;
    }



}
