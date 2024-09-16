package com.smart.device.install.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.smart.common.core.page.PageDomain;
import com.smart.common.utils.IdWorker;
import com.smart.common.utils.StringUtils;
import com.smart.common.utils.bean.BeanUtils;
import com.smart.device.common.install.entity.TBaseCompany;
import com.smart.device.common.install.entity.TBaseFirefighter;
import com.smart.device.install.entity.vo.Dept;
import com.smart.device.install.mapper.TBaseBuildingMapper;
import com.smart.device.install.mapper.TBaseCompanyMapper;
import com.smart.device.install.service.ITBaseCompanyService;
import com.smart.device.install.service.ITBaseRegionService;
import com.smart.device.install.service.inspection.ITBaseFirefighterService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author f
 */
@Service
public class TBaseCompanyServiceImpl extends ServiceImpl<TBaseCompanyMapper, TBaseCompany> implements ITBaseCompanyService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TBaseCompanyMapper TBaseCompanyMapper;
    @Resource
    private ITBaseRegionService tBaseRegionService;
    @Resource
    private ITBaseFirefighterService itBaseFirefighterService;
    @Resource
    private TBaseBuildingMapper tBaseBuildingMapper;

    @Override
    public IPage<TBaseCompany> baseCompanyList(PageDomain page, TBaseCompany vo) {
        Page<TBaseCompany> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TBaseCompany> iPage = TBaseCompanyMapper.baseCompanyList(pg,vo);
        String codes =  tBaseRegionService.getRegionsByTDeptInfoVo(iPage.getRecords());
        Map<String,String> map = tBaseRegionService.mapRegions(codes);
        List<TBaseCompany> list = iPage.getRecords();
        for (int i=0;i<list.size();i++){
            TBaseCompany ebean = list.get(i);
            list.get(i).setAreaName(getStringAreaName(ebean, map));

        }
        iPage.setRecords(list);
        return iPage;
    }

    @Override
    public TBaseCompany baseCompanyAdd(TBaseCompany entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        //生成id
        entity.setId(idWorker.nextId());
        entity.setCreateTime(LocalDateTime.now());
        entity.setInlineState(2);
        TBaseCompanyMapper.insert(entity);
        saveFirefighter(entity);
        // 保存单位到 系统部门表中。
        saveDept(entity);
        return entity;
    }

    public void saveFirefighter(TBaseCompany entity){
        try {
            TBaseFirefighter firefighter = itBaseFirefighterService.selectBaseFirefighter(new TBaseFirefighter(entity.getInchargePhone()));
            if(firefighter == null || firefighter.getId() == null){
                itBaseFirefighterService.baseFirefighterAdd(new TBaseFirefighter(3,entity.getInchargeName(),entity.getInchargeCardid(),entity.getInchargePhone()));
            }else{
                itBaseFirefighterService.baseFirefighterUpdate(new TBaseFirefighter(firefighter.getId(),3,entity.getInchargeName(),entity.getInchargeCardid(),entity.getInchargePhone()));
            }
            itBaseFirefighterService.checkUserAndSave(entity.getInchargePhone(),entity.getId());
            firefighter = itBaseFirefighterService.selectBaseFirefighter(new TBaseFirefighter(entity.getManagerPhone()));
            if(firefighter == null || firefighter.getId() == null){
                itBaseFirefighterService.baseFirefighterAdd(new TBaseFirefighter(3,entity.getManagerName(),entity.getManagerCardid(),entity.getManagerPhone()));
            }else{
                itBaseFirefighterService.baseFirefighterUpdate(new TBaseFirefighter(firefighter.getId(),3,entity.getManagerName(),entity.getManagerCardid(),entity.getManagerPhone()));
            }
            itBaseFirefighterService.checkUserAndSave(entity.getManagerPhone(),entity.getId());
            firefighter = itBaseFirefighterService.selectBaseFirefighter(new TBaseFirefighter(entity.getParttimePhone()));
            if(firefighter == null || firefighter.getId() == null){
                itBaseFirefighterService.baseFirefighterAdd(new TBaseFirefighter(3,entity.getParttimeName(),entity.getParttimeCardid(),entity.getParttimePhone()));
            }else{
                itBaseFirefighterService.baseFirefighterUpdate(new TBaseFirefighter(firefighter.getId(),3,entity.getParttimeName(),entity.getParttimeCardid(),entity.getParttimePhone()));
            }
            itBaseFirefighterService.checkUserAndSave(entity.getParttimePhone(),entity.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public int baseCompanyDel(Long id) {
        int countx = TBaseCompanyMapper.queryCompanyCanDel(id);
        int res = 0;
        if(countx > 0){
        }else{
            TBaseCompanyMapper.deleteById(id);
            Dept dept = new Dept();
            dept.setDeptId(id);
            tBaseCompanyMapper.deleteSysDept(dept);
            res = 1;
        }
        return res;
    }

    @Override
    public TBaseCompany baseCompanyUpdate(TBaseCompany entity) {
        TBaseCompany company = TBaseCompanyMapper.selectById(entity.getId());
        BeanUtils.copyBeanProp(company,entity);

        company.setUpdateTime(LocalDateTime.now());
        TBaseCompanyMapper.updateById(company);
        saveFirefighter(entity);
        // 保存单位到 系统部门表中。
        saveDept(company);
        try {
            if(company.getCompanyName().equals(entity.getCompanyName())){
                tBaseBuildingMapper.updateBuildingCompanyName(company.getId(),company.getCompanyName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return company;
    }

    @Override
    public TBaseCompany selecBaseCompanyByWhree(TBaseCompany vo) {
        TBaseCompany entity = TBaseCompanyMapper.selectByWhere(vo);
        if(entity != null){
            String codes =  selectCodes(entity);
            Map<String,String> map = tBaseRegionService.mapRegions(codes);
            entity.setAreaName(getStringAreaName(entity, map));
            setPersonList(entity);
        }
        return entity;
    }

    private String getStringAreaName(TBaseCompany entity, Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        if (map.get(entity.getProvince()) != null) {
            sb.append(map.get(entity.getProvince()));
        }
        if (map.get(entity.getCity()) != null) {
            sb.append("/" + map.get(entity.getCity()));
        }
        if (map.get(entity.getCounty()) != null) {
            sb.append("/" + map.get(entity.getCounty()));
        }
        if (map.get(entity.getTown()) != null) {
            sb.append("/" + map.get(entity.getTown()));
        }
        return sb.toString();
    }

    public void setPersonList(TBaseCompany entity) {
        List<TBaseFirefighter> personList = new ArrayList<TBaseFirefighter>();
        TBaseFirefighter f = new TBaseFirefighter();
        TBaseFirefighter firefighter = itBaseFirefighterService.selectBaseFirefighter(new TBaseFirefighter(entity.getInchargePhone()));
        Map<Long,Long> phoneMap = new HashMap<>();
        if(firefighter != null){
            phoneMap.put(firefighter.getId(),firefighter.getId());
            f.setId(firefighter.getId());
            f.setName(entity.getInchargeName());
            f.setPhone(entity.getInchargePhone());
            personList.add(f);
        }
        firefighter = itBaseFirefighterService.selectBaseFirefighter(new TBaseFirefighter(entity.getManagerPhone()));
        if(firefighter != null){
            if(!phoneMap.containsKey(firefighter.getId())){
                phoneMap.put(firefighter.getId(),firefighter.getId());
                f = new TBaseFirefighter();
                f.setId(firefighter.getId());
                f.setName(entity.getManagerName());
                f.setPhone(entity.getManagerPhone());
                personList.add(f);
            }
        }
        firefighter = itBaseFirefighterService.selectBaseFirefighter(new TBaseFirefighter(entity.getParttimePhone()));
        if(firefighter != null){
            if(!phoneMap.containsKey(firefighter.getId())){
                f = new TBaseFirefighter();
                f.setId(firefighter.getId());
                f.setName(entity.getParttimeName());
                f.setPhone(entity.getParttimePhone());
                personList.add(f);
            }
        }
        entity.setPersonList(personList);
    }
    /**------通用方法开始结束-----------------------------------------*/
    public String selectCodes(TBaseCompany ebean) {
        String str = "";
        StringBuilder sb = new StringBuilder();
        if(StringUtils.isNotBlank(ebean.getProvince())){
            sb.append(ebean.getProvince());
        }
        if(StringUtils.isNotBlank(ebean.getCity())){
            sb.append(","+ebean.getCity());
        }
        if(StringUtils.isNotBlank(ebean.getCounty())){
            sb.append(","+ebean.getCounty());
        }
        if(StringUtils.isNotBlank(ebean.getTown())){
            sb.append(","+ebean.getTown());
        }
        str = sb.toString();
        if(str.endsWith(",")){
            str = str.substring(0,str.length()-1);
        }
        return str;
    }

    @Resource
    private TBaseCompanyMapper tBaseCompanyMapper;

    @Override
    public List<TBaseCompany> selectCompanys(TBaseCompany vo) {
        List<TBaseCompany> list =  tBaseCompanyMapper.selectCompanys(vo);
        return list;
    }


    public void saveDept(TBaseCompany vo) {
        Dept dept = new Dept();
        dept.setDeptId(vo.getId());
        dept.setParentId(vo.getParentId());
        dept.setDeptName(vo.getCompanyName());
        try {
            dept.setDeptCode(vo.getCompanyCode());
            dept.setDeptAddress(vo.getAddress());
            dept.setLongitude(vo.getLongitude());
            dept.setLatitude(vo.getLatitude());

            dept.setProvince(vo.getProvince());
            dept.setCity(vo.getCity());
            dept.setCounty(vo.getCounty());
            dept.setTown(vo.getTown());
        }catch (Exception e){
            e.printStackTrace();
        }
        log.error("zz-部门"+JSONObject.toJSONString(dept));
        Dept entity = tBaseCompanyMapper.selectSysDeptBy(dept);
        if(entity != null && entity.getDeptId() != null){
            tBaseCompanyMapper.updateSysDept(dept);
        }else{
            tBaseCompanyMapper.insertSysDeptBy(dept);
        }
    }


}
