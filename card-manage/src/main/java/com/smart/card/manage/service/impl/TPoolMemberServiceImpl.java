package com.smart.card.manage.service.impl;

import com.smart.card.manage.entity.TPoolMember;
import com.smart.card.manage.mapper.TPoolMemberMapper;
import com.smart.card.manage.service.ITPoolMemberService;
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
public class TPoolMemberServiceImpl extends ServiceImpl<TPoolMemberMapper, TPoolMember> implements ITPoolMemberService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TPoolMemberMapper tPoolMemberMapper;

    @Override
    public IPage<TPoolMember> tPoolMemberList(PageDomain page, TPoolMember vo) {
        Page<TPoolMember> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TPoolMember> iPage = tPoolMemberMapper.tPoolMemberList(pg,vo);
        return iPage;
    }

    @Override
    public TPoolMember tPoolMemberAdd(TPoolMember entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
         if(entity.getPoolMemberId() == null ){
            entity.setPoolMemberId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);
        }else{
            tPoolMemberUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tPoolMemberDel(Long id) {
        int res = tPoolMemberMapper.deleteById(id);
        return res;
    }

    @Override
    public TPoolMember tPoolMemberUpdate(TPoolMember entity) {
        tPoolMemberMapper.updateById(entity);
        return entity;
    }

    @Override
    public TPoolMember tPoolMemberDetail(TPoolMember entity) {
        TPoolMember detail = tPoolMemberMapper.selectById(entity.getPoolMemberId());
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
