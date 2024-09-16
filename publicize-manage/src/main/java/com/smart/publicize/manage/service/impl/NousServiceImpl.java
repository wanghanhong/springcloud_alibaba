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
import com.smart.publicize.manage.common.config.FdfsConfig;
import com.smart.publicize.manage.common.utils.Constant;
import com.smart.publicize.manage.mapper.NousMapper;
import com.smart.publicize.manage.entity.NousEntity;
import com.smart.publicize.manage.entity.NousEntityVo;
import com.smart.publicize.manage.service.NousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * USER: gll
 * DATE: 2020/5/22
 * TIME: 16:53
 * Describe:
 */
@Service
public class NousServiceImpl extends ServiceImpl<NousMapper, NousEntity> implements NousService {
    @Resource
    private NousMapper nousMapper;
    @Resource
    private FdfsConfig fdfsConfig;
    @Autowired
    private FastDfsUtil fastDfsUtil;

    public Result insert(NousEntity nousEntity) {
        IdWorker idWorker = new IdWorker(0, 0);
        //生成id
        nousEntity.setId(idWorker.nextId());
        //设置状态
        nousEntity.setStatus(Constant.STATUS);
        nousEntity.setCreateTime(new Date());
        if (nousMapper.insert(nousEntity) <= 0) {
            return Result.ERROR(ResultCode.PUBLICIZE_ADD_FAIL);
        }

        return Result.SUCCESS();
    }

    @Override
    public Result add(NousEntity nousEntity) throws IOException {
        nousEntity.setExamineStatus(Constant.EXAMINE_STATUS0);
        insert(nousEntity);
        return Result.SUCCESS();
    }

    @Override
    public Result del(NousEntity nousEntity) {
        //修改删除状态为1
        nousEntity.setStatus(Constant.DEL_STATUS);
        //修改时间
        nousEntity.setUpdateTime(new Date());
        LambdaQueryWrapper<NousEntity> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (nousEntity.getId() != null) {
            lambdaQueryWrapper.eq(NousEntity::getStatus, Constant.STATUS);
            lambdaQueryWrapper.eq(NousEntity::getId, nousEntity.getId());

        }
        NousEntity entity = this.baseMapper.selectOne(lambdaQueryWrapper);
        if (entity == null) {
            return Result.ERROR(ResultCode.PUBLICIZE_NOT_EXITS);
        }
        int i = this.baseMapper.updateById(nousEntity);
        String fileUrl = fdfsConfig.getResHost() + ":" + fdfsConfig.getStoragePort() + "/" + entity.getFileUrl();
        if (i > 0) {
            fastDfsUtil.deleteFile(fileUrl);
        } else {
            return Result.ERROR(ResultCode.PUBLICIZE_DELETE_FAIL);
        }


        return Result.SUCCESS();
    }

    //更新常识方法
    private Result upateFile(NousEntity nousEntity) {
        nousEntity.setUpdateTime(new Date());
        baseMapper.updateById(nousEntity);
        return Result.SUCCESS();
    }

    @Override
    public Result updateNous(NousEntity nousEntity, MultipartFile[] file) {

        if (file == null || file.length <= 0 ) {

            return upateFile(nousEntity);
        }
        if (file != null && file.length > 0) {
            for (int i = 0; i < file.length; i++) {
                String sqlPath = "";
                try {
                    sqlPath = fastDfsUtil.uploadFile(file[i]);
                } catch (Exception e) {
                    e.printStackTrace();
                    return Result.ERROR(ResultCode.PUBLICIZE_NOUS_FILE_FAIL);
                }
                nousEntity.setFileUrl(sqlPath);
                Result result = upateFile(nousEntity);
                if (result.getCode() != ResultCode.SUCCESS.getCode()) {
                    fastDfsUtil.deleteFile(sqlPath);
                    return result;
                }

            }
        }
        return Result.SUCCESS();
    }

    @Override
    public IPage<NousEntity> getNousInfo(Page page, NousEntity nousEntity) {
        LambdaQueryWrapper<NousEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (nousEntity.getExamineStatus() != null) {
            lambdaQueryWrapper.eq(NousEntity::getExamineStatus, nousEntity.getExamineStatus());
            lambdaQueryWrapper.eq(NousEntity::getStatus, Constant.STATUS);
        }
        IPage<NousEntity> nousPage = this.baseMapper.selectPage(page, lambdaQueryWrapper);
        List<NousEntity> list = nousPage.getRecords();
        list.stream().forEach(e->{
            if(StringUtils.isNotBlank(e.getFileUrl()) ){
                String fileUrl = fastDfsUtil.getResAccessUrl(e.getFileUrl());
                e.setFileUrl(fileUrl);
            }else {
                e.setFileUrl("");
            }
        });
        nousPage.setRecords(list);
        return nousPage;
    }

    @Override
    public Result examine(NousEntity nousEntity) {
        nousEntity.setExamineTime(new Date());
        if (this.baseMapper.updateById(nousEntity) <= 0) {
            return Result.ERROR(ResultCode.PUBLICIZE_EXAMINE_FAIL);
        }

        return Result.SUCCESS();
    }


    @Override
    public NousEntity getByIds(long id) {
        return this.baseMapper.selectById(id);
    }
}
