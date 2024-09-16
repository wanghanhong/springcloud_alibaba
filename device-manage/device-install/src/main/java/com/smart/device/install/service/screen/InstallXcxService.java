package com.smart.device.install.service.screen;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.common.core.page.PageDomain;
import com.smart.device.common.install.entity.TBaseBuilding;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.install.entity.vo.ScreenVo;

import java.util.List;

/**
 * @author f
 */
public interface InstallXcxService extends IService<DeviceCompanyVo> {

    IPage<DeviceCompanyVo> queryInstallDevicesList(PageDomain page,DeviceCompanyVo vo);


}
