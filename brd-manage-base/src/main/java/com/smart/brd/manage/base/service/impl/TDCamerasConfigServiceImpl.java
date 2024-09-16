package com.smart.brd.manage.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.smart.brd.manage.base.common.exception.CustomException;
import com.smart.brd.manage.base.entity.TDCamerasConfig;
import com.smart.brd.manage.base.entity.TDeviceInstall;
import com.smart.brd.manage.base.entity.vo.DeviceVo;
import com.smart.brd.manage.base.mapper.TDCamerasConfigMapper;
import com.smart.brd.manage.base.mapper.TDeviceInstallMapper;
import com.smart.brd.manage.base.service.ITDCamerasConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.brd.manage.base.service.Video;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.page.PageDomain;
import javax.annotation.Resource;
import com.smart.common.utils.IdWorker;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */
@Service
public class TDCamerasConfigServiceImpl extends ServiceImpl<TDCamerasConfigMapper, TDCamerasConfig> implements ITDCamerasConfigService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TDCamerasConfigMapper tDCamerasConfigMapper;
    @Resource
    private TDeviceInstallMapper tDeviceInstallMapper;
    @Resource
    private Video video;

    private static final Integer RECORD_SIZE_1 = 6 ;
    private static final Integer CONFIG_SIZE_2 = 5 ;

    @Override
    public IPage<DeviceVo> tDCamerasConfigList(PageDomain page, TDCamerasConfig vo) {
        Page<DeviceVo> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<DeviceVo> iPage = tDCamerasConfigMapper.tDCamerasConfigList(pg,vo);
        List<DeviceVo> records = iPage.getRecords();
        List<DeviceVo> re = new ArrayList<>();
        for (DeviceVo record : records) {
            TDeviceInstall tDeviceInstall = tDeviceInstallMapper.selectOne(new LambdaQueryWrapper<TDeviceInstall>().eq(TDeviceInstall::getDeviceId, record.getDeviceId()).eq(TDeviceInstall::getDeleteFlag, 0));
            if (tDeviceInstall!=null){
                String channel = tDeviceInstall.getDeviceCode();
                String urlVlc = video.getUrlVlc(channel);
                record.setUrlVlc(urlVlc);
                record.setLocation(tDeviceInstall.getLocation());
                if (urlVlc==null){
                    re.add(record);
                }
            }
        }
        //过滤掉播放地址为空的设备
        records.removeAll(re);
        if(records != null && records.size() > RECORD_SIZE_1){
            records = records.subList(0,6);
        }
        iPage.setRecords(records);
        return iPage;
    }

    @Override
    @Transactional
    public TDCamerasConfig tDCamerasConfigAdd(TDCamerasConfig entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        if(entity.getId() == null ){
            List<TDCamerasConfig> tdCamerasConfigs1 = tDCamerasConfigMapper.selectList(new LambdaQueryWrapper<TDCamerasConfig>().eq(TDCamerasConfig::getDeviceId, entity.getDeviceId()).eq(TDCamerasConfig::getDeleteFlag, 0));
            if (!tdCamerasConfigs1.isEmpty()){
                throw new CustomException("该设备已添加，不能重复添加！");
            }
            List<TDCamerasConfig> tdCamerasConfigs = tDCamerasConfigMapper.selectList(new LambdaQueryWrapper<TDCamerasConfig>().in(TDCamerasConfig::getDeptId, entity.getDeptIds()).eq(TDCamerasConfig::getDeleteFlag, 0));
            if (tdCamerasConfigs.size()>CONFIG_SIZE_2){
                throw new CustomException("最多可添加六个，请重置之后重新添加！");
            }
            entity.setId(id);
            entity.setCreateTime(LocalDateTime.now());
            this.save(entity);


        }else{
            tDCamerasConfigUpdate(entity);
        }
        return entity;
    }

    @Override
    public int tDCamerasConfigDel(Long id) {
        return tDCamerasConfigMapper.deleteById(id);
    }

    @Override
    public TDCamerasConfig tDCamerasConfigUpdate(TDCamerasConfig entity) {
        tDCamerasConfigMapper.updateById(entity);
        return entity;
    }

    @Override
    public TDCamerasConfig tDCamerasConfigDetail(TDCamerasConfig entity) {
        TDCamerasConfig detail = tDCamerasConfigMapper.selectById(entity.getId());
        TDeviceInstall tDeviceInstall = tDeviceInstallMapper.selectOne(new LambdaQueryWrapper<TDeviceInstall>().eq(TDeviceInstall::getDeviceId, detail.getDeviceId()).eq(TDeviceInstall::getDeleteFlag, 0));
        if (tDeviceInstall!=null){
            String channel = tDeviceInstall.getDeviceCode();
            String urlVlc = video.getUrlVlc(channel);
            detail.setUrlVlc(urlVlc);
        }
        return detail;
    }

    /**------通用方法开始结束-----------------------------------------*/

}
