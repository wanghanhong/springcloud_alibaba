package com.smart.device.install.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.common.core.page.PageDomain;
import com.smart.device.common.access.entity.TDeviceElectric;
import com.smart.device.common.install.entity.TManagerElectric;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;

/**
 * @author 
 */
public interface ITManagerElectricService extends IService<TManagerElectric> {

    IPage<TManagerElectric> managerElectricList(PageDomain page, TManagerElectric entity);

    /**
     * 电力添加
     *
     * @param entity
     * @return
     */
    TManagerElectric managerElectricAdd(TManagerElectric entity);

    /**
     * 电力删除
     *
     * @param
     * @return
     */
    int managerElectricDel(Long id);

    /**
     * 电力修改
     *
     * @param entity
     * @return
     */
    TManagerElectric managerElectricUpdate(TManagerElectric entity);

    TManagerElectric selectElectricByID(Long id);

    TManagerElectric selectElectric(TManagerElectric entity);
    //  根据IMEI 号查询是否存在
    TManagerElectric getManagerElectric(TManagerElectric entity);

    DeviceCompanyVo electricPerSonByDeviceId(Long deviceId);

    void updateElectricStatus(TManagerElectric entity);
}
