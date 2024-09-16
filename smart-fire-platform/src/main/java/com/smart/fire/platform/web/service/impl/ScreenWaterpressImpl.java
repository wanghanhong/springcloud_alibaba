package com.smart.fire.platform.web.service.impl;

import com.smart.device.common.install.entity.vo.ScreenVo;
import com.smart.fire.platform.web.service.ScreenWaterpressService;
import com.smart.fire.platform.web.feign.DeviceInstallFeign;
import com.smart.fire.platform.web.feign.DeviceMessageFeign;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ScreenWaterpressImpl implements ScreenWaterpressService {

    @Resource
    private DeviceInstallFeign deviceInstallFeignService;
    @Resource
    private DeviceMessageFeign deviceMessageFeignService;

    @Override
    public List<ScreenVo> eventNumByCompanyWaterpress() {
        List<ScreenVo> list = deviceMessageFeignService.eventNumByCompanyWaterpress();
        List<ScreenVo> deviceNumlist = deviceInstallFeignService.deviceWaterpressNumByCompany();
        Map<Long,ScreenVo> map = deviceNumlist.stream().collect(Collectors.toMap(ScreenVo::getCompanyId, Function.identity(),(key1, key2)->key2));
        list.stream().forEach(e->{
            e.setTotalNum(map.get(e.getCompanyId()).getCountNum());
        });
        return list;
    }

}
