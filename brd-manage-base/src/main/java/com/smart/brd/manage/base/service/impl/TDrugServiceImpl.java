package com.smart.brd.manage.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.brd.manage.base.common.exception.CustomException;
import com.smart.brd.manage.base.common.utils.ExcelUtils;
import com.smart.brd.manage.base.entity.TBaseDict;
import com.smart.brd.manage.base.entity.TDisease;
import com.smart.brd.manage.base.entity.TDrug;
import com.smart.brd.manage.base.entity.vo.DrugManufacturerVo;
import com.smart.brd.manage.base.entity.vo.DrugSupplierVo;
import com.smart.brd.manage.base.mapper.TBaseDictMapper;
import com.smart.brd.manage.base.mapper.TDiseaseMapper;
import com.smart.brd.manage.base.mapper.TDrugMapper;
import com.smart.brd.manage.base.service.DictListService;
import com.smart.brd.manage.base.service.ITDrugService;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author
 */
@Service
public class TDrugServiceImpl extends ServiceImpl<TDrugMapper, TDrug> implements ITDrugService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TDrugMapper tDrugMapper;
    @Resource
    private DictListService dictListService;
    @Resource
    private TDiseaseMapper tDiseaseMapper;
    @Resource
    private TBaseDictMapper tBaseDictMapper;

    @Override
    public IPage<TDrug> tDrugList(PageDomain page, TDrug vo) {
        Page<TDrug> pg = new Page<>(page.getPageNum(), page.getPageSize());
        //前端传入的是疾病id 将疾病id转成疾病名称进行模糊查询 避免1 like 11
        if (!StringUtils.isEmpty(vo.getDiseasesName())){
            String diseasesName = vo.getDiseasesName();
            int i = Integer.parseInt(diseasesName);
            TDisease tDisease = tDiseaseMapper.selectById(i);
            vo.setDiseasesName(tDisease.getDiseaseName());
        }
        return tDrugMapper.tDrugList(pg,vo);
    }

    @Override
    @Transactional
    public TDrug tDrugAdd(TDrug entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        //检查兽药唯一性
        Boolean flag = checkUnique(entity);
        if (!flag){
            throw new CustomException("该药品已经添加");
        }
        List<DrugManufacturerVo> drugManufacturerVoList = entity.getDrugManufacturerVoList();
        for (DrugManufacturerVo drugManufacturerVo : drugManufacturerVoList) {
            List<DrugSupplierVo> drugSupplierVos = drugManufacturerVo.getDrugSupplierVos();
            for (DrugSupplierVo drugSupplierVo : drugSupplierVos) {
                List<String> diseaseList = entity.getDiseaseId();
                String di="";
                String dis="";
                for (String s : diseaseList) {
                    if ("".equals(di)){
                        di=di+s;
                    }else {
                        di=di+","+s;
                    }
                    TDisease tDisease = tDiseaseMapper.selectById(s);
                    if (tDisease!=null){
                        if ("".equals(dis)){
                            dis=dis+tDisease.getDiseaseName();
                        }else {
                            dis=dis+","+tDisease.getDiseaseName();
                        }
                    }
                }
                entity.setDiseases(di);
                entity.setDiseasesName(dis);
                Long id = idWorker.nextId();
                entity.setDrugId(id);
                entity.setCreateTime(LocalDateTime.now());
                /*注释价格相关*/
                //entity.setUnitPrice(drugSupplierVo.getPrice().divide(new BigDecimal(drugManufacturerVo.getContentNum()),2,BigDecimal.ROUND_HALF_UP));
//                entity.setSuitable(dictListService.typeListToType(entity.getSuitableList()));
//                entity.setSuitableName(dictListService.typeListToName("dict_type_1",entity.getSuitableList()));
                entity.setManufacturer(drugManufacturerVo.getManufacturer());
                entity.setContentNum(drugManufacturerVo.getContentNum());
                entity.setContentUnit(drugManufacturerVo.getContentUnit());
                entity.setSupplierName(drugSupplierVo.getSupplierName());
                /*注释价格相关*/
                //entity.setPrice(drugSupplierVo.getPrice());
                this.save(entity);
            }
        }
        return entity;
    }

    @Override
    public int tDrugDel(Long id) {
        TDrug tDrug = tDrugMapper.selectById(id);
        tDrug.setDeleteFlag(1);
        return tDrugMapper.updateById(tDrug);
    }

    @Override
    @Transactional
    public TDrug tDrugUpdate(TDrug entity) {
//        entity.setSuitable(dictListService.typeListToType(entity.getSuitableList()));
//        entity.setSuitableName(dictListService.typeListToName("dict_type_1",entity.getSuitableList()));
        List<String> diseaseList = entity.getDiseaseId();
        String di="";
        String dis="";
        for (String s : diseaseList) {
            if ("".equals(di)){
                di=di+s;
            }else {
                di=di+","+s;
            }
            TDisease tDisease = tDiseaseMapper.selectById(s);
            if (tDisease!=null){
                if ("".equals(dis)){
                    dis=dis+tDisease.getDiseaseName();
                }else {
                    dis=dis+","+tDisease.getDiseaseName();
                }
            }
        }
        entity.setDiseases(di);
        entity.setDiseasesName(dis);
        tDrugMapper.updateById(entity);
        return entity;
    }

    @Override
    public TDrug tDrugDetail(TDrug entity) {
        TDrug detail = tDrugMapper.selectById(entity.getDrugId());
        List<String> su=new ArrayList<>();
        String[] split = detail.getDiseases().split(",");
        for (String s : split) {
            su.add(s);
        }
        detail.setDiseaseId(su);
//        List<Integer> su=new ArrayList<>();
//        String[] split = detail.getSuitable().split(",");
//        for (String s : split) {
//            su.add(Integer.parseInt(s));
//        }
//        detail.setSuitableList(su);
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

    @Override
    public List<TDrug> queryTDrugList(HttpServletRequest request, TDrug entity) {
        LambdaQueryWrapper<TDrug> wrapper = new LambdaQueryWrapper<>();
//        if (entity.getDeptId() != null) {
//            wrapper.eq(TDrug::getDeptId, entity.getDeptId());
//        }
        if (entity.getDisease()!=null){
            wrapper.like(TDrug::getDiseases,entity.getDisease());
        }
        wrapper.eq(TDrug::getDeleteFlag,0);
        return tDrugMapper.selectList(wrapper);
    }

    //检查唯一性方法
    private Boolean checkUnique(TDrug entity) {
        QueryWrapper<TDrug> tDrugQueryWrapper = new QueryWrapper<>();

        tDrugQueryWrapper.eq("drug_name",entity.getDrugName());//名称
        tDrugQueryWrapper.and(wrapper -> wrapper.eq("effect",entity.getEffect()));//疗效
        tDrugQueryWrapper.and(wrapper -> wrapper.eq("manufacturer",entity.getDrugManufacturerVoList().get(0).getManufacturer()));//生产商
        tDrugQueryWrapper.and(wrapper -> wrapper.eq("supplier_name",entity.getDrugManufacturerVoList().get(0).getDrugSupplierVos().get(0).getSupplierName()));//供应商
        tDrugQueryWrapper.and(wrapper -> wrapper.eq("price",entity.getDrugManufacturerVoList().get(0).getDrugSupplierVos().get(0).getPrice()));//单价

        List<TDrug> tDrugs = tDrugMapper.selectList(tDrugQueryWrapper);
        return tDrugs.size() == 0 ? true : false;
    }
    public String getNum(String str){
        Pattern p = Pattern.compile("(\\d+\\.\\d+)");
        Matcher m = p.matcher(str);
        if (m.find()) {
            str = m.group(1) == null ? "" : m.group(1);
        } else {
            //如果匹配不到小数，就进行整数匹配
            p = Pattern.compile("(\\d+)");
            m = p.matcher(str);
            if (m.find()) {
                //如果有整数相匹配
                str = m.group(1) == null ? "" : m.group(1);
            } else {
                str = "";
            }
        }
        return str;
    }
    @Override
    @Transactional
    public void importTDrug(MultipartFile path) {
        List<TDrug> list = ExcelUtils.importExcel(path, 0, 1, TDrug.class);
        if(list.size()>1){
            list.forEach(s -> {
                String msg=""+list.size();
                try {
                    IdWorker idWorker = new IdWorker(0, 0);
                    Long id = idWorker.nextId();
                    TDrug tDrug = new TDrug();
                    tDrug.setDrugName(s.getDrugName());
                    String effect = s.getEffect();
                    String[] split = effect.split(",");
                    String diseases="";
                    String diseasesName="";
                    for (String s1 : split) {
                        TDisease tDisease = tDiseaseMapper.selectOne(new LambdaQueryWrapper<TDisease>().eq(TDisease::getDiseaseName, s1));
                        if ("".equals(diseases)){
                            diseases=diseases+tDisease.getDiseaseId();
                            diseasesName=diseasesName+tDisease.getDiseaseName();
                        }else {
                            diseases=diseases+","+tDisease.getDiseaseId();
                            diseasesName=diseasesName+","+tDisease.getDiseaseName();
                        }
                    }
                    tDrug.setDiseases(diseases);
                    tDrug.setDiseasesName(diseasesName);
                    tDrug.setManufacturer(s.getManufacturer());
                    tDrug.setSupplierName(tBaseDictMapper.selectOne(new LambdaQueryWrapper<TBaseDict>().eq(TBaseDict::getDictName,s.getSupplierName()).eq(TBaseDict::getDictTypeId,"dict_type_130")).getDictId().toString());
                    msg+=s.getDrugName();
                    String model = s.getModel();
                    String num = getNum(model);
                    tDrug.setContentUnit(model.replace(num,""));
                    tDrug.setContentNum((double)new BigDecimal(num).toBigInteger().intValue());
                    BigDecimal bigDecimal = new BigDecimal(10);
                    /*注释价格相关*/
                    //tDrug.setUnitPrice(bigDecimal.divide(new BigDecimal(num),2,BigDecimal.ROUND_HALF_UP));
                    //tDrug.setPrice(bigDecimal);
                    tDrug.setDeleteFlag(0);
                    tDrug.setCreateTime(LocalDateTime.now());
                    tDrug.setDrugId(id);
                    tDrugMapper.insert(tDrug);
                }catch (Exception e){
                    throw  new CustomException(msg+"有错误！！");
                }
            });
        }
    }
}
