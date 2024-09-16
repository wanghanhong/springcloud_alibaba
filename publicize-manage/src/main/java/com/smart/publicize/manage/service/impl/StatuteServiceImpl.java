package com.smart.publicize.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.utils.IdWorker;
import com.smart.common.utils.StringUtils;
import com.smart.common.utils.upload.FastDfsUtil;
import com.smart.publicize.manage.common.utils.Constant;
import com.smart.publicize.manage.entity.NousEntity;
import com.smart.publicize.manage.mapper.StatueMapper;
import com.smart.publicize.manage.entity.StatuteEntity;
import com.smart.publicize.manage.entity.StatuteVo;
import com.smart.publicize.manage.service.StatuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * USER: gll
 * DATE: 2020/5/22
 * TIME: 16:55
 * Describe:
 */
@Service
public class StatuteServiceImpl extends ServiceImpl<StatueMapper, StatuteEntity> implements StatuteService {

    @Autowired
    private FastDfsUtil fastDfsUtil;

    @Override
    public Result add(StatuteEntity statuteEntity) {
        IdWorker idWorker = new IdWorker(0, 0);
        //生成id
        statuteEntity.setId(idWorker.nextId());
        //法规添加时间
        statuteEntity.setCreateTime(new Date());
        try {
            this.baseMapper.insert(statuteEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.PUBLICIZE_ADD_FAIL);
        }
        return Result.SUCCESS();
    }

    @Override
    public Result del(StatuteEntity statuteEntity) {
        //修改删除状态为1
        statuteEntity.setStatus(Constant.DEL_STATUS);
        //修改时间
        statuteEntity.setUpdateTime(new Date());

        if (this.baseMapper.updateById(statuteEntity) <= 0) {
            return Result.ERROR(ResultCode.PUBLICIZE_DELETE_FAIL);
        }

        return Result.SUCCESS();
    }

    @Override
    public Result update(StatuteEntity statuteEntity) {
        statuteEntity.setUpdateTime(new Date());
        try {
            this.baseMapper.updateById(statuteEntity);
        } catch (Exception e) {
            return Result.ERROR(ResultCode.PUBLICIZE_UPDATE_FAIL);
        }
        return Result.SUCCESS();
    }

    @Override
    public IPage<StatuteEntity> getInfo(Page page, StatuteEntity statuteEntity) {
        LambdaQueryWrapper<StatuteEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (statuteEntity.getIssueDatetime() != null && StringUtils.isNotBlank(statuteEntity.getIssueDatetime())) {
            lambdaQueryWrapper.between(StatuteEntity::getIssueDatetime, statuteEntity.getIssueDatetime() + " 00:00:00", statuteEntity.getIssueDatetime() + " 23:59:59");
            lambdaQueryWrapper.eq(StatuteEntity::getStatus, Constant.STATUS);
        }
        IPage<StatuteEntity> statueVoPage = this.baseMapper.selectPage(page, lambdaQueryWrapper);
        return statueVoPage;
    }

    @Override
    public Result examine(StatuteEntity statuteEntity) {
        statuteEntity.setExamineTime(new Date());
        try {
            this.baseMapper.updateById(statuteEntity);
        } catch (Exception e) {
            return Result.ERROR(ResultCode.PUBLICIZE_EXAMINE_FAIL);
        }
        return Result.SUCCESS();
    }

    @Override
    public StatuteEntity getByIds(long id) {
        return this.baseMapper.selectById(id);
    }
}
