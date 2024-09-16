package com.smart.device.install.service.screen.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.common.core.page.PageDomain;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.message.vo.AlarmVo;
import com.smart.device.install.mapper.screen.InstallXcxScreenMapper;
import com.smart.device.install.service.screen.InstallXcxService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


/**
 * @author f
 */
@Service
public class InstallXcxServiceImpl extends ServiceImpl<InstallXcxScreenMapper, DeviceCompanyVo> implements InstallXcxService {

    /**------通用方法开始-----------------------------------------*/
    @Resource
    private InstallXcxScreenMapper installXcxScreenMapper;


    @Override
    public IPage<DeviceCompanyVo> queryInstallDevicesList(PageDomain page, DeviceCompanyVo vo) {
        int start = 0;
        int limit = 10;
        if(page.getPageSize() != null){
            limit = page.getPageSize();
        }
        if(page.getPageNum()  != null){
            start = limit*(page.getPageNum()-1);
        }

        List<DeviceCompanyVo> list = installXcxScreenMapper.queryInstallDevicesList(vo,start,limit);
        Long countx = installXcxScreenMapper.queryInstallDevicesListCount(vo);

        IPage<DeviceCompanyVo> iPage = new Page<>();
        iPage.setRecords(list);
        iPage.setTotal(countx);
        return iPage;
    }



}
