package com.smart.brd.manage.base.service.impl;

import com.smart.brd.manage.base.entity.TVaccineRecord;
import com.smart.brd.manage.base.mapper.TVaccineRecordMapper;
import com.smart.brd.manage.base.service.ITVaccineRecordService;
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
public class TVaccineRecordServiceImpl extends ServiceImpl<TVaccineRecordMapper, TVaccineRecord> implements ITVaccineRecordService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TVaccineRecordMapper vaccineRecordMapper;

    @Override
    public IPage<TVaccineRecord> vaccineRecordList(PageDomain page, TVaccineRecord vo) {
        Page<TVaccineRecord> pg = new Page<>(page.getPageNum(), page.getPageSize());
        return  vaccineRecordMapper.vaccineRecordList(pg,vo);
    }

    @Override
    public TVaccineRecord vaccineRecordAdd(TVaccineRecord entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            vaccineRecordUpdate(entity);
        }
        return entity;
    }

    @Override
    public int vaccineRecordDel(Long id) {
        return vaccineRecordMapper.deleteById(id);
    }

    @Override
    public TVaccineRecord vaccineRecordUpdate(TVaccineRecord entity) {
        vaccineRecordMapper.updateById(entity);
        return entity;
    }

    @Override
    public TVaccineRecord vaccineRecordDetail(TVaccineRecord entity) {
        return vaccineRecordMapper.selectById(entity.getId());
    }

    /**------通用方法开始结束-----------------------------------------*/

}
