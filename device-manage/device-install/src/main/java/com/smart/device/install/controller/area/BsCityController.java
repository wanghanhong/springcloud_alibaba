package com.smart.device.install.controller.area;

import com.smart.device.common.install.entity.TBaseRegion;
import com.smart.device.install.service.area.IBsCityService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author f
 */
@RestController
@RequestMapping("/api/v2/install/base/city")
public class BsCityController {

    @Resource
    private IBsCityService iBsCityService;

    @ApiOperation(value = "根据省编码查询地级市")
    @ApiImplicitParam
    @GetMapping("/queryCitysByCode")
    public List<TBaseRegion> queryCitysByCode(String code){
        try {
            List<TBaseRegion> list = iBsCityService.queryCitysByCode(code);
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<TBaseRegion>();
        }

    }


}
