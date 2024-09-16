package com.smart.device.common.service;

import com.smart.device.common.access.entity.vo.DeviceAccessVO;
import com.smart.device.common.entity.UserBean;
import com.smart.device.common.install.entity.*;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.install.entity.vo.DeviceMonitorVo;
import javax.servlet.http.HttpServletRequest;

public interface UserService{

    UserBean getUserByToken(HttpServletRequest request);

    UserBean setDataAuth(HttpServletRequest request,TBaseCompany entity);
    UserBean setDataAuth(HttpServletRequest request,DeviceCompanyVo entity);
    UserBean setDataAuth(HttpServletRequest request,TBaseBuilding entity);

    UserBean setDataAuth(HttpServletRequest request,TManagerSmoke entity);
    UserBean setDataAuth(HttpServletRequest request,TManagerElectric entity);
    UserBean setDataAuth(HttpServletRequest request,TManagerWaterpress entity);
    UserBean setDataAuth(HttpServletRequest request,TBaseFirehydrant entity);
    UserBean setDataAuth(HttpServletRequest request,TManagerCameras entity);

    UserBean setDataAuth(HttpServletRequest request, DeviceMonitorVo entity);
    UserBean setDataAuth(HttpServletRequest request, DeviceAccessVO entity);

}
