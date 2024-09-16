package com.smart.device.message.parse.fire.gas.wanlin;


import com.smart.device.message.factory.fire.DeviceParseFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 三多
 * @Time 2020/5/9
 */
@RestController
public class GasController {

    @Resource
    private DeviceParseFactory deviceParseFactory;
//    public GasController() {
//        this.deviceParseFactory = new FireDeviceParseFactory();
//    }

    @PostMapping("api/gas/test")
    public String test(String type, @RequestBody String data) {

        String result = deviceParseFactory.analysis(type, data);
        return result;
    }
}
