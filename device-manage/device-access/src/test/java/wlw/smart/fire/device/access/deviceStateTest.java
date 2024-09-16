package wlw.smart.fire.device.access;

import com.alibaba.excel.EasyExcel;
import com.smart.device.access.DeviceAccessApplication;
import com.smart.device.access.service.ITDeviceDictService;
import com.smart.device.access.timing.service.DeviceStatusService;
import com.smart.device.common.access.entity.TDeviceDict;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableDiscoveryClient
@EnableFeignClients
@ContextConfiguration(classes = DeviceAccessApplication.class)
public class deviceStateTest {

    @Resource
    private DeviceStatusService deviceStatusService;
    @Resource
    private ITDeviceDictService deviceDictService;

    @Test
    public void dateTest(){
        Date date = new Date(1608691642951L);
        System.out.println(date);
        System.out.println(12);
    }


}
