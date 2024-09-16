package com.smart.brd.manage.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.brd.manage.base.common.constant.BrdConstant;
import com.smart.brd.manage.base.common.exception.CustomException;
import com.smart.brd.manage.base.entity.*;
import com.smart.brd.manage.base.entity.vo.TLivestockBedVo;
import com.smart.brd.manage.base.entity.vo.FileVo;
import com.smart.brd.manage.base.mapper.TLivestockBedMapper;
import com.smart.brd.manage.base.mapper.TLivestockMapper;
import com.smart.brd.manage.base.mapper.TLivestockShedMapper;
import com.smart.brd.manage.base.service.ITLivestockBedService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.brd.manage.base.service.ITLivestockShedService;
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
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */
@Service
public class TLivestockBedServiceImpl extends ServiceImpl<TLivestockBedMapper, TLivestockBed> implements ITLivestockBedService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TLivestockBedMapper tLivestockBedMapper;
    @Resource
    private TLivestockShedMapper tLivestockShedMapper;
    @Resource
    private ITLivestockShedService itLivestockShedService;
    @Resource
    private ITLivestockBedService itLivestockBedService;

    @Resource
    private TLivestockMapper tLivestockMapper;

    @Override
    public IPage<BarnsVo> tLivestockBedList(PageDomain page, BarnsVo vo) {
        Page<BarnsVo> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<BarnsVo> iPage = tLivestockBedMapper.tLivestockBedList(pg,vo);
        for(BarnsVo a:iPage.getRecords()){
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String str = df.format(a.getCreateTime());
            a.setCreateTimeDisplay(str);
        }
        return iPage;
    }

    @Override
    public TLivestockBed tLivestockBedAdd(TLivestockBed entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getBedId() == null ){
            entity.setBedId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tLivestockBedUpdate(entity);
        }
        return entity;
    }

    @Override
    @Transactional
    public int tLivestockBedDel(Long id) {
        TLivestockBed tLivestockBed = tLivestockBedMapper.selectById(id);
        tLivestockBed.setDeleteFlag(1);
        QueryWrapper<TLivestock> tLivestockQueryWrapper = new QueryWrapper<>();
        List<TLivestock> lives = tLivestockMapper.selectList(tLivestockQueryWrapper.eq("bed_id", id).eq("delete_flag",0));
        if (lives.size() != 0){
            throw new CustomException("该栏内还存有家畜,不能删除");
        }
        int res = tLivestockBedMapper.updateById(tLivestockBed);
        LambdaQueryWrapper<TLivestockBed> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(TLivestockBed::getShedId,tLivestockBed.getShedId());
        wrapper.eq(TLivestockBed::getDeleteFlag,0);
        List<TLivestockBed> tLivestockBeds = tLivestockBedMapper.selectList(wrapper);
        if (tLivestockBeds.size()<1){
            TLivestockShed tLivestockShed=new TLivestockShed();
            tLivestockShed.setShedId(tLivestockBed.getShedId());
            tLivestockShed.setDeleteFlag(1);
            tLivestockShedMapper.updateById(tLivestockShed);
        }
        return res;
    }

    @Override
    public TLivestockBed tLivestockBedUpdate(TLivestockBed entity) {
        tLivestockBedMapper.updateById(entity);
        return entity;
    }

    @Override
    public TLivestockBed tLivestockBedDetail(TLivestockBed entity) {
        return  tLivestockBedMapper.selectById(entity.getBedId());
    }

    /**------通用方法开始结束-----------------------------------------*/

    @Override
    @Transactional
    public Result barnsAdd(BarnsVo entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        long sId = idWorker.nextId();
        long bId = idWorker.nextId();
        String msg = "";
        TLivestockShed shed = itLivestockShedService.quetyShed(entity.getDeptId(),entity.getShedName());
        if(shed == null){
            TLivestockShed tLivestockShed=new TLivestockShed();
            BeanUtils.copyProperties(entity,tLivestockShed);
            tLivestockShed.setShedId(sId);
            tLivestockShed.setFieldId(entity.getDeptId());
            tLivestockShed.setDeleteFlag(0);
            tLivestockShed.setCreateTime(LocalDateTime.now());
            tLivestockShedMapper.insert(tLivestockShed);
        }
        TLivestockBed bed = quetyBed(entity.getDeptId(),entity.getShedName(),entity.getBedName());
        if(bed == null){
            TLivestockBed tLivestockBed = new TLivestockBed();
            queryFileName(entity);
            BeanUtils.copyProperties(entity,tLivestockBed);
            tLivestockBed.setBedId(bId);
            if (shed == null) {
                tLivestockBed.setShedId(sId);
            }else {
                tLivestockBed.setShedId(shed.getShedId());
            }
            tLivestockBed.setFieldId(entity.getDeptId());
            tLivestockBed.setDeleteFlag(0);
            tLivestockBed.setCreateTime(LocalDateTime.now());
            tLivestockBedMapper.insert(tLivestockBed);
        }else{
            msg += "栏名字已存在!";
            throw  new CustomException(msg);
        }
        return Result.SUCCESS();
    }

    private void queryFileName(BarnsVo entityVo){
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

    @Override
    public TLivestockBed quetyBed(Long fieldId,String shedName,String bedName){
        QueryWrapper<TLivestockBed> bedWrapper = new QueryWrapper<>();
        bedWrapper.eq("field_id", fieldId);
        bedWrapper.eq("shed_name", shedName);
        bedWrapper.eq("bed_name", bedName);
        bedWrapper.eq("delete_flag",0);
        return tLivestockBedMapper.selectOne(bedWrapper);
    }

    @Override
    public List<TLivestockBedVo> queryBreederList(HttpServletRequest request,TLivestockBedVo vo) {
        return tLivestockBedMapper.queryBreederList(vo);
    }

    @Override
    @Transactional
    public BarnsVo barnsUpdate(BarnsVo entity) {
        TLivestockBed tLivestockBed1 = tLivestockBedMapper.selectById(entity.getBedId());
        TLivestockShed tLivestockShed=new TLivestockShed();
        BeanUtils.copyProperties(entity,tLivestockShed);
        tLivestockShed.setShedId(tLivestockBed1.getShedId());
        List<TLivestockBed> tLivestockBeds = tLivestockBedMapper.selectList(new LambdaQueryWrapper<TLivestockBed>()
                .eq(TLivestockBed::getShedId, tLivestockBed1.getShedId())
                .eq(TLivestockBed::getDeleteFlag, 0)
                .eq(TLivestockBed::getDeptId,entity.getDeptId()));
        List<TLivestockShed> tLivestockShedList = tLivestockShedMapper.selectList(new LambdaQueryWrapper<TLivestockShed>()
                .eq(TLivestockShed::getShedName, entity.getShedName())
                .eq(TLivestockShed::getDeleteFlag, 0)
                .eq(TLivestockShed::getDeptId, entity.getDeptId()));
        int shedSize = tLivestockShedList.size();
        int livestockSize = tLivestockMapper.selectList(new LambdaQueryWrapper<TLivestock>()
                .eq(TLivestock::getDeleteFlag, 0)
                .eq(TLivestock::getDeptId, entity.getDeptId())
                .eq(TLivestock::getBedId, entity.getBedId())).size();
        List<TLivestockBed> beds = tLivestockBedMapper.selectList(new LambdaQueryWrapper<TLivestockBed>()
                .eq(TLivestockBed::getBedName,entity.getBedName())
                .eq(TLivestockBed::getShedId, tLivestockBed1.getShedId())
                .eq(TLivestockBed::getDeleteFlag, 0)
                .eq(TLivestockBed::getDeptId,entity.getDeptId()));
        if (beds.size()>0&&!beds.get(0).getBedId().equals(entity.getBedId())){
            throw new CustomException(entity.getBedName()+"已存在！不能重复！");
        }

        if (shedSize==1){
            if (!tLivestockBed1.getShedId().equals(tLivestockShedList.get(0).getShedId())){
                throw new CustomException("请删除本条栏舍后重新创建");
            }else if (tLivestockBeds.size()>0&&!tLivestockBed1.getShedName().equals(entity.getShedName())){
                throw new CustomException(tLivestockBed1.getShedName()+"舍下还有其它栏，不能修改");
            }else if (livestockSize<1||(entity.getBedName().equals(tLivestockBed1.getBedName())&&entity.getShedName().equals(tLivestockBed1.getShedName()))){
                    tLivestockShedMapper.updateById(tLivestockShed);
                    TLivestockBed tLivestockBed=new TLivestockBed();
                    queryFileName(entity);
                    BeanUtils.copyProperties(entity,tLivestockBed);
                    tLivestockBed.setShedName(entity.getShedName());
                    tLivestockBedMapper.updateById(tLivestockBed);
                    return entity;
            }else{
                throw new CustomException("该栏还有动物，不能修改！");
            }
        }else if (shedSize<1){
            if (livestockSize<1){
                if (1==tLivestockBeds.size()){
                    tLivestockShedMapper.updateById(tLivestockShed);
                    TLivestockBed tLivestockBed=new TLivestockBed();
                    queryFileName(entity);
                    BeanUtils.copyProperties(entity,tLivestockBed);
                    tLivestockBed.setShedName(entity.getShedName());
                    tLivestockBedMapper.updateById(tLivestockBed);
                    return entity;
                }else {
                    throw new CustomException(tLivestockBed1.getShedName()+"舍下还有其它栏，不能修改");
                }
            }else {
                throw new CustomException("该栏还有动物，不能修改！");
            }

        }
        return entity;
    }

    @Override
    public BarnsVo barnsInfo(Long id) {
        BarnsVo barnsVo=new BarnsVo();
        TLivestockBed tLivestockBed = tLivestockBedMapper.selectById(id);
        BeanUtils.copyProperties(tLivestockBed,barnsVo);
        TLivestockShed tLivestockShed = tLivestockShedMapper.selectById(tLivestockBed.getShedId());
        BeanUtils.copyProperties(tLivestockShed,barnsVo);
        return barnsVo;
    }

    @Override
    public List<TLivestockBed> queryBedList(HttpServletRequest request,TLivestockBed entity) {
        LambdaQueryWrapper<TLivestockBed> wrapper = new LambdaQueryWrapper();
        if (!StringUtils.isEmpty(entity.getDeptIds())) {
            String[] split = entity.getDeptIds().split(",");
            wrapper.in(TLivestockBed::getDeptId, split);
        }

        if (entity.getShedId() != null) {
            wrapper.eq(TLivestockBed::getShedId, entity.getShedId());
        }
        wrapper.eq(TLivestockBed::getDeleteFlag,0);
        return tLivestockBedMapper.selectList(wrapper);
    }

    @Override
    public List<Tree> selectTree(TLivestockAnalysis tla) {
        List<Tree> treeList = new ArrayList<>();
        LambdaQueryWrapper<TLivestockShed> wrapper = new LambdaQueryWrapper();
        wrapper.eq(TLivestockShed::getDeleteFlag,0);
        wrapper.in(TLivestockShed::getDeptId,tla.getDeptIds());
        List<TLivestockShed> tLivestockShedList = tLivestockShedMapper.selectList(wrapper);
        for (TLivestockShed tLivestockShed : tLivestockShedList) {
            Tree tree=new Tree();
            tree.setId(tLivestockShed.getShedId());
            tree.setName(tLivestockShed.getShedName());
            LambdaQueryWrapper<TLivestockBed> wrapper1 = new LambdaQueryWrapper();
            wrapper1.eq(TLivestockBed::getShedId,tLivestockShed.getShedId());
            wrapper1.eq(TLivestockBed::getDeleteFlag,0);
            List<TLivestockBed> list = tLivestockBedMapper.selectList(wrapper1);
            if (list.size()>0){
                List<Tree> ff = new ArrayList<>();
                for (TLivestockBed tLivestockBed : list) {
                    Tree t=new Tree();
                    t.setId(tLivestockBed.getBedId());
                    t.setName(tLivestockBed.getBedName());
                    ff.add(t);
                }
                tree.setChildren(ff);
            }
            treeList.add(tree);
        }
        return treeList;
    }

}
