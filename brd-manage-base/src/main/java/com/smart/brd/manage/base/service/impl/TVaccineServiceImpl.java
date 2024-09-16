package com.smart.brd.manage.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.brd.manage.base.common.exception.CustomException;
import com.smart.brd.manage.base.common.utils.ExcelUtils;
import com.smart.brd.manage.base.entity.TBaseDict;
import com.smart.brd.manage.base.entity.TVaccine;
import com.smart.brd.manage.base.entity.vo.VaccinesManufacturerVo;
import com.smart.brd.manage.base.entity.vo.VaccinesSupplierVo;
import com.smart.brd.manage.base.mapper.TBaseDictMapper;
import com.smart.brd.manage.base.mapper.TVaccineMapper;
import com.smart.brd.manage.base.service.DictListService;
import com.smart.brd.manage.base.service.ITVaccineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.page.PageDomain;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.smart.common.utils.IdWorker;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */
@Service
public class TVaccineServiceImpl extends ServiceImpl<TVaccineMapper, TVaccine> implements ITVaccineService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TVaccineMapper tVaccineMapper;
    @Resource
    private DictListService dictListService;
    @Resource
    private TBaseDictMapper tBaseDictMapper;

    @Override
    public IPage<TVaccine> tVaccineList(PageDomain page, TVaccine vo) {
        Page<TVaccine> pg = new Page<>(page.getPageNum(), page.getPageSize());
        if (StringUtils.isNotBlank(vo.getStartTime())) {
            vo.setStartTime(vo.getStartTime() + " 00:00:00");
        }
        if (StringUtils.isNotBlank(vo.getEndTime())) {
            vo.setEndTime(vo.getEndTime() + " 23:59:59");
        }
        return tVaccineMapper.tVaccineList(pg,vo);
    }

    @Override
    @Transactional
    public TVaccine tVaccineAdd(TVaccine entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        //检查疫苗唯一性
        Boolean flag = checkUnique(entity);
        if (!flag){
            throw new CustomException("该疫苗已经添加");
        }
        List<VaccinesManufacturerVo> vaccinesManufacturerVoList = entity.getVaccinesManufacturerVoList();
        for (VaccinesManufacturerVo vaccinesManufacturerVo : vaccinesManufacturerVoList) {
            List<VaccinesSupplierVo> vaccinesSupplierVoList = vaccinesManufacturerVo.getVaccinesSupplierVoList();
            for (VaccinesSupplierVo vaccinesSupplierVo : vaccinesSupplierVoList) {
                Long id = idWorker.nextId();
                entity.setVaccineId(id);
                entity.setCreateTime(LocalDateTime.now());
//                entity.setSuitable(dictListService.typeListToType(entity.getSuitableList()));
//                entity.setSuitableName(dictListService.typeListToName("dict_type_1",entity.getSuitableList()));
                entity.setManufacturer(vaccinesManufacturerVo.getManufacturer());
                entity.setSupplierName(vaccinesSupplierVo.getSupplierName());
//                entity.setUnitPrice(vaccinesSupplierVo.getUnitPrice());
                entity.setContent(vaccinesManufacturerVo.getContent());
                this.save(entity);
            }
        }
        return entity;
    }

    private Boolean checkUnique(TVaccine entity) {
        QueryWrapper<TVaccine> tTVaccineQueryWrapper = new QueryWrapper<>();
        tTVaccineQueryWrapper.eq("vaccine_name",entity.getVaccineName());//名称
        tTVaccineQueryWrapper.and(wrapper -> wrapper.eq("manufacturer",entity.getVaccinesManufacturerVoList().get(0).getManufacturer()));//生产商
        tTVaccineQueryWrapper.and(wrapper -> wrapper.eq("supplier_name",entity.getVaccinesManufacturerVoList().get(0).getVaccinesSupplierVoList().get(0).getSupplierName()));//供应商
        tTVaccineQueryWrapper.and(wrapper -> wrapper.eq("unit_price",entity.getVaccinesManufacturerVoList().get(0).getVaccinesSupplierVoList().get(0).getUnitPrice()));//单价

        List<TVaccine> tDrugs = tVaccineMapper.selectList(tTVaccineQueryWrapper);
        return tDrugs.isEmpty() ? true : false;
    }

    @Override
    public int tVaccineDel(Long id) {
        TVaccine tVaccine = tVaccineMapper.selectById(id);
        tVaccine.setDeleteFlag(1);
        return tVaccineMapper.updateById(tVaccine);
    }

    @Override
    public TVaccine tVaccineUpdate(TVaccine entity) {
//        entity.setSuitable(dictListService.typeListToType(entity.getSuitableList()));
//        entity.setSuitableName(dictListService.typeListToName("dict_type_1",entity.getSuitableList()));
        tVaccineMapper.updateById(entity);
        return entity;
    }

    @Override
    public TVaccine tVaccineDetail(TVaccine entity) {
//        List<Integer> su=new ArrayList<>();
//        String[] split = tVaccine.getSuitable().split(",");
//        for (String s : split) {
//            su.add(Integer.parseInt(s));
//        }
//        tVaccine.setSuitableList(su);
        return tVaccineMapper.selectById(entity.getVaccineId());
    }

    /**------通用方法开始结束-----------------------------------------*/

    @Override
    public List<TVaccine> queryVaccineList(HttpServletRequest request, TVaccine entity) {
        LambdaQueryWrapper<TVaccine> wrapper = new LambdaQueryWrapper<>();
//        if (entity.getDeptId() != null) {
//            wrapper.eq(TVaccine::getDeptId, entity.getDeptId());
//        }
        if (entity.getPreventionObj() != null) {
            wrapper.eq(TVaccine::getPreventionObj, entity.getPreventionObj());
        }
        wrapper.eq(TVaccine::getDeleteFlag,0);
        return tVaccineMapper.selectList(wrapper);
    }

    @Override
    @Transactional
    public void importTVaccine(MultipartFile path) {
        List<TVaccine> list = ExcelUtils.importExcel(path, 0, 1, TVaccine.class);
        if(list.size()>1){
            list.forEach(s -> {
                String msg=""+list.size();
                try {
                    TVaccine tVaccine = new TVaccine();
                    IdWorker idWorker = new IdWorker(0, 0);
                    Long id = idWorker.nextId();
                    tVaccine.setPreventionObj(tBaseDictMapper.selectOne(new LambdaQueryWrapper<TBaseDict>().eq(TBaseDict::getDictName,s.getPreventionObj()).eq(TBaseDict::getDictTypeId,"dict_type_90")).getDictId().toString());
                    msg+=s.getVaccineName();
                    tVaccine.setManufacturer(s.getShengchanshang());
                    tVaccine.setSupplierName(tBaseDictMapper.selectOne(new LambdaQueryWrapper<TBaseDict>().eq(TBaseDict::getDictName,s.getGonghuoshang()).eq(TBaseDict::getDictTypeId,"dict_type_131")).getDictId().toString());
                    tVaccine.setVaccineName(s.getVaccineName());
//                    tVaccine.setUnitPrice(new BigDecimal(10));
                    tVaccine.setContent(s.getContent());
                    tVaccine.setVaccineId(id);
                    tVaccine.setCreateTime(LocalDateTime.now());
                    tVaccineMapper.insert(tVaccine);
                }catch (Exception e){
                    throw  new CustomException(msg+"有错误！！");
                }
            });
        }
    }

}
