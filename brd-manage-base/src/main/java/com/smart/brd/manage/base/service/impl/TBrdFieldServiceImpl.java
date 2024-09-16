package com.smart.brd.manage.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.brd.manage.base.common.constant.BrdConstant;
import com.smart.brd.manage.base.common.usertoken.entity.UserBean;
import com.smart.brd.manage.base.common.usertoken.service.UserTokenService;
import com.smart.brd.manage.base.entity.TBaseDict;
import com.smart.brd.manage.base.entity.TBrdField;
import com.smart.brd.manage.base.entity.vo.BrdFieldVo;
import com.smart.brd.manage.base.entity.vo.FileVo;
import com.smart.brd.manage.base.mapper.TBaseDictMapper;
import com.smart.brd.manage.base.mapper.TBrdFieldMapper;
import com.smart.brd.manage.base.service.ITBrdFieldService;
import com.smart.brd.manage.base.service.ITLivestockShedService;
import com.smart.brd.manage.base.service.area.ITBaseRegionService;
import com.smart.common.core.domain.Result;
import com.smart.common.core.page.PageDomain;
import com.smart.common.utils.IdWorker;
import com.smart.common.utils.StringUtils;
import com.smart.common.utils.bean.BeanUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TBrdFieldServiceImpl extends ServiceImpl<TBrdFieldMapper, TBrdField> implements ITBrdFieldService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TBrdFieldMapper tBrdFieldMapper;
    @Resource
    private TBaseDictMapper tBaseDictMapper;
    @Resource
    private ITBaseRegionService tBaseRegionService;
    @Resource
    private ITLivestockShedService tLivestockShedService;
    @Resource
    private UserTokenService userTokenService;

    @Override
    public IPage<BrdFieldVo> tBrdFieldList(HttpServletRequest request,PageDomain page, TBrdField vo) {
        Page<BrdFieldVo> pg = new Page<>(page.getPageNum(), page.getPageSize());
        UserBean userbean = userTokenService.getUserByToken(request);
        String deptIds = getDeptIds(userbean.getDeptId());
        vo.setDeptIds(deptIds);

        IPage<BrdFieldVo> iPage = tBrdFieldMapper.tBrdFieldList(pg,vo);
        String codes =  tBaseRegionService.getRegionsByTDeptInfoVo(iPage.getRecords());
        Map<String,String> map = tBaseRegionService.mapRegions(codes);
        List<BrdFieldVo> list = iPage.getRecords();
        for (BrdFieldVo ebean : list) {
            ebean.setArea(getStringAreaName(ebean, map));
            ebean.setTypeName(getListTypes(ebean.getType()));
        }
        iPage.setRecords(list);
        return iPage;
    }

    @Override
    public Result tBrdFieldAdd(BrdFieldVo entityVo) {
        IdWorker idWorker = new IdWorker(0, 0);
        if(entityVo.getFieldId() == null) {
              List<TBrdField> isexist = queryTBrdFieldList(entityVo);
              if(isexist != null && isexist.size() > 0){
                  return new Result(10001,"养殖场名字已存在。",true);
              }else{
                  TBrdField entity = new TBrdField();
                  queryFileName(entityVo);
                  BeanUtils.copyBeanProp(entity, entityVo);
                  entity.setFieldId(idWorker.nextId());
                  entity.setDeptId(entity.getFieldId());
                  entity.setFieldNumber(getNumber(entityVo));
                  entity.setCreateTime(LocalDateTime.now());
                  entity.setType(typeListToType(entityVo.getTypes()));
                  entity.setTypeName(setTypeNames(entityVo.getTypes()));
                  this.save(entity);
                  insertSysDept(entity);
                  return Result.SUCCESS(entity);
              }
        }else {
             tBrdFieldUpdate(entityVo);
        }
        return Result.SUCCESS();
    }

    private void queryFileName(BrdFieldVo entityVo){
        if(entityVo.getFile() != null ){
            String fileName = "";
            for (FileVo file:entityVo.getFile()){
                fileName += file.getName()+",";
            }
            if(fileName.endsWith(BrdConstant.COMMA)){
                fileName = fileName.substring(0,fileName.length()-1);
            }
            entityVo.setFileName(fileName);
        }
    }

    public void insertSysDept(TBrdField entity) {
        entity.setParentId(0L);
        entity.setType("0");
        tBrdFieldMapper.insertSysDept(entity);
    }


    @Override
    public Result tBrdFieldDel(Long id) {
        tBrdFieldMapper.deleteById(id);
        tBrdFieldMapper.deleteSysDept(id);
        return Result.SUCCESS();
    }

    @Override
    public Result tBrdFieldUpdate(BrdFieldVo entityVo) {
        List<TBrdField> isexist = queryTBrdFieldList(entityVo);
        if(isexist != null && isexist.size() > 0){
            return new Result(10001,"养殖场名字已存在。",true);
        }else{
            TBrdField entity = new TBrdField();
            queryFileName(entityVo);
            BeanUtils.copyBeanProp(entity, entityVo);
            if(entityVo.getTypes() != null){
                entity.setType(typeListToType(entityVo.getTypes()));
                entity.setTypeName(setTypeNames(entityVo.getTypes()));
            }
            if(entityVo.getFieldNumber() == null){
                entity.setFieldNumber(getNumber(entityVo));
            }
            tBrdFieldMapper.updateById(entity);
            Long id = tBrdFieldMapper.selectSysDept(entity.getFieldId());
            if(id != null){
                tBrdFieldMapper.updateSysDept(entity);
            }else{
                insertSysDept(entity);
            }
            return Result.SUCCESS(entity);
        }
    }

    @Override
    public BrdFieldVo tBrdFieldDetail(Long fieldId) {
        BrdFieldVo detailVo = new BrdFieldVo();
        TBrdField detail = tBrdFieldMapper.selectById(fieldId);
        BeanUtils.copyBeanProp(detailVo, detail);

        detailVo.setTypes(queryListTypes(detailVo.getType()));
        if(detailVo != null){
            String codes =  selectCodes(detailVo);
            Map<String,String> map = tBaseRegionService.mapRegions(codes);
            detailVo.setArea(getStringAreaName(detailVo, map));
        }
        return detailVo;
    }

    /**------通用方法开始结束-----------------------------------------*/
    // 将list<1,2,3> 变为 1,2,3  添加和修改时使用
    private String typeListToType(List<Integer> types) {
        StringBuilder t = new StringBuilder();
        for (Integer ty:types) {
            t.append(ty);
            t.append(",");
        }
        t.deleteCharAt(t.lastIndexOf(","));
        return t.toString();
    }
    // 将list<1,2,3> 变为 猪,牛,羊  添加和修改时使用
    private String setTypeNames(List<Integer> types){
        Map<Integer,String> map = getTypeMap();
        StringBuilder t = new StringBuilder();
        for (Integer type:types){
            t.append(map.get(type));
            t.append(",");
        }
        t.deleteCharAt(t.lastIndexOf(","));
        return t.toString();
    }
    // 将1,2,3   变为  list<1,2,3>  修改时使用
    private List<Integer> queryListTypes(String type){
        List<Integer> ty = new ArrayList<>();
        if(StringUtils.isNotBlank(type)){
            if (type.contains(BrdConstant.COMMA)) {
                String[] t = type.split(",");
                for (String s:t) {
                    ty.add(Integer.parseInt(s));
                }
            }else {
                ty.add(Integer.parseInt(type));
            }
        }
        return ty;
    }

    // 将1,2,3   变为  名称  列表时使用
    private String getListTypes(String type){
        StringBuilder ty = new StringBuilder();
        String dictTypeId = "dict_type_id";
        String dictType = "dict_type_0";
        if(StringUtils.isNotBlank(type)){
            QueryWrapper<TBaseDict> wrapper = new QueryWrapper<>();
            if (type.contains(BrdConstant.COMMA)) {
                String[] t = type.split(",");
                for (String s:t) {
                    wrapper.clear();
                    wrapper.eq(dictTypeId, dictType);
                    wrapper.eq("dict_id", s);
                    TBaseDict dict = tBaseDictMapper.selectOne(wrapper);
                    ty.append(dict.getDictName()).append(",");
                }
                ty.deleteCharAt(ty.lastIndexOf(","));
                return ty.toString();
            }else {
                wrapper.eq(dictTypeId, dictType);
                wrapper.eq("dict_id", type);
                TBaseDict dict = tBaseDictMapper.selectOne(wrapper);
                ty.append(dict.getDictName());
            }
        }
        return ty.toString();
    }

    private String getStringAreaName(BrdFieldVo entity, Map<String, String> map) {
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
    public String selectCodes(BrdFieldVo ebean) {
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
        if(str.endsWith(BrdConstant.COMMA)){
            str = str.substring(0,str.length()-1);
        }
        return str;
    }

    private Map<Integer,String> getTypeMap(){
        List<TBaseDict> dicts = getType();
        Map<Integer, String> map = new HashMap<>();
        try {
            map = dicts.stream().collect(Collectors.toMap(TBaseDict::getDictId,TBaseDict::getDictName,(key1, key2)->key2));
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public List<TBaseDict> getType(){
        String dictType = "dict_type_0";
        QueryWrapper<TBaseDict> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_type_id", dictType);
        return tBaseDictMapper.selectList(wrapper);
    }

    private Long getNumber(BrdFieldVo vo) {
        Long res = 0L;
        if(StringUtils.isNotBlank(vo.getCounty())){
            Long number = tBrdFieldMapper.selectfieldNumber(vo.getCounty());
            if(number != null){
                res = number+1;
            }else{
                number = 1L;
                String str = vo.getCounty() +new DecimalFormat("0000").format(number);
                res = Long.valueOf(str);
            }
        }
        return res;
    }

    public List<TBrdField> queryTBrdFieldList(BrdFieldVo entity) {
        LambdaQueryWrapper<TBrdField> wrapper = new LambdaQueryWrapper<>();
        if (entity.getFieldName() != null) {
            wrapper.eq(TBrdField::getFieldName, entity.getFieldName());
        }
        if (entity.getFieldId() != null) {
            wrapper.ne(TBrdField::getFieldId, entity.getFieldId());
        }
        wrapper.eq(TBrdField::getDeleteFlag,0);
        return tBrdFieldMapper.selectList(wrapper);
    }

    public String getDeptIds(Long id) {
        if (id==null){
            return "";
        }
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(id);
        addChildIds(id,stringBuffer);
        return new String(stringBuffer);
    }

    private void addChildIds(Long parentId,StringBuffer stringBuffer){
        List<Long> depts = tBrdFieldMapper.selectSysDeptSon(parentId);
        for(Long son:depts){
            stringBuffer.append(","+son);
            addChildIds(son,stringBuffer);
        }
    }


}
