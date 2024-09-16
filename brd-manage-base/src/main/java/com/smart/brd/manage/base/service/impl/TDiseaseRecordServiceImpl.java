package com.smart.brd.manage.base.service.impl;

import com.smart.brd.manage.base.entity.TDiseaseRecord;
import com.smart.brd.manage.base.mapper.TDiseaseRecordMapper;
import com.smart.brd.manage.base.service.ITDiseaseRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.page.PageDomain;
import javax.annotation.Resource;
import com.smart.common.utils.IdWorker;
import java.time.LocalDateTime;

/**
 * @author 
 */
@Service
public class TDiseaseRecordServiceImpl extends ServiceImpl<TDiseaseRecordMapper, TDiseaseRecord> implements ITDiseaseRecordService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TDiseaseRecordMapper tDiseaseRecordMapper;

    @Override
    public IPage<TDiseaseRecord> tDiseaseRecordList(PageDomain page, TDiseaseRecord vo) {
        Page<TDiseaseRecord> pg = new Page<>(page.getPageNum(), page.getPageSize());
        return tDiseaseRecordMapper.tDiseaseRecordList(pg,vo);
    }

    @Override
    public TDiseaseRecord tDiseaseRecordAdd(TDiseaseRecord entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tDiseaseRecordUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tDiseaseRecordDel(Long id) {
        return tDiseaseRecordMapper.deleteById(id);
    }

    @Override
    public TDiseaseRecord tDiseaseRecordUpdate(TDiseaseRecord entity) {
        tDiseaseRecordMapper.updateById(entity);
        return entity;
    }

    @Override
    public TDiseaseRecord tDiseaseRecordDetail(TDiseaseRecord entity) {
        return tDiseaseRecordMapper.selectById(entity.getId());
    }

    /**------通用方法开始结束-----------------------------------------*/

}
