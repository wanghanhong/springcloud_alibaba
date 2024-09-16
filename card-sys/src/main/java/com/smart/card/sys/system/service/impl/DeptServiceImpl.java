package com.smart.card.sys.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.card.sys.common.domain.FebsConst;
import com.smart.card.sys.common.domain.QueryRequest;
import com.smart.card.sys.common.domain.Tree;
import com.smart.card.sys.common.utils.SortUtil;
import com.smart.card.sys.common.utils.TreeUtil;
import com.smart.card.sys.system.dao.DeptMapper;
import com.smart.card.sys.system.domain.po.Dept;
import com.smart.card.sys.system.service.DeptService;
import com.smart.card.sys.system.service.UserService;
import com.smart.card.sys.system.usertoken.entity.UserBean;
import com.smart.card.sys.system.usertoken.service.UserTokenService;
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
@Service("deptService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    @Resource
    private DeptMapper deptMapper;
    @Resource
    private UserTokenService userTokenService;
    @Resource
    private UserService userService;

    @Override
    public Map<String, Object> findDepts(HttpServletRequest request, QueryRequest queryRequest, Dept dept) {
        Map<String, Object> result = new HashMap<>(16);
        try {
            Page<Dept> pg = new Page<>(queryRequest.getPageNum(), queryRequest.getPageSize());
            UserBean userbean = userTokenService.getUserByToken(request);
            String deptIds = userService.getDeptIds(userbean.getDeptId());
            dept.setDeptIds(deptIds);

            IPage<Dept> iPage = deptMapper.findDeptPage(pg,dept);

            List<Dept> depts = iPage.getRecords();

            List<Tree<Dept>> trees = new ArrayList<>();
            buildTrees(trees, depts);
            if (deptMapper.selectById(userbean.getDeptId()) == null) {
                userbean.setDeptId(null);
            }
            Tree<Dept> deptTree = TreeUtil.build_Dept(trees,userbean.getDeptId());
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
    public List<Dept> findDeptLists(HttpServletRequest request,QueryRequest queryRequest,Dept dept) {
        UserBean userBean = userTokenService.getUserByToken(request);
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        if ( dept.getDeptId() != null ) {
            queryWrapper.lambda().eq(Dept::getDeptId, dept.getDeptId());
        }
        if (userBean.getDeptId() != null ) {
            queryWrapper.lambda().eq(Dept::getDeptId, userBean.getDeptId());
        }
        if (StringUtils.isNotBlank(dept.getDeptName())) {
            queryWrapper.lambda().like(Dept::getDeptName, dept.getDeptName());
        }
        if (StringUtils.isNotBlank(dept.getProvince())) {
            queryWrapper.lambda().eq(Dept::getProvince, dept.getProvince());
        }
        if (StringUtils.isNotBlank(dept.getCounty())) {
            queryWrapper.lambda().eq(Dept::getCity, dept.getCounty());
        }
        if (StringUtils.isNotBlank(dept.getTown())) {
            queryWrapper.lambda().eq(Dept::getTown, dept.getTown());
        }
        if (StringUtils.isNotBlank(dept.getCity())) {
            queryWrapper.lambda().eq(Dept::getCity, dept.getCity());
        }
        if (StringUtils.isNotBlank(dept.getCreateTimeFrom()) && StringUtils.isNotBlank(dept.getCreateTimeTo())) {
            queryWrapper.lambda()
                    .ge(Dept::getCreateTime, dept.getCreateTimeFrom())
                    .le(Dept::getCreateTime, dept.getCreateTimeTo());
        }
        SortUtil.handleWrapperSort(queryRequest, queryWrapper, "orderNum", FebsConst.ORDER_ASC, true);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDept(Dept dept) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        dept.setDeptId(id);
        dept.setCreateTime(new Date());
        if(dept.getParentId() != null){
            Dept entity =  deptMapper.selectOne(new LambdaQueryWrapper<Dept>().eq(Dept::getDeptId,dept.getParentId()));
            dept.setParentIdName(entity.getDeptName());
        }
        this.save(dept);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDept(Dept dept) {
        dept.setModifyTime(new Date());
        if(dept.getDeptId() != null){
            // 查询parentid 为dept 的部门，然后将 当前的 父名字更改下
            List<Dept> list =  deptMapper.selectList(new LambdaQueryWrapper<Dept>().eq(Dept::getParentId,dept.getDeptId()));
            for(Dept vo:list){
                vo.setParentIdName(dept.getDeptName());
                deptMapper.updateById(vo);
            }
        }
        this.baseMapper.updateById(dept);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDepts(String[] deptIds) {
        this.delete(Arrays.asList(deptIds));
    }

    private void buildTrees(List<Tree<Dept>> trees, List<Dept> depts) {
        depts.forEach(dept -> {
            Tree<Dept> tree = new Tree<>();
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
        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Dept::getParentId, deptIds);
        List<Dept> depts = baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(depts)) {
            List<String> deptIdList = new ArrayList<>();
            depts.forEach(d -> deptIdList.add(String.valueOf(d.getDeptId())));
            this.delete(deptIdList);
        }
    }

    @Override
    public Dept getDeptIdByDeptName(String deptName) {
        return deptMapper.getDeptIdByDeptName(deptName);
    }

    @Override
    public IPage<Dept> listDept(QueryRequest queryRequest,Dept dept){
        try {
            LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
            //匹配类型名称
            queryWrapper.like(StringUtils.isNotBlank(dept.getDeptName()),Dept::getDeptName, dept.getDeptName());
            queryWrapper.orderByAsc(Dept::getDeptId);
            Page<Dept> page = new Page<>();
            SortUtil.handlePageSort(queryRequest, page, true);
            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取失败", e);
            return null;
        }
    }

    @Override
    public Dept selectById(Long deptId){
        Dept dept = baseMapper.selectById(deptId);
        return dept;
    }

    @Override
    public List<Dept> findDeptLists(Dept dept, QueryRequest request){
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
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
    public List<Dept> quetyDeptLists(Dept dept, QueryRequest request){
        List<Dept> list = deptMapper.queryDeptSons(dept);
        if(list == null || list.size() == 0){
            list = deptMapper.selectList(new LambdaQueryWrapper<Dept>().eq(Dept::getDeptId,dept.getDeptId()));
        }
        return list;
    }



}
