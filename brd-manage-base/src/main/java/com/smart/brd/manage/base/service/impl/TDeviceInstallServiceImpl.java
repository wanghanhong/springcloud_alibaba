package com.smart.brd.manage.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.brd.manage.base.common.exception.CustomException;
import com.smart.brd.manage.base.entity.*;
import com.smart.brd.manage.base.entity.vo.PlaybackVideoVo;
import com.smart.brd.manage.base.entity.vo.PlaybackVo;
import com.smart.brd.manage.base.mapper.TDeviceInstallMapper;
import com.smart.brd.manage.base.mapper.TDeviceMapper;
import com.smart.brd.manage.base.mapper.TLivestockBedMapper;
import com.smart.brd.manage.base.mapper.TLivestockShedMapper;
import com.smart.brd.manage.base.service.ITDeviceInstallService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.brd.manage.base.service.ITDeviceService;
import com.smart.brd.manage.base.service.PlaybackVideo;
import com.smart.brd.manage.base.service.Video;
import com.smart.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.core.page.PageDomain;
import javax.annotation.Resource;
import com.smart.common.utils.IdWorker;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author
 */
@Service
public class TDeviceInstallServiceImpl extends ServiceImpl<TDeviceInstallMapper, TDeviceInstall> implements ITDeviceInstallService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private TDeviceInstallMapper tDeviceInstallMapper;
    @Resource
    private TLivestockBedMapper tLivestockBedMapper;
    @Resource
    private TLivestockShedMapper tLivestockShedMapper;
    @Resource
    private ITDeviceService tdeviceService;
    @Resource
    private TDeviceMapper tDeviceMapper;
    @Resource
    private Video video;
    @Resource
    private PlaybackVideo playbackVideo;

    @Override
    public IPage<TDeviceInstall> tDeviceInstallList(PageDomain page, TDeviceInstall vo) {
        Page<TDeviceInstall> pg = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<TDeviceInstall> iPage = tDeviceInstallMapper.tDeviceInstallList(pg,vo);
        List<TDeviceInstall> records = iPage.getRecords();
//        for (TDeviceInstall record : records) {
//            String urlVlc = video.getUrlVlc(record.getDeviceCode());
//            record.setUrlVlc(urlVlc);
//        }
        iPage.setRecords(records);
        return iPage;
    }

    @Override
    @Transactional
    public TDeviceInstall tDeviceInstallAdd(TDeviceInstall entity) {
        IdWorker idWorker = new IdWorker(0, 0);
        Long id = idWorker.nextId();
        Long deviceId = idWorker.nextId();
//        TLivestockShed shed = tLivestockShedMapper.selectById(entity.getShedId());
//        TLivestockBed bed = tLivestockBedMapper.selectById(entity.getBedId());
//        entity.setShedName(shed.getShedName());
//        entity.setBedName(bed.getBedName());
        if(entity.getId() == null ){
            entity.setId(id);
            entity.setDeviceId(deviceId);
            entity.setCreateTime(LocalDateTime.now());
//            TDevice detail = tDeviceMapper.selectById(entity.getDeviceId());
//            int size = tDeviceInstallMapper.selectList(new LambdaQueryWrapper<TDeviceInstall>().eq(TDeviceInstall::getDeviceId, entity.getDeviceId()).eq(TDeviceInstall::getDeleteFlag, 0)).size();
//            if (size>0){
//                throw new CustomException(detail.getDeviceName()+"已存在，请重新选择");
//            }
//            entity.setDeviceType(detail.getDeviceType());
//            entity.setDeviceName(detail.getDeviceName());
//            entity.setDeviceCode(detail.getDeviceCode());
            if (this.save(entity)){
                TDevice device = new TDevice();
                device.setDeviceId(deviceId);
                device.setDeviceName(entity.getDeviceName());
                device.setDeviceCode(entity.getDeviceCode());
                device.setDeviceType(77);
                tdeviceService.save(device);
            }
        }else{
            tDeviceInstallUpdate(entity);
        }
        return entity;
    }

    public TDevice getTDeviceAttr(TDeviceInstall entity){
        TDevice device = new TDevice();
        device.setDeviceType(entity.getDeviceType());
        device.setDeviceCode(entity.getDeviceCode());
        device.setDeviceName(entity.getDeviceName());
        device = tdeviceService.tDeviceAdd(device);
        return device;
    }

    @Override
    public int tDeviceInstallDel(Long id) {
        TDeviceInstall tDeviceInstall = tDeviceInstallMapper.selectById(id);
        tDeviceInstall.setDeleteFlag(1);
        return tDeviceInstallMapper.updateById(tDeviceInstall);
    }

    @Override
    public TDeviceInstall tDeviceInstallUpdate(TDeviceInstall entity) {
//        //同时修改舍名和栏名
//        entity.setShedName(tLivestockShedMapper.selectById(entity.getShedId()).getShedName());
//        entity.setBedName(tLivestockBedMapper.selectById(entity.getBedId()).getBedName());
        TDeviceInstall tDeviceInstall = tDeviceInstallMapper.selectById(entity.getId());
        TDevice device = new TDevice();
        device.setDeviceId(tDeviceInstall.getDeviceId());
        device.setDeviceName(entity.getDeviceName());
        device.setDeviceCode(entity.getDeviceCode());
        tdeviceService.tDeviceUpdate(device);
        tDeviceInstallMapper.updateById(entity);
        return entity;
    }

    @Override
    public TDeviceInstall tDeviceInstallDetail(TDeviceInstall entity) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        TDeviceInstall detail = tDeviceInstallMapper.selectById(entity.getId());
        if (detail!=null){
            String channel = detail.getDeviceCode();
            String urlVlc = video.getUrlVlc(channel);
            if (StringUtils.isNull(urlVlc)){
                PlaybackVideoVo vo = new PlaybackVideoVo();
                vo.setPageNum("1");
                vo.setPageSize("50");
                vo.setDeviceCode(channel);
                LocalDateTime time = LocalDateTime.now().minusDays(2L);
                vo.setPlayStartTime(time.format(df));
                vo.setPlayEndTime(LocalDateTime.now().format(df));
                PlaybackVo playbackVo = playbackVideo(vo);
                List<PlaybackVideoVo> list = playbackVo.getList();
                if (list.size()>0){
                    urlVlc=list.get(0).getHttpUri();
                }
            }
            detail.setUrlVlc(urlVlc);
        }
        return detail;
    }

    @Override
    public PlaybackVo playbackVideo(PlaybackVideoVo vo) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String playStartTime = vo.getPlayStartTime();
        String playEndTime = vo.getPlayEndTime();
        //当前只能查询24小时之内的，如果传参不在24小时之内，就默认24小时的回放
        try{
            if (!LocalDateTime.parse(playStartTime,df).isBefore(LocalDateTime.now().minusDays(2L))){
                vo.setPlayStartTime(LocalDateTime.now().minusDays(2L).format(df));
            }
            if (LocalDateTime.parse(playEndTime,df).isBefore(LocalDateTime.now())){
                vo.setPlayEndTime(LocalDateTime.now().format(df));
            }
        }catch (Exception e){
            throw new CustomException("时间输入有误！");
        }
        if (StringUtils.isNull(vo.getPageSize())){
            vo.setPageSize("20");
        }
        if (StringUtils.isNull(vo.getPageNum())){
            vo.setPageNum("1");
        }
        return playbackVideo.getUrlList(vo);
    }

    @Override
    public List<TDeviceInstall> getList(TDeviceInstall entity) {
        QueryWrapper<TDeviceInstall> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(entity.getDeptIds())){
            String[] split = entity.getDeptIds().split(",");
            wrapper.in("dept_id", split);
        }
        wrapper.eq("delete_flag",0);
        return tDeviceInstallMapper.selectList(wrapper);
    }

    /**------通用方法开始结束-----------------------------------------*/

}
