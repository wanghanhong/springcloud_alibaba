package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.TDeviceInstall;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.brd.manage.base.entity.vo.PlaybackVideoVo;
import com.smart.brd.manage.base.entity.vo.PlaybackVo;
import com.smart.common.core.page.PageDomain;

import java.util.List;

/**
 * @author 
 */
public interface ITDeviceInstallService extends IService<TDeviceInstall> {

    /**------基本方法开始-----------------------------------------*/
    IPage<TDeviceInstall> tDeviceInstallList(PageDomain page,TDeviceInstall entity);

    TDeviceInstall tDeviceInstallAdd(TDeviceInstall entity);

    TDeviceInstall tDeviceInstallUpdate(TDeviceInstall entity);

    int tDeviceInstallDel(Long id);

    TDeviceInstall tDeviceInstallDetail(TDeviceInstall entity);

    PlaybackVo playbackVideo(PlaybackVideoVo vo);

    List<TDeviceInstall> getList(TDeviceInstall entity);
    /**------基本方法结束-----------------------------------------*/

}
