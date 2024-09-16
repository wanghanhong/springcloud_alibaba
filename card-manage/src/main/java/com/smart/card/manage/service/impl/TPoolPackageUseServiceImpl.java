package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TPoolPackageUse;
import com.smart.card.manage.mapper.TPoolPackageUseMapper;
import com.smart.card.manage.service.ITPoolPackageUseService;
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
public class TPoolPackageUseServiceImpl extends ServiceImpl<TPoolPackageUseMapper, TPoolPackageUse> implements ITPoolPackageUseService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TPoolPackageUseMapper tPoolPackageUseMapper;

    @Override
    public IPage<TPoolPackageUse> tPoolPackageUseList(PageDomain page, TPoolPackageUse vo) {
        Page<TPoolPackageUse> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TPoolPackageUse> iPage = tPoolPackageUseMapper.tPoolPackageUseList(pg,vo);
        return iPage;
    }

    @Override
    public TPoolPackageUse tPoolPackageUseAdd(TPoolPackageUse entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
         if(entity.getPackageUseId() == null ){
            entity.setPackageUseId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tPoolPackageUseUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tPoolPackageUseDel(Long id) {
        int res = tPoolPackageUseMapper.deleteById(id);
        return res;
    }

    @Override
    public TPoolPackageUse tPoolPackageUseUpdate(TPoolPackageUse entity) {
        tPoolPackageUseMapper.updateById(entity);
        return entity;
    }

    @Override
    public TPoolPackageUse tPoolPackageUseDetail(TPoolPackageUse entity) {
        TPoolPackageUse detail = tPoolPackageUseMapper.selectById(entity.getPackageUseId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
