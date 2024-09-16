package wlw.smart.fire.utils;/*
package wlw.smart.fire.device.access.utils;

import com.iemp.common.entity.kafka.fireHydrant.FireService;
import com.iemp.common.entity.kafka.fireHydrant.FrieHydrantData;
import com.iemp.common.entity.kafka.fireHydrant.FrieHydrantDataRaw;
import com.szhq.data.common.constant.Constant;
import com.szhq.data.common.service.DataSendService;
import com.szhq.data.common.util.HexUtil;
import com.szhq.data.unity.model.DeviceDataRowSmoke;
import com.szhq.data.unity.service.W308Service;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class W308ServiceImpl implements W308Service {

    static Logger log = LoggerFactory.getLogger(W308ServiceImpl.class);

    @Autowired
    DataSendService dataSendService;


    @Override
    public void dealData(List<String> hexList, DeviceDataRowSmoke rawData) {
        FrieHydrantDataRaw frieHydrantDataRaw = new FrieHydrantDataRaw();
        BeanUtils.copyProperties(rawData, frieHydrantDataRaw);
        FireService fireService = new FireService();
        fireService.setServiceId("scm");
        fireService.setServiceType("scm");
        FrieHydrantData frieHydrantData = new FrieHydrantData();
        //1.解析header
        //包类型
        String type = hexList.get(0);
        //包序号
        String index = hexList.get(1);
        //版本号
        String version = hexList.get(2);
        //设备类型
        String dev = hexList.get(3);
        //生产厂商
        String manu = hexList.get(4);
        //原始包序号
        String initiator = hexList.get(5);
        //运营商
        String opt = hexList.get(6);
        //属性
        String attri = hexList.get(7) + hexList.get(8);
        //时间
        String timeHex = hexList.get(9) + hexList.get(10) + hexList.get(11) + hexList.get(12);
        //属性
        String attriBinary = HexUtil.hexToBinaryString(attri);

        if (StringUtils.isEmpty(attriBinary) || attriBinary.length() != 16) {
            return;
        }
        String dataLengthBinary = attriBinary.substring(7);
        //data的数据长度
        Integer dataLength = HexUtil.binaryToDecimal(dataLengthBinary);
        if (dataLength != 27) {
            return;
        }
        //转换后的时间
        String time = HexUtil.getCaculateData(timeHex);

        String imei = getImei(hexList);

        //2.解析通用数据包
        //通用数据上报
        long cmd = HexUtil.hexToLong(hexList.get(21) + hexList.get(22));
        //报警类型
        int alert = HexUtil.hexToLong(hexList.get(23)).intValue();
        //设备故障码
        long deviceCode = HexUtil.hexToLong(hexList.get(24));
        //设备模式
        int deviceMode = HexUtil.hexToLong(hexList.get(25)).intValue();
        //设备电池电压
        float deviceVolt = HexUtil.hexToLong(hexList.get(26) + hexList.get(27)) / 100f;
        //电池电量剩余--百分比
        int DevVoltper = HexUtil.hexToLong(hexList.get(28)).intValue();
        //设备摄氏温度
        int temp = HexUtil.hexToLong(hexList.get(29)).intValue() - 100;
        //小区信息
        int cellid = HexUtil.hexToLong(hexList.get(30) + hexList.get(31) + hexList.get(32) + hexList.get(33)).intValue();
        //信号强度信息
        int rsrp = (HexUtil.hexToLong(hexList.get(34)).intValue() * -1);

        String Smoketime = HexUtil.getCaculateData(hexList.get(35) + hexList.get(36) + hexList.get(37) + hexList.get(38));

        //最新自检时间
        String Selfdetecttime = HexUtil.getCaculateData(hexList.get(39) + hexList.get(40) + hexList.get(41) + hexList.get(42));
        //最新自检结果
        int selfdetectret = HexUtil.hexToLong(hexList.get(43)).intValue();
        //保留
        long Reserve = HexUtil.hexToLong(hexList.get(44) + hexList.get(45) + hexList.get(46) + hexList.get(47));

        frieHydrantData.setImei(imei);
        frieHydrantData.setCollettime(new Date(Long.parseLong(time)));
        frieHydrantData.setAlert(alert);
        frieHydrantData.setDevicecode(String.valueOf(deviceCode));
        frieHydrantData.setDeivemode(deviceMode);
        frieHydrantData.setDevicemode(deviceMode);
        frieHydrantData.setCellid(cellid);
        frieHydrantData.setRsrp(rsrp);
        //百分比
        frieHydrantData.setVolt(DevVoltper);
        //电压
        frieHydrantData.setVoltper(deviceVolt);
        frieHydrantData.setTemp(temp);
        frieHydrantData.setReverse(String.valueOf(Reserve));
        frieHydrantData.setSelfdetecttime(new Date(Long.parseLong(Selfdetecttime)));
        frieHydrantData.setSelfdetectret(selfdetectret);
        frieHydrantData.setSmokeTime(new Date(Long.parseLong(Smoketime)));
        fireService.setData(frieHydrantData);
        frieHydrantDataRaw.setService(fireService);
        dataSendService.sendData(Constant.TOPIC_WATER_PRESS, JSON.toJSONString(frieHydrantDataRaw));
    }



    private String getImei(List<String> hexList) {
        StringBuilder imei = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            char[] arr = hexList.get(13 + i).toCharArray();
            imei.append(arr[1]);
            if (i != 7) {
                imei.append(arr[0]);
            }
        }
        return imei.toString();
    }
}
*/
