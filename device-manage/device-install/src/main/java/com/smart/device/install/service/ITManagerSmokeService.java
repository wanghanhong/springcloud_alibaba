package com.smart.device.install.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.common.core.page.PageDomain;
import com.smart.device.common.install.entity.TManagerSmoke;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;

/**
 * @author f
 */
public interface ITManagerSmokeService extends IService<TManagerSmoke> {

    IPage<TManagerSmoke> managerSmokeList(PageDomain page, TManagerSmoke entity);

    /**
     * 烟感添加
     *
     * @param entity
     * @return
     */
    TManagerSmoke managerSmokeAdd(TManagerSmoke entity);
    String managerSmokeAddSP(TManagerSmoke entity);
    /**
     * 烟感删除
     *
     * @param
     * @return
     */
    int managerSmokeDel(Long id);

    /**
     * 烟感修改
     *
     * @param entity
     * @return
     */
    TManagerSmoke managerSmokeUpdate(TManagerSmoke entity);

    TManagerSmoke selectSmokeByID(Long id);

    TManagerSmoke selectSmoke(TManagerSmoke entity);
    //  根据IMEI 号查询是否存在
    TManagerSmoke getManagerSmoke(TManagerSmoke entity);

    DeviceCompanyVo smokePerSonByDeviceId(Long deviceId);

    void updateSmokeStatus(TManagerSmoke entity);

}
