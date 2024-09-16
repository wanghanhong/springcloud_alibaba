package com.smart.brd.manage.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.brd.manage.base.common.dict.DictCache;
import com.smart.brd.manage.base.common.exception.CustomException;
import com.smart.brd.manage.base.entity.*;
import com.smart.brd.manage.base.entity.vo.DrugDoseVo;
import com.smart.brd.manage.base.entity.vo.LivestockInfo;
import com.smart.brd.manage.base.mapper.*;
import com.smart.brd.manage.base.service.ITDiseaseTreatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.page.PageDomain;

import javax.annotation.Resource;

import com.smart.common.utils.IdWorker;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author
 */
@Service
public class TDiseaseTreatServiceImpl extends ServiceImpl <TDiseaseTreatMapper, TDiseaseTreat> implements ITDiseaseTreatService {

    /**
     * ------通用方法开始-----------------------------------------
     */
    @Resource
    private TDiseaseTreatMapper tDiseaseTreatMapper;
    @Resource
    private TLivestockMapper tLivestockMapper;
    @Resource
    private TDiseaseRecordMapper tDiseaseRecordMapper;
    @Resource
    private TLivestockDeadMapper tLivestockDeadMapper;
    @Resource
    private TDrugMapper tDrugMapper;
    @Resource
    private TDiseaseMapper tDiseaseMapper;


    @Override
    public IPage <TDiseaseTreat> tDiseaseTreatList(PageDomain page, TDiseaseTreat vo) {
        Page <TDiseaseTreat> pg = new Page <>(page.getPageNum(), page.getPageSize());
        if (StringUtils.isNotBlank(vo.getStartTime())) {
            vo.setStartTime(vo.getStartTime() + " 00:00:00");
        }
        if (StringUtils.isNotBlank(vo.getEndTime())) {
            vo.setEndTime(vo.getEndTime() + " 23:59:59");
        }
//        List <TDiseaseTreat> treatList = iPage.getRecords();
        /*注释价格相关*/
        /*for (TDiseaseTreat tDiseaseTreat : treatList) {
            //计算治疗成本和
            tDiseaseTreat.setTreatmentCostsSum(new BigDecimal(String.valueOf(tDiseaseTreatMapper.getTreatmentCostsSum(tDiseaseTreat.getId()))));
        }
        //过滤治疗成本
        List <TDiseaseTreat> newTreatList = new ArrayList <>();
        if (vo.getMinTreatmentCostsSum() != null && vo.getMaxTreatmentCostsSum() != null) {
            newTreatList = treatList.stream().filter(t -> Objects.nonNull(t.getTreatmentCostsSum()) && t.getTreatmentCostsSum().compareTo(new BigDecimal(vo.getMinTreatmentCostsSum()))>=0 && t.getTreatmentCostsSum().compareTo(new BigDecimal(vo.getMaxTreatmentCostsSum()))<=0 ).collect(Collectors.toList());
            pg.setRecords(newTreatList);
            pg.setTotal(newTreatList.size());
            return pg;
        }*/
        return tDiseaseTreatMapper.tDiseaseTreatList(pg, vo);

    }

    @Override
    @Transactional
    public TDiseaseTreat tDiseaseTreatAdd(TDiseaseTreat entity) {
        Long livestockId = entity.getLivestockId();
        TLivestock tLivestock = tLivestockMapper.selectById(livestockId);
        TDiseaseTreat tDiseaseTreat = tDiseaseTreatMapper.selectOne(new LambdaQueryWrapper<TDiseaseTreat>().eq(TDiseaseTreat::getDeleteFlag, 0).eq(TDiseaseTreat::getLivestockId, entity.getLivestockId()).eq(TDiseaseTreat::getTreatmentState, 127));
        if (tDiseaseTreat!=null){
            throw new CustomException(tLivestock.getDeviceCode()+"该耳标号正在治疗，无需重复添加");
        }
        tLivestock.setStatus("151");
        tLivestockMapper.updateById(tLivestock);
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        String symptom = entity.getSymptom();
        if (entity.getId() == null) {
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            TDisease tDisease = tDiseaseMapper.selectById(entity.getDiseaseId());
            entity.setDiseaseName(tDisease.getDiseaseName());
            entity.setTreatmentState("127");
//            String treatDateString = entity.getTreatDateString();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localTime = LocalDateTime.parse(entity.getTreatDateString(),df);
            entity.setTreatDate(localTime);
            this.save(entity);
        }
        List <DrugDoseVo> tDrugList = entity.getTDrugList();
        if (Objects.nonNull(tDrugList)) {
            for (DrugDoseVo tDrug : tDrugList) {
                TDiseaseRecord diseaseRecord = getTDiseaseRecord(entity, id, tDrug,symptom);
                diseaseRecord.setDiseaseName(tDiseaseMapper.selectById(entity.getDiseaseId()).getDiseaseName());
                diseaseRecord.setSuitableName(entity.getSuitableName());
                tDiseaseRecordMapper.insert(diseaseRecord);
            }
        }
        return entity;
    }

    private TDiseaseRecord getTDiseaseRecord(TDiseaseTreat entity, Long id, DrugDoseVo tDrug,String symptom) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long diseaseRecordId = idWorker.nextId();
        TDiseaseRecord diseaseRecord = new TDiseaseRecord();
        if (diseaseRecord.getId() == null) {
            diseaseRecord.setId(diseaseRecordId);
            diseaseRecord.setCreateTime(LocalDateTime.now());
        }
        diseaseRecord.setDose(tDrug.getDose());
        diseaseRecord.setDiseaseId(entity.getDiseaseId());
        diseaseRecord.setLivestockId(entity.getLivestockId());
        diseaseRecord.setDiseaseTreatId(id);
        TDrug tDrug1 = tDrugMapper.selectById(tDrug.getDrugId());
        diseaseRecord.setManufacturer(tDrug1.getManufacturer());
        diseaseRecord.setSupplierName(DictCache.getDict(tDrug1.getSupplierName()));
        diseaseRecord.setDrugId(tDrug1.getDrugId());
        diseaseRecord.setDrugName(tDrug1.getDrugName());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localTime = LocalDateTime.parse(entity.getTreatDateString(),df);
        diseaseRecord.setTreatmentTime(localTime);
        /*注释价格相关*/
        //diseaseRecord.setUnitPrice(tDrug1.getUnitPrice());
        diseaseRecord.setSymptom(symptom);
        diseaseRecord.setContentUnit(tDrug1.getContentUnit());
        /*注释价格相关*/
        //diseaseRecord.setTreatmentCosts(tDrug1.getUnitPrice().multiply(new BigDecimal(tDrug.getDose())));
        return diseaseRecord;
    }

    @Override
    public int tDiseaseTreatDel(Long id) {
        return tDiseaseTreatMapper.deleteById(id);
    }

    @Override
    @Transactional
    public TDiseaseTreat tDiseaseTreatUpdate(TDiseaseTreat entity) {

        entity.setTreatmentState("128");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localTime = LocalDateTime.parse(entity.getTreatDateString(),df);
        entity.setTreatDate(localTime);
        tDiseaseTreatMapper.updateById(entity);
        TDiseaseTreat tDiseaseTreat = tDiseaseTreatMapper.selectById(entity.getId());
        TLivestock tLivestock = tLivestockMapper.selectById(tDiseaseTreat.getLivestockId());
        tLivestock.setStatus("150");
        tLivestockMapper.updateById(tLivestock);
        return entity;
    }

    @Override
    public TDiseaseTreat tDiseaseTreatDetail(TDiseaseTreat entity) {
        return tDiseaseTreatMapper.selectById(entity.getId());
    }

    @Override
    public LivestockInfo liveStockInfo(String deviceCode) {
        //封装查询信息
        LivestockInfo livestockInfo = new LivestockInfo();
        //家畜信息
        TLivestock tLivestock = tLivestockMapper.selectOne(new QueryWrapper <TLivestock>().eq("device_code", deviceCode));
        //查询供应商信息
        if (Objects.nonNull(tLivestock)) {
            livestockInfo.setDeviceCode(deviceCode);
            livestockInfo.setLivestockId(tLivestock.getLivestockId());
            livestockInfo.setSuitable(tLivestock.getSuitable());
            livestockInfo.setShedName(tLivestock.getShedName());
            livestockInfo.setBedName(tLivestock.getBedName());
            livestockInfo.setEntryDate(tLivestock.getEntryDate());
            livestockInfo.setBirthDate(tLivestock.getBirthDate());
            livestockInfo.setCreditCode(tLivestock.getCreditCode());
            livestockInfo.setDeviceCode(tLivestock.getDeviceCode());
        }
        return livestockInfo;
    }

    @Override
    public TDiseaseTreat diseaseTreatInfo(Long id) {
//        String regEx = "[^0-9]";
//        Pattern p = Pattern.compile(regEx);
        TDiseaseTreat diseaseTreat = tDiseaseTreatMapper.diseaseTreatInfo(id);
        List <TDiseaseRecord> recordList = tDiseaseRecordMapper.selectRecordList(id);
        for (TDiseaseRecord tDiseaseRecord : recordList) {
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String date = String.valueOf(tDiseaseRecord.getTreatmentTime());
                String format = sdf2.format(sdf.parse(date));
                tDiseaseRecord.setTreatmentTimeString(format);
            }catch (Exception e){
                e.printStackTrace();
            }
            tDiseaseRecord.setSymptomName(DictCache.getDict(tDiseaseRecord.getSymptom()));
        }
//        for (TDiseaseRecord tDiseaseRecord : recordList) {
//            BigDecimal unitPrice = tDiseaseRecord.getUnitPrice();
//            Matcher m = p.matcher(tDiseaseRecord.getDose());
//            int dose = Integer.parseInt(m.replaceAll("").trim());
//            BigDecimal multiply = unitPrice.multiply(BigDecimal.valueOf(dose).setScale(2, BigDecimal.ROUND_HALF_UP));
//            tDiseaseRecord.setTreatmentCosts(multiply);
//        }
        return diseaseTreat.setTDiseaseRecordList(recordList);
    }

    @Override
    @Transactional
    public void updateDeath(TDiseaseTreat entity) {
        TDiseaseTreat tDiseaseTreat = tDiseaseTreatMapper.selectById(entity.getId());
        TLivestock tLivestock = tLivestockMapper.selectById(tDiseaseTreat.getLivestockId());
        tLivestock.setDeleteFlag(1);
        tLivestockMapper.updateById(tLivestock);
        //修改蓄病状态为死亡
        entity.setTreatmentState("129");
        tDiseaseTreatMapper.updateById(entity);
        //添加死亡记录
        TLivestockDead tLivestockDead = new TLivestockDead();
        tLivestockDead.setLivestockId(tLivestock.getLivestockId());
        tLivestockDead.setDeviceCode(entity.getDeviceCode());
        tLivestockDead.setDeadDate(LocalDate.from(entity.getDeathDate()));
        tLivestockDead.setDictId(entity.getDictId());
        tLivestockDead.setDictName(DictCache.getDict(entity.getDictId()));
        tLivestockDead.setDeleteFlag(0);
        IdWorker idWorker = new IdWorker(0, 0);
        Long livestockDeadId = idWorker.nextId();
        if (tLivestockDead.getId() == null) {
            tLivestockDead.setId(livestockDeadId);
            tLivestockDead.setCreateTime(LocalDateTime.now());
        }
        tLivestockDead.setDeptId(entity.getDeptId());
        tLivestockDeadMapper.insert(tLivestockDead);
    }

    @Override
    @Transactional
    public void addTreatRecord(TDiseaseTreat entity) {
        TDiseaseTreat tDiseaseTreat = new TDiseaseTreat();
        tDiseaseTreat.setId(entity.getId());
        DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.parse(entity.getTreatDateString(),d);
        tDiseaseTreat.setTreatDate(time);
        tDiseaseTreat.setDiseaseName(tDiseaseMapper.selectById(entity.getDiseaseId()).getDiseaseName());
        tDiseaseTreat.setDiseaseId(entity.getDiseaseId());
        tDiseaseTreatMapper.updateById(tDiseaseTreat);
        List <DrugDoseVo> drugList = entity.getTDrugList();
        if (drugList.size() > 0 && drugList != null) {
            for (DrugDoseVo tDrug : drugList) {
                TDiseaseRecord diseaseRecord = new TDiseaseRecord();
                diseaseRecord.setDiseaseTreatId(entity.getId());
                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime localTime = LocalDateTime.parse(entity.getTreatDateString(),df);
                diseaseRecord.setTreatmentTime(localTime);
                diseaseRecord.setLivestockId(entity.getLivestockId());
                diseaseRecord.setDose(tDrug.getDose());
                diseaseRecord.setSymptom(entity.getSymptom());
                diseaseRecord.setSuitableName(entity.getSuitableName());
                TDrug tDrug1 = tDrugMapper.selectById(tDrug.getDrugId());
                diseaseRecord.setManufacturer(tDrug1.getManufacturer());
                diseaseRecord.setSupplierName(DictCache.getDict(tDrug1.getSupplierName()));
                diseaseRecord.setDrugName(tDrug1.getDrugName());
                diseaseRecord.setDrugId(tDrug1.getDrugId());
                diseaseRecord.setDiseaseName(tDiseaseMapper.selectById(entity.getDiseaseId()).getDiseaseName());
                diseaseRecord.setDiseaseId(entity.getDiseaseId());
                /*注释价格相关*/
                //diseaseRecord.setUnitPrice(tDrug1.getUnitPrice());
                IdWorker idWorker = new IdWorker(0, 0);
                Long diseaseRecordId = idWorker.nextId();
                if (diseaseRecord.getId() == null) {
                    diseaseRecord.setId(diseaseRecordId);
                    diseaseRecord.setCreateTime(LocalDateTime.now());
                }
                if(Objects.nonNull(entity.getDiseaseId())){
                    diseaseRecord.setDiseaseId(entity.getDiseaseId());
                    TDisease tDisease = tDiseaseMapper.selectById(entity.getDiseaseId());
                    diseaseRecord.setDiseaseName(tDisease.getDiseaseName());
                }
                diseaseRecord.setContentUnit(tDrug1.getContentUnit());
                //diseaseRecord.setTreatmentCosts(tDrug1.getUnitPrice().multiply(new BigDecimal(tDrug.getDose())));
                tDiseaseRecordMapper.insert(diseaseRecord);
            }
        }
    }

    @Override
    public int getTreatNum(TLivestockAnalysis tLivestockAnalysis) {
        QueryWrapper<TDiseaseTreat> wrapper=new QueryWrapper<>();
        wrapper.eq("delete_flag",0);
        wrapper.eq("treatment_state",127);
        if(!StringUtils.isEmpty(tLivestockAnalysis.getDeptIds())){
            String[] split = tLivestockAnalysis.getDeptIds().split(",");
            wrapper.in("dept_id", split);
        }
        List<TDiseaseTreat> tDiseaseTreats = tDiseaseTreatMapper.selectList(wrapper);
        return tDiseaseTreats.size();
    }
}

/**
 * ------通用方法开始结束-----------------------------------------
 */


