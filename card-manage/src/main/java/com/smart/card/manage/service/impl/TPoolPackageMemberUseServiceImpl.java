package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TPoolPackageMemberUse;
import com.smart.card.manage.mapper.TPoolPackageMemberUseMapper;
import com.smart.card.manage.service.ITPoolPackageMemberUseService;
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
public class TPoolPackageMemberUseServiceImpl extends ServiceImpl<TPoolPackageMemberUseMapper, TPoolPackageMemberUse> implements ITPoolPackageMemberUseService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TPoolPackageMemberUseMapper tPoolPackageMemberUseMapper;

    @Override
    public IPage<TPoolPackageMemberUse> tPoolPackageMemberUseList(PageDomain page, TPoolPackageMemberUse vo) {
        Page<TPoolPackageMemberUse> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TPoolPackageMemberUse> iPage = tPoolPackageMemberUseMapper.tPoolPackageMemberUseList(pg,vo);
        return iPage;
    }

    @Override
    public TPoolPackageMemberUse tPoolPackageMemberUseAdd(TPoolPackageMemberUse entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tPoolPackageMemberUseUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tPoolPackageMemberUseDel(Long id) {
        int res = tPoolPackageMemberUseMapper.deleteById(id);
        return res;
    }

    @Override
    public TPoolPackageMemberUse tPoolPackageMemberUseUpdate(TPoolPackageMemberUse entity) {
        tPoolPackageMemberUseMapper.updateById(entity);
        return entity;
    }

    @Override
    public TPoolPackageMemberUse tPoolPackageMemberUseDetail(TPoolPackageMemberUse entity) {
        TPoolPackageMemberUse detail = tPoolPackageMemberUseMapper.selectById(entity.getId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
