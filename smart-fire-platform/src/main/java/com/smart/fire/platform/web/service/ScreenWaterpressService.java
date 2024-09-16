package com.smart.fire.platform.web.service;

import com.smart.device.common.install.entity.vo.ScreenProvinceVo;
import com.smart.device.common.install.entity.vo.ScreenVo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ScreenWaterpressService {

    List<ScreenVo> eventNumByCompanyWaterpress();

}
