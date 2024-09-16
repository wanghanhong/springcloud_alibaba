package com.smart.publicize.manage.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import com.smart.common.utils.DateUtil;
import com.smart.common.utils.IdWorker;
import com.smart.publicize.manage.common.utils.Constant;
import com.smart.publicize.manage.mapper.NoticeMapper;
import com.smart.publicize.manage.entity.NoticeEntity;
import com.smart.publicize.manage.entity.NoticeVo;
import com.smart.publicize.manage.service.NoticeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * USER: gll
 * DATE: 2020/5/22
 * TIME: 16:51
 * Describe:
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, NoticeEntity> implements NoticeService {

    @Resource
    NoticeMapper noticeMapper;

    @Override
    public IPage<NoticeVo> noticeList(Page page, NoticeEntity entity) {

        LambdaQueryWrapper<NoticeEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (entity.getReleaseTime() != null && StringUtils.isNotBlank(entity.getReleaseTime())) {
            queryWrapper.between(NoticeEntity::getReleaseTime, entity.getReleaseTime() + " 00:00:00", entity.getReleaseTime() + " 23:59:59");
            queryWrapper.eq(NoticeEntity::getStatus, Constant.STATUS);
        }
        IPage<NoticeVo> noticeVoIPageList = this.baseMapper.selectPage(page, queryWrapper);

        return noticeVoIPageList;


    }

    @Override
    public Result add(NoticeEntity entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        //生成id
        entity.setId(idWorker.nextId());
        //设置状态
        entity.setStatus(Constant.STATUS);
        //公告添加时间
        entity.setPublicStatus(Constant.NOT_PUBLIC_STATUS);
        entity.setExamineStatus(Constant.EXAMINE_STATUS0);
        entity.setCreateTime(new Date());
        if (entity.getReleaseType() == Constant.RELEASE_TYPE_ATONCE) {
            //发布类型为立即发布时发布时间取当前时间
            entity.setReleaseTime(getTime());
        }


        String roleId = entity.getRoleId();
        if (!"".equals(roleId) || StringUtils.isNotBlank(roleId)) {
            //roleId需要写入到资源中转表中
//        ArrayListUtils.stringToArray(roleId);
        }

        try {
            this.baseMapper.insert(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.PUBLICIZE_ADD_FAIL);
        }


        return Result.SUCCESS();
    }

    @Override
    public Result del(NoticeEntity entity) {
        //修改时间为当前时间
        entity.setUpdateTime(new Date());
        entity.setStatus(Constant.DEL_STATUS);

        if (this.baseMapper.updateById(entity) <= 0) {
            return Result.ERROR(ResultCode.PUBLICIZE_DELETE_FAIL);
        }

        return Result.SUCCESS();
    }

    @Override
    public Result update(NoticeEntity entity) {
        if (entity.getReleaseType() != null) {
            //修改时间为当前时间
            if (entity.getReleaseType() == Constant.RELEASE_TYPE_TIMING) {
                //发布类型为立即发布时发布时间取当前时间
                entity.setReleaseTime(getTime());
            }
        }
        entity.setUpdateTime(new Date());

        String roleId = entity.getRoleId();
        if (!"".equals(roleId) || StringUtils.isNotBlank(roleId)) {
            //roleId需要写入到资源中转表中
        }
        try {
            this.baseMapper.updateById(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.PUBLICIZE_UPDATE_FAIL);
        }

        return Result.SUCCESS();
    }

    @Override
    public Result getByIds(long id) {
        NoticeEntity noticeVo = null;
        try {
            noticeVo = noticeMapper.selectById(id);
        } catch (Exception e) {
            e.printStackTrace();
            Result.ERROR(ResultCode.FAIL);
        }
        return Result.SUCCESS(noticeVo);
    }

    @Override
    public Result examine(NoticeEntity entity) {
        entity.setExamineTime(new Date());
        if (entity.getExamineStatus().equals(Constant.EXAMINE_STATUS2)) {
            entity.setPublicStatus(Constant.PUBLIC_STATUS);
        }
        try {
            this.baseMapper.updateById(entity);
        } catch (Exception e) {
            e.printStackTrace();
            Result.ERROR(ResultCode.PUBLICIZE_EXAMINE_FAIL);
        }

        return Result.SUCCESS();
    }

    @Override
    public List<NoticeEntity> queryNoticeListLimit() {
        List<NoticeEntity> list = noticeMapper.queryNoticeListLimit();
        return list;
    }

    private String getTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }
}
