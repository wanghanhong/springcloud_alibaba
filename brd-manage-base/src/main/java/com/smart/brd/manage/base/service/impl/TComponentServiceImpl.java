package com.smart.brd.manage.base.service.impl;

import com.smart.brd.manage.base.entity.TDeviceInstall;
import com.smart.brd.manage.base.entity.vo.ComponentVo;
import com.smart.brd.manage.base.entity.vo.PlaybackVideoVo;
import com.smart.brd.manage.base.entity.vo.PlaybackVo;
import com.smart.brd.manage.base.mapper.TDeviceInstallMapper;
import com.smart.brd.manage.base.mapper.TLivestockMapper;
import com.smart.brd.manage.base.service.IComponentService;
import com.smart.brd.manage.base.service.ITDeviceInstallService;
import com.smart.brd.manage.base.service.Video;
import com.smart.common.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TComponentServiceImpl implements IComponentService {
    @Resource
    private TLivestockMapper tLivestockMapper;
    @Resource
    private TDeviceInstallMapper tDeviceInstallMapper;
    @Resource
    private Video video;
    @Resource
    private ITDeviceInstallService itDeviceInstallService;
    @Override
    public ComponentVo getInfo(Long id) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (id!=null){
            TDeviceInstall tDeviceInstall = tDeviceInstallMapper.selectById(id);
            Long shedId = tDeviceInstall.getShedId();
            ComponentVo componentVo = new ComponentVo();
            int livestockNum=tLivestockMapper.getLivestockNum(shedId);
            if (livestockNum>=0){
                if (tDeviceInstall.getDeviceCode()!=null){
                    String channel = tDeviceInstall.getDeviceCode();
                    String urlVlc = video.getUrlVlc(channel);
                    if (StringUtils.isNull(urlVlc)){
                        PlaybackVideoVo vo = new PlaybackVideoVo();
                        vo.setPageNum("1");
                        vo.setPageSize("50");
                        vo.setDeviceCode(channel);
                        LocalDateTime time = LocalDateTime.now().minusDays(2L);
                        vo.setPlayStartTime(time.format(df));
                        vo.setPlayEndTime(LocalDateTime.now().format(df));
                        PlaybackVo playbackVo = itDeviceInstallService.playbackVideo(vo);
                        List<PlaybackVideoVo> list = playbackVo.getList();
                        if (list.size()>0){
                            urlVlc=list.get(0).getHttpUri();
                        }
                    }
                    componentVo.setUrlVlc(urlVlc);
                }
                int abnormalNum=tLivestockMapper.getAbnormalNum(shedId);
                componentVo.setAbnormalNum(abnormalNum);//异常数据
                componentVo.setLivestockNum(livestockNum);//存栏数据
                componentVo.setTemperature("-");//温度假数据 22℃
                componentVo.setHumidity("-");//湿度假数据 22%
                componentVo.setAmmonia("-");//氨气假数据 0.2mg/m³
                componentVo.setSulfide("-");//硫化氢假数据 10mg/m³
            }
            return componentVo;
        }
        return null;
    }
}
