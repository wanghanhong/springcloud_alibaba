package com.smart.fire.platform.web.service;

import com.smart.device.common.install.entity.vo.ScreenProvinceVo;
import com.smart.fire.platform.web.entity.vo.ScreenCount;
import com.smart.fire.platform.web.entity.vo.WholeProvinceVo;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author: wueryong
 * @Date: 2020/6/9 15
 * @Description:
 */
@Service
public interface IWholeProvinceService {


    ScreenCount getAll();

    List<ScreenProvinceVo> getCity();


    List<WholeProvinceVo> getWholeProvinceDeviceInfo();
}
