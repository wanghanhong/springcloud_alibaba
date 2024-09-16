package com.smart.brd.manage.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.brd.manage.base.common.exception.CustomException;
import com.smart.brd.manage.base.entity.*;
import com.smart.brd.manage.base.mapper.*;
import com.smart.brd.manage.base.service.DictListService;
import com.smart.brd.manage.base.service.ITVaccinePreventionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.page.PageDomain;
import javax.annotation.Resource;
import com.smart.common.utils.IdWorker;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author
 */
@Service
public class TVaccinePreventionServiceImpl extends ServiceImpl<TVaccinePreventionMapper, TVaccinePrevention> implements ITVaccinePreventionService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TVaccinePreventionMapper tVaccinePreventionMapper;
    @Resource
    private TVaccineRecordMapper tVaccineRecordMapper;
    @Resource
    private TLivestockMapper tLivestockMapper;
    @Resource
    private TVaccineMapper tVaccineMapper;
    @Resource
    private TDiseaseMapper tDiseaseMapper;
    @Resource
    private DictListService dictListService;


    @Override
    public IPage<TVaccinePrevention> tVaccinePreventionList(PageDomain page, TVaccinePrevention vo) {
        Page<TVaccinePrevention> pg = new Page<>(page.getPageNum(), page.getPageSize());
        return tVaccinePreventionMapper.tVaccinePreventionList(pg,vo);
    }

    @Override
    @Transactional
    public TVaccinePrevention tVaccinePreventionAdd(TVaccinePrevention entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        entity.setPreventionId(id);
        TDisease tDisease = tDiseaseMapper.selectById(entity.getDiseaseId());
        if (Objects.nonNull(tDisease)){
            entity.setDiseaseName(tDisease.getDiseaseName());
        }
        entity.setCreateTime(LocalDateTime.now());
        entity.setSuitable(dictListService.typeListToType(entity.getSuitableList()));
        entity.setSuitableName(dictListService.typeListToName("dict_type_1",entity.getSuitableList()));
        this.save(entity);
        return entity;
    }

    @Override
    public int tVaccinePreventionDel(Long id) {
        TVaccinePrevention tDrug = tVaccinePreventionMapper.selectById(id);
        tDrug.setDeleteFlag(1);
        return tVaccinePreventionMapper.updateById(tDrug);
    }

    @Override
    @Transactional
    public TVaccinePrevention tVaccinePreventionUpdate(TVaccinePrevention entity) {
        List<String> recordList = entity.getRecordList();
        if (Objects.nonNull(recordList)){
            for (String s : recordList) {
                QueryWrapper<TVaccineRecord> wrapper=new QueryWrapper<>();
                wrapper.eq("livestock_id",s);
                List<TVaccineRecord> tVaccineRecords = tVaccineRecordMapper.selectList(wrapper);
                if (!tVaccineRecords.isEmpty()){
                    throw new CustomException("所选的动物已防疫！");
                }
                TLivestock tLivestock = tLivestockMapper.selectById(s);
                if (Objects.nonNull(tLivestock)){
                    TVaccineRecord tVaccineRecord=new TVaccineRecord();
                    tVaccineRecord.setPreventionId(entity.getPreventionId());
                    tVaccineRecord.setLivestockId(tLivestock.getLivestockId());
                    tVaccineRecord.setDeleteFlag(0);
                    tVaccineRecord.setCreateTime(LocalDateTime.now());
                    tVaccineRecord.setBedName(tLivestock.getBedName());
                    tVaccineRecord.setShedName(tLivestock.getShedName());
                    tVaccineRecord.setBirthDate(tLivestock.getBirthDate());
                    tVaccineRecord.setEntryDate(tLivestock.getEntryDate());
                    tVaccineRecord.setSuitable(tLivestock.getSuitable());
                    tVaccineRecord.setDeviceCode(tLivestock.getDeviceCode());
                    tVaccineRecord.setVarieties(tLivestock.getVarieties());
                    tVaccineRecord.setDeptId(tLivestock.getDeptId());
                    tVaccineRecordMapper.insert(tVaccineRecord);
                }
            }
            return null;
        }else {
//            BigDecimal unitPrice = tVaccineMapper.selectById(entity.getVaccineId()).getUnitPrice();
            entity.setSuitable(dictListService.typeListToType(entity.getSuitableList()));
            entity.setSuitableName(dictListService.typeListToName("dict_type_1",entity.getSuitableList()));
//            entity.setCost(unitPrice.multiply(new BigDecimal(entity.getDosage())));
        }
        TDisease tDisease = tDiseaseMapper.selectById(entity.getDiseaseId());
        if (Objects.nonNull(tDisease)){
            entity.setDiseaseName(tDisease.getDiseaseName());
        }
        tVaccinePreventionMapper.updateById(entity);
        return entity;
    }

    @Override
    public TVaccinePrevention tVaccinePreventionDetail(TVaccinePrevention entity) {
        TVaccinePrevention detail = tVaccinePreventionMapper.selectById(entity.getPreventionId());
        TVaccine tVaccine = tVaccineMapper.selectById(detail.getVaccineId());
        detail.setVaccineName(tVaccine.getVaccineName());
        detail.setManufacturer(tVaccine.getManufacturer());
        detail.setSupplierName(tVaccine.getSupplierName());
//        detail.setUnitPrice(tVaccine.getUnitPrice());
        List<Integer> su=new ArrayList<>();
        String[] split = detail.getSuitable().split(",");
        for (String s : split) {
            su.add(Integer.parseInt(s));
        }
        detail.setSuitableList(su);
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
