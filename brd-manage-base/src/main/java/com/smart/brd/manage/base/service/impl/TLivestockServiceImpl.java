package com.smart.brd.manage.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.brd.manage.base.common.constant.BrdConstant;
import com.smart.brd.manage.base.common.exception.CustomException;
import com.smart.brd.manage.base.common.utils.ExcelUtils;
import com.smart.brd.manage.base.common.utils.LocalDateUtil;
import com.smart.brd.manage.base.entity.*;
import com.smart.brd.manage.base.entity.vo.LiveStockExcelVo;
import com.smart.brd.manage.base.entity.vo.LiveStockVo;
import com.smart.brd.manage.base.mapper.*;
import com.smart.brd.manage.base.service.ITDeviceService;
import com.smart.brd.manage.base.service.ITLivestockBedService;
import com.smart.brd.manage.base.service.ITLivestockService;
import com.smart.brd.manage.base.service.ITLivestockShedService;
import com.smart.common.core.domain.Result;
import com.smart.common.core.page.PageDomain;
import com.smart.common.utils.IdWorker;
import com.smart.common.utils.StringUtils;
import com.smart.common.utils.bean.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDate;
import java.util.Map;

/**
 * @author
 */
@Service
public class TLivestockServiceImpl extends ServiceImpl<TLivestockMapper, TLivestock> implements ITLivestockService {

    /**
     * ------通用方法开始-----------------------------------------
     */
    @Resource
    private TLivestockMapper tLivestockMapper;
    @Resource
    private TDeviceMapper tDeviceMapper;
    @Resource
    private TLivestockShedMapper tLivestockShedMapper;
    @Resource
    private TLivestockBedMapper tLivestockBedMapper;
    @Resource
    private TBaseDictMapper tBaseDictMapper;
    @Resource
    private ITDeviceService tdeviceService;
    @Resource
    private TLivestockLogMapper tLivestockLogMapper;
    @Resource
    private ITLivestockShedService itLivestockShedService;
    @Resource
    private ITLivestockBedService itLivestockBedService;
    @Resource
    private TDiseaseTreatMapper diseaseTreatMapper;

    @Override
    public IPage<LiveStockVo> tLivestockList(PageDomain page, LiveStockVo vo) {
        Page<LiveStockVo> pg = new Page<>(page.getPageNum(), page.getPageSize());
        if (StringUtils.isNotBlank(vo.getStartTime())) {
            vo.setStartTime(vo.getStartTime() + " 00:00:00");
        }
        if (StringUtils.isNotBlank(vo.getEndTime())) {
            vo.setEndTime(vo.getEndTime() + " 23:59:59");
        }
        return tLivestockMapper.tLivestockList(pg, vo);
    }

    @Override
    @Transactional
    public TLivestock tLivestockAdd(LiveStockVo entityVo) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        TLivestock entity = new TLivestock();
        BeanUtils.copyBeanProp(entity, entityVo);
        if (entity.getLivestockId() == null) {
            entity.setLivestockId(id);

            entity.setCreateTime(LocalDateTime.now());
            entity.setType(1);
            entity.setSituation(0);
            entity.setFieldId(entityVo.getDeptId());
            if (entityVo.getBirthDate() != null) {
                entity.setGroupTransferDate(entity.getBirthDate().plusDays(70));
                entity.setPreListingDate(entity.getBirthDate().plusDays(180));
            }
            // 判断是否是仔猪-仔猪的话给出默认的出栏日期
            groupTransferDate(entity);
            if (this.save(entity)) {
                try {
                    TLivestockLog tLivestockLog = new TLivestockLog();
                    BeanUtils.copyProperties(entity, tLivestockLog);
                    tLivestockLogMapper.insert(tLivestockLog);
                    QueryWrapper<TDevice> wrapper = new QueryWrapper<>();
                    wrapper.eq("device_code", entityVo.getDeviceCode());
                    wrapper.eq("dept_id", entityVo.getFieldId());
                    if (tDeviceMapper.selectList(wrapper).isEmpty()) {
                        TDevice device = new TDevice();
                        device.setDeviceCode(entityVo.getDeviceCode());
                        device.setDeviceType(BrdConstant.DEVICE_TYPE_EAR);
                        device = tdeviceService.tDeviceAdd(device);
                        entity.setDeviceId(device.getDeviceId());
                        tLivestockUpdate(entity);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            tLivestockUpdate(entity);
        }
        return entity;
    }

    private void groupTransferDate(TLivestock entity) {
        if (StringUtils.isNotBlank(entity.getSuitable()) && entity.getSuitable().equals(String.valueOf(BrdConstant.suitable_3))) {
            LocalDate birthDate = entity.getBirthDate();
            entity.setGroupTransferDate(birthDate.plusDays(70));
            entity.setPreListingDate(birthDate.plusDays(180));
        }
    }

    @Override
    @Transactional
    public int tLivestockDel(Long id) {
        TLivestock stock = getById(id);
        stock.setDeleteFlag(1);
        TLivestock tLivestock = tLivestockMapper.selectById(stock);
        int i = tLivestockMapper.updateById(stock);
        //存栏信息中删除 治疗管理中同步删除
        diseaseTreatMapper.updateByDeviceCode(tLivestock.getDeviceCode());
        return i;
    }

    @Override
    public TLivestock tLivestockUpdate(TLivestock entity) {
        //
        List<TLivestock> tLivestocks = tLivestockMapper.selectList(new QueryWrapper<TLivestock>().eq("device_code", entity.getDeviceCode()));

        if (!tLivestocks.isEmpty()) {
            boolean equals = tLivestocks.get(0).getLivestockId().equals(entity.getLivestockId());
            if (!equals) {
                throw new CustomException("该耳标号已经存在");
            }
        }
        TLivestock stock = tLivestockMapper.selectById(entity.getLivestockId());
        if (stock.getGroupTransferDate() != null && entity.getGroupTransferDate() != null && stock.getGroupTransferDate().toString().equals(entity.getGroupTransferDate().toString())) {
            entity.setTransferType(1);
        }
        //修改时根据牲畜栏id保存 栏舍名称
        TLivestockBed byId = itLivestockBedService.getById(entity.getBedId());
        if (byId != null) {
            entity.setBedName(byId.getBedName());
        }
        TLivestockShed byId1 = itLivestockShedService.getById(entity.getShedId());
        if (byId1 != null) {
            entity.setShedName(byId1.getShedName());
        }
        tLivestockMapper.updateById(entity);
        TLivestockLog tLivestockLog = new TLivestockLog();
        BeanUtils.copyProperties(entity, tLivestockLog);
        tLivestockLogMapper.updateById(tLivestockLog);
        return entity;
    }

    @Override
    @Transactional
    public LiveStockVo tLivestockDetail(TLivestock entity) {
        LambdaQueryWrapper<TLivestock> wrapper = new LambdaQueryWrapper<>();
//        if (!StringUtils.isEmpty(entity.getDeptIds())) {
//            wrapper.in(TLivestock::getDeptId, entity.getDeptIds());
//        }
        if (entity.getLivestockId() != null) {
            wrapper.eq(TLivestock::getLivestockId, entity.getLivestockId());
        }
        if (entity.getFieldId() != null) {
            wrapper.eq(TLivestock::getFieldId, entity.getFieldId());
        }
        if (entity.getDeviceCode() != null) {
            wrapper.eq(TLivestock::getDeviceCode, entity.getDeviceCode());
        }
        wrapper.eq(TLivestock::getDeleteFlag, 0);//添加搜索条件为未删除
        LiveStockVo vo = new LiveStockVo();
        try {
            TLivestock detail = tLivestockMapper.selectOne(wrapper);
            if (detail != null) {
                BeanUtils.copyBeanProp(vo, detail);
                vo.setBirthDate(detail.getBirthDate());
                // 计算淘汰日期
                getAndSetAge(detail, vo);
            }
        } catch (Exception e) {
            throw new CustomException("耳标为必传");
        }
        return vo;
    }

    /**
     * ------通用方法开始结束-----------------------------------------
     */

    @Override
    public List<LiveStockVo> queryLivestockList(LiveStockVo entity) {
        return tLivestockMapper.queryLivestockList(entity);
    }


    @Override
    @Transactional
    public Result livestockImport(MultipartFile path, LiveStockVo entity) {
        List<LiveStockExcelVo> list = ExcelUtils.importExcel(path, 1, 1, LiveStockExcelVo.class);
        String msg = "";
        for (LiveStockExcelVo vo : list) {
            vo.setDeptId(entity.getDeptId());
            vo.setFieldId(entity.getDeptId());

            String shedName = vo.getShedName();
            String bedName = vo.getBedName();
            //创建保存对象
            LiveStockVo lvo = new LiveStockVo();
            BeanUtils.copyBeanProp(lvo, vo);
            try {
                TLivestockShed shed = itLivestockShedService.quetyShed(entity.getDeptId(), shedName);
                if (shed != null) {
                    lvo.setShedId(shed.getShedId());
                } else {
                    msg += "序号：" + vo.getXuhao() + ",舍名字【" + vo.getShedName() + "】不存在。";
                    continue;
                }
                TLivestockBed bed = itLivestockBedService.quetyBed(entity.getDeptId(), shedName, bedName);
                if (bed != null) {
                    lvo.setBedId(bed.getBedId());
                } else {
                    msg += "序号：" + vo.getXuhao() + ",舍" + vo.getShedName() + "中，栏名字【" + vo.getBedName() + "】不存在。";
                    continue;
                }
                //如果出生日期或入栏日期或耳标或家畜类型为空时抛出异常
                if(vo.getBirthDate()==null){
                    //throw new CustomException("出生日期不能为空");
                    msg +=  "序号：" + vo.getXuhao() + ",出生日期不能为空";
                    continue;
                }
                if(vo.getEntryDate()==null){
                    msg +=  "序号：" + vo.getXuhao() + ",入栏日期不能为空";
                    continue;
                }
                if(vo.getDeviceCode()==null){
                    msg +=  "序号：" + vo.getXuhao() + ",耳标不能为空";
                    continue;
                }
                if(vo.getVarieties()==null){
                    msg +=  "序号：" + vo.getXuhao() + ",家畜品种不能为空";
                    continue;
                }
                if(vo.getSuitable()==null){
                    msg +=  "序号：" + vo.getXuhao() + ",家畜种类不能为空";
                    continue;
                }
                //去字典将汉字转为数字
                int varietiesId = tBaseDictMapper.selectIdByName(vo.getVarieties());
                lvo.setVarieties(String.valueOf(varietiesId));
                int suitableId = tBaseDictMapper.selectIdByName(vo.getSuitable());
                lvo.setSuitable(String.valueOf(suitableId));

            } catch (Exception e) {
                e.printStackTrace();
                msg += "序号：" + vo.getXuhao() + ",出现错误。";
                return null;
            }
            TLivestock livestock = querytLivestock(entity.getDeptId(), vo.getDeviceCode());
            if (livestock == null || livestock.getFieldId() == null) {
                tLivestockAdd(lvo);
            } else {
                msg += "序号：" + vo.getXuhao() + ",耳标号【" + vo.getDeviceCode() + "】已经存在。";
            }
        }
        if (StringUtils.isNotBlank(msg)) {
            throw new CustomException(msg);
        }
        return Result.SUCCESS();
    }

    private TLivestock querytLivestock(Long fieldId, String deviceCode) {
        LambdaQueryWrapper<TLivestock> wrapper = new LambdaQueryWrapper<>();
        if (fieldId != null) {
            wrapper.eq(TLivestock::getFieldId, fieldId);
        }
        if (deviceCode != null) {
            wrapper.eq(TLivestock::getDeviceCode, deviceCode);
        }
        wrapper.eq(TLivestock::getDeleteFlag, 0);
        return tLivestockMapper.selectOne(wrapper);
    }

    @Override
    public void livestockExport(HttpServletResponse response) {
        List<LiveStockVo> vos = new ArrayList<>();
        LiveStockVo export = new LiveStockVo();
        export.setType(1);
        export.setSuitable("输入类别：\n" +
                "17-母猪\n" +
                "18-公猪\n" +
                "19-仔猪\n" +
                "20-育肥猪\n" +
                "21-后备母猪\n" +
                "22-后备公猪\n");
        export.setBedName("输入栏名");
        export.setShedName("输入舍名");
        vos.add(export);
        ExcelUtils.exportExcel(vos, "注：1.带“*”的字段为必填项\n" +
                "    2.第三行为示例数据，请在上传时删除该条记录。", "存栏模板", LiveStockVo.class, "stock.xls", response);
    }

    private void getAndSetAge(TLivestock entity, LiveStockVo vo) {
        if (vo.getBirthDate() != null) {
            Map<String, Object> map = LocalDateUtil.calEscapeAge(entity.getBirthDate(), LocalDate.now());
            vo.setEscapeMonth(Integer.parseInt(String.valueOf(map.get("months"))));
            vo.setEscapeDay(Integer.parseInt(String.valueOf(map.get("days"))));
            vo.setEscapeAge(String.valueOf(map.get("age")));
        }
    }


    @Override
    public void preTransferList() {
        LiveStockVo entity = new LiveStockVo();
        entity.setGroupTransferDate(LocalDate.now());
        entity.setSuitable(BrdConstant.suitable_4.toString());
        List<TLivestock> list = tLivestockMapper.preTransferList(entity);
        for (int i = 0; i < list.size(); i++) {
            tLivestockMapper.preListingdateNull(list.get(i).getLivestockId());
        }
    }

    @Override
    public void suitableListSet() {
        LiveStockVo entity = new LiveStockVo();
        entity.setGroupTransferDate(LocalDate.now());
        entity.setSuitable(BrdConstant.suitable_3.toString());
        List<TLivestock> list = tLivestockMapper.suitableList(entity);
        for (int i = 0; i < list.size(); i++) {
            TLivestock stock = list.get(i);
            stock.setSuitable(BrdConstant.suitable_4.toString());
            tLivestockMapper.updateById(stock);
        }
    }

}
