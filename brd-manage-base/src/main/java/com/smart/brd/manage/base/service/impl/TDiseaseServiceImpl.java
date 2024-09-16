package com.smart.brd.manage.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.brd.manage.base.common.exception.CustomException;
import com.smart.brd.manage.base.common.utils.ExcelUtils;
import com.smart.brd.manage.base.entity.*;
import com.smart.brd.manage.base.entity.vo.DiseaseVo;
import com.smart.brd.manage.base.mapper.TBaseDictMapper;
import com.smart.brd.manage.base.mapper.TDiseaseMapper;
import com.smart.brd.manage.base.mapper.TDiseaseSubMapper;
import com.smart.brd.manage.base.service.DictListService;
import com.smart.brd.manage.base.service.ITDiseaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.core.domain.Result;
import com.smart.common.utils.StringUtils;
import com.smart.common.utils.bean.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.page.PageDomain;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.smart.common.utils.IdWorker;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */
@Service
public class TDiseaseServiceImpl extends ServiceImpl<TDiseaseMapper, TDisease> implements ITDiseaseService{

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TDiseaseMapper tDiseaseMapper;
    @Resource
    private TBaseDictMapper tBaseDictMapper;
    @Resource
    private TDiseaseSubMapper tDiseaseSubMapper;
    @Resource
    private DictListService dictListService;

    @Override
    public IPage<TDisease> tDiseaseList(PageDomain page, TDisease vo) {
        Page<TDisease> pg = new Page<>(page.getPageNum(), page.getPageSize());
        if (StringUtils.isNotBlank(vo.getStartTime())) {
            vo.setStartTime(vo.getStartTime() + " 00:00:00");
        }
        if (StringUtils.isNotBlank(vo.getEndTime())) {
            vo.setEndTime(vo.getEndTime() + " 23:59:59");
        }
        IPage<TDisease> iPage = tDiseaseMapper.tDiseaseList(pg,vo);
        for(TDisease a:iPage.getRecords()){
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String str = df.format(a.getCreateTime());
            a.setLastTime(str);
        }
        return iPage;
    }

    @Override
    public TDisease tDiseaseAdd(DiseaseVo entityVo) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        //检查疾病种类唯一性
        Boolean flag = checkUnique(entityVo);
        if (!flag){
            throw new CustomException("该疾病种类已经添加");
        }
        TDisease entity = new TDisease();
//        TDisease update = tDiseaseMapper.getDiseaseByCode(entityVo.getDiseaseCode());
        BeanUtils.copyBeanProp(entity, entityVo);
        if(entity.getDiseaseId() == null) {
            entity.setDiseaseId(id);
//            entity.setDiseaseCode(entityVo.getDiseaseCode());
            entity.setCreateTime(LocalDateTime.now());
//            entity.setSuitable(dictListService.typeListToType(entityVo.getSuitableList()));
//            entity.setSuitableName(dictListService.typeListToName("dict_type_1",entityVo.getSuitableList()));
            this.save(entity);
//            if(save(entity)){
//                entityVo.setDiseaseId(entity.getDiseaseId());
//                setSub(entityVo.getSuitableList(), entityVo);
//            }
        }else {
//             entityVo.setDiseaseId(update.getDiseaseId());
             tDiseaseUpdate(entityVo);
        }
        return entity;
    }

    private Boolean checkUnique(DiseaseVo entityVo) {
        QueryWrapper<TDisease> wrapper = new QueryWrapper<>();
//        diseaseVoQueryWrapper.eq("diseaseCode",entityVo.getDiseaseCode());
        wrapper.eq("disease_name",entityVo.getDiseaseName());
        wrapper.eq("delete_flag",0);
        List<TDisease> tDiseases = tDiseaseMapper.selectList(wrapper);
        return tDiseases.size() == 0 ? true : false;
    }

    @Override
    public int tDiseaseDel(Long id) {
        TDisease tDisease = tDiseaseMapper.selectById(id);
        tDisease.setDeleteFlag(1);
        return tDiseaseMapper.updateById(tDisease);
    }

    @Override
    public TDisease tDiseaseUpdate(DiseaseVo entityVo) {
        TDisease entity = new TDisease();
        BeanUtils.copyBeanProp(entity, entityVo);
//        if (setSub(entityVo.getSuitableList(), entityVo)) {
//            entity.setSuitable(dictListService.typeListToType(entityVo.getSuitableList()));
//            entity.setSuitableName(dictListService.typeListToName("dict_type_1",entityVo.getSuitableList()));
//        }
        tDiseaseMapper.updateById(entity);
        return entity;
    }

    @Override
    public DiseaseVo tDiseaseDetail(TDisease entity) {
        TDisease detail = tDiseaseMapper.selectById(entity.getDiseaseId());
        DiseaseVo detailVo = new DiseaseVo();
        BeanUtils.copyBeanProp(detailVo, detail);
//        detailVo.setSuitableList(dictListService.typeToTypeList(detail.getSuitable()));
//        detailVo.setSuitable(dictListService.typeListToType(detailVo.getSuitableList()));
        return detailVo;
    }

    public Boolean setSub(List<Integer> types, DiseaseVo entityVo){
        IdWorker idWorker = new IdWorker(0, 0);
        if (types == null || types.isEmpty()) {
            return false;
        }
        List<Integer> type = new ArrayList<>(types);
        List<TDiseaseSub> typeOld = tDiseaseSubMapper.getDictList(entityVo.getDiseaseCode());
        List<Long> oldIds = new ArrayList<>();
        for (TDiseaseSub t:typeOld) {
            if (!type.contains(t.getDiseaseDict())) {
                oldIds.add(t.getId());
            }else {
                type.remove(t.getDiseaseDict());
            }
        }
        if (!oldIds.isEmpty()) {
            tDiseaseSubMapper.deleteBatchIds(oldIds);
        }
        if (!type.isEmpty()){
            for (Integer t:type) {
                TDiseaseSub sub = new TDiseaseSub();
                sub.setDiseaseCode(entityVo.getDiseaseCode());
                sub.setDiseaseDict(t);
                sub.setId(idWorker.nextId());
                if (tDiseaseSubMapper.insert(sub) <= 0) {
                    return false;
                }
            }
        }
        return true;
    }
    /**------通用方法开始结束-----------------------------------------*/

    @Override
    public List<TBaseDict> getType() {
        String dictType = "dict_type_1";
        QueryWrapper<TBaseDict> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_type_id", dictType);
        return tBaseDictMapper.selectList(wrapper);
    }

    @Override
    public List<TDisease> queryDiseaseList(HttpServletRequest request,TDisease entity) {
        LambdaQueryWrapper<TDisease> wrapper = new LambdaQueryWrapper<>();
//        if (entity.getDeptId() != null) {
//            wrapper.eq(TDisease::getDeptId, entity.getDeptId());
//        }
        wrapper.eq(TDisease::getDeleteFlag,0);
        return tDiseaseMapper.selectList(wrapper);
    }

    @Override
    public TDisease getDisease(HttpServletRequest request, TDisease entity) {
        LambdaQueryWrapper<TDisease> wrapper = new LambdaQueryWrapper<>();
//        if (entity.getDeptId() != null) {
//            wrapper.eq(TDisease::getDeptId, entity.getDeptId());
//        }
        wrapper.eq(TDisease::getDiseaseId,entity.getDiseaseId());
        wrapper.eq(TDisease::getDeleteFlag,0);
        TDisease tDisease = new TDisease();
        try {
            tDisease = tDiseaseMapper.selectOne(wrapper);
        }catch (Exception e){
            new Result(211,"该疾病名称重复",true);
        }
        return tDisease;
    }

    @Override
    public void exportTDisease(MultipartFile path) {
        List<TDisease> list = ExcelUtils.importExcel(path, 0, 1, TDisease.class);
        if(list.size()>1){
            list.forEach(s -> {
                IdWorker idWorker = new IdWorker(0, 0);
                Long id = idWorker.nextId();
                s.setDiseaseId(id);
                s.setCreateTime(LocalDateTime.now());
                tDiseaseMapper.insert(s);
            });
        }
    }


}
