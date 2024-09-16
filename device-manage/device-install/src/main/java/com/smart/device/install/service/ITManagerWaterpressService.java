package com.smart.device.install.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.common.core.page.PageDomain;
import com.smart.device.common.install.entity.TManagerWaterpress;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;

/**
 * @author f
 */
public interface ITManagerWaterpressService extends IService<TManagerWaterpress> {

    IPage<TManagerWaterpress> managerWaterpressList(PageDomain page, TManagerWaterpress entity);

    /**
     * 水力添加
     *
     * @param entity
     * @return
     */
    TManagerWaterpress managerWaterpressAdd(TManagerWaterpress entity);

    /**
     * 水力删除
     *
     * @param
     * @return
     */
    int managerWaterpressDel(Long id);

    /**
     * 水力修改
     *
     * @param entity
     * @return
     */
    TManagerWaterpress managerWaterpressUpdate(TManagerWaterpress entity);

    TManagerWaterpress selectWaterpressByID(Long id);

    TManagerWaterpress selectWaterpress(TManagerWaterpress entity);
    //  根据IMEI 号查询是否存在
    TManagerWaterpress getManagerWaterpress(TManagerWaterpress entity);

    DeviceCompanyVo waterpressPerSonByDeviceId(Long deviceId);

    void updateWaterpressStatus(TManagerWaterpress entity);


}
