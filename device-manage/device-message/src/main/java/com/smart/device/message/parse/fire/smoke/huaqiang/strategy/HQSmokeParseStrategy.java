package com.smart.device.message.parse.fire.smoke.huaqiang.strategy;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.message.common.utils.DateUtils;
import com.smart.device.message.common.utils.HexUtil;
import com.smart.device.message.parse.fire.ParseStrategy;
import com.smart.device.message.parse.fire.smoke.huaqiang.constant.SmokeEnum;
import com.smart.device.message.parse.fire.smoke.huaqiang.constant.SmokeMapConstant;
import com.smart.device.message.parse.fire.smoke.huaqiang.entity.DeviceEntity;
import com.smart.device.message.parse.fire.smoke.huaqiang.entity.DeviceEntityVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 烟感通用数据解析类
 *
 * @author 三多
 * @Time 2020/4/8
 */
@Component
@Slf4j
public class HQSmokeParseStrategy implements ParseStrategy<DeviceEntity> {

    /**
     * {"IMEI":"860803031745975","IMSI":"undefined","deviceType":"","messageType":"dataReport","topic":"v1/up/ad","assocAssetId":"","payload":{"APPdata":"UFUAMBAKLwgBAAMAGwL75SxogDAwcVR5BQgAAAAAAS9kdwJVcVJZAvmvgAL75SwAAAAAAA=="},"upPacketSN":"","upDataSN":"","serviceId":"","tenantId":"10461180","productId":"10045600","deviceId":"f15d8b0a090447539896c085e27966c4","timestamp":1586571442454,"protocol":"lwm2m"}
     *
     *
     *
     * @param
     * @return 返回值
     */
    @Override
    public DeviceEntity assemblyData(String dataStr) {
        JSONObject obj = JSONObject.parseObject(dataStr);

        String IMEI = String.valueOf(obj.get("IMEI"));// NB终端设备识别号
        String IMSI = String.valueOf(obj.get("IMSI"));// NB终端sim卡标识
        JSONObject payload = obj.getJSONObject("payload");
        String APPdata = String.valueOf(payload.get("APPdata"));

        List<String> hexList = parseBase64Str(APPdata);
        DeviceEntity entity = new DeviceEntity();
        //1.解析header
        //包类型
        String type = hexList.get(0);
        entity.setType(type);
        //包序号
        String index = hexList.get(1);
        entity.setIndex(index);
        //版本号
        String version = hexList.get(2);
        entity.setVer(version);
        //设备类型
        String dev = hexList.get(3);
        entity.setDev(dev);
        //生产厂商
        String manu = hexList.get(4);
        entity.setManu(manu);
        //原始包序号
        String initiator = hexList.get(5);
        entity.setInitiator(initiator);
        //运营商
        String opt = hexList.get(6);
        entity.setOpt(opt);
        //属性
        // TODO: 2020/5/12 为什么二进制
        String attri = hexList.get(7) + hexList.get(8);
        entity.setAttri(attri);
        //时间
        String timeHex = hexList.get(9) + hexList.get(10) + hexList.get(11) + hexList.get(12);
        //属性
        // TODO: 2020/5/12 为什么二进制  1001100100011111
        String attriBinary = HexUtil.hexToBinaryString(attri);
        entity.setAttri(attriBinary);

        if (StringUtils.isEmpty(attriBinary) || attriBinary.length() != 16) {
            return new DeviceEntity();
        }
        String dataLengthBinary = attriBinary.substring(6);
        /**
         * 加密方式
         */
        String encryptType = attriBinary.substring(4, 6);
        entity.setEncryptType(encryptType);
        //data的数据长度  287
        Integer dataLength = HexUtil.binaryToDecimal(dataLengthBinary);
        int length = 27;
        if (dataLength != length) {
            return new DeviceEntity();
        }
        entity.setDataLength(dataLength);
        //转换后的时间
        String time = HexUtil.getCaculateData(timeHex);
        entity.setTime(time);
        String imei = getImei(hexList);
        entity.setImei(imei);
        //2.解析通用数据包
        //通用数据上报
        long cmd = HexUtil.hexToLong(hexList.get(21) + hexList.get(22));
        entity.setCmd(cmd);
        //报警类型
        int alert = HexUtil.hexToLong(hexList.get(23)).intValue();
        entity.setAlert(alert);
        //设备故障码
        long deviceCode = HexUtil.hexToLong(hexList.get(24));
        entity.setDevCode(deviceCode);
        //设备模式
        int deviceMode = HexUtil.hexToLong(hexList.get(25)).intValue();
        entity.setDevMode(deviceMode);
        //设备电池电压
        float deviceVolt = HexUtil.hexToLong(hexList.get(26) + hexList.get(27)) / 100f;
        entity.setDevVolt(deviceVolt);
        //电池电量剩余--百分比
        int devVoltPer = HexUtil.hexToLong(hexList.get(28)).intValue();
        entity.setDevVoltPer(devVoltPer);
        //设备摄氏温度
        int temp = HexUtil.hexToLong(hexList.get(29)).intValue() - 100;
        entity.setDevTemp(temp);
        //小区信息
        int cellId = HexUtil.hexToLong(hexList.get(30) + hexList.get(31) + hexList.get(32) + hexList.get(33)).intValue();
        entity.setCellId(cellId);
        //信号强度信息
        int rsrp = (HexUtil.hexToLong(hexList.get(34)).intValue() * -1);
        entity.setRsrp(rsrp);

        String smokeTime = HexUtil.getCaculateData(hexList.get(35) + hexList.get(36) + hexList.get(37) + hexList.get(38));
        entity.setSmokeTime(smokeTime);
        //最新自检时间
        String selfDetectTime = HexUtil.getCaculateData(hexList.get(39) + hexList.get(40) + hexList.get(41) + hexList.get(42));
        entity.setSelfDetectTime(selfDetectTime);
        //最新自检结果
        int selfDetectRet = HexUtil.hexToLong(hexList.get(43)).intValue();
        entity.setSelfDetectRet(selfDetectRet);
        //保留
        long reserve = HexUtil.hexToLong(hexList.get(44) + hexList.get(45) + hexList.get(46) + hexList.get(47));
        entity.setReserve(reserve);
        return entity;
    }

    /**
     * 参考数据：
     * type=10, index=02, ver=2F, dev=08, manu=01, initiator=00, opt=03, attri=0000000000011011,
     * dataLength=27, encryptType=00, time=49734088, imei=865484029251022, cmd=2048, alert=0,
     * devCode=0, devMode=0, devVolt=2.91, devVoltPer=65, devTemp=24, cellId=128502100, rsrp=-92,
     * smokeTime=0, selfDetectTime=49734088, selfDetectRet=0, reserve=0
     * 将数据字典转换成可以识别的表达式
     *
     * @param deviceEntity 设备实体
     * @return 转换后的设备实体
     */
    public DeviceEntityVo convertVo(DeviceEntity deviceEntity) {
        DeviceEntityVo entity = new DeviceEntityVo();
        //目前
        //header
        entity.setType(SmokeMapConstant.SMOKE_MAP.get(SmokeEnum.TYPE).get(deviceEntity.getType()));
        entity.setIndex(deviceEntity.getIndex());
        entity.setVer(SmokeMapConstant.SMOKE_MAP.get(SmokeEnum.VERSION).get(deviceEntity.getVer()));
        entity.setDev(SmokeMapConstant.SMOKE_MAP.get(SmokeEnum.DEV).get(deviceEntity.getDev()));
        entity.setManu(SmokeMapConstant.SMOKE_MAP.get(SmokeEnum.MANU).get(deviceEntity.getManu()));
        entity.setInitiator(deviceEntity.getInitiator());
        entity.setOpt(SmokeMapConstant.SMOKE_MAP.get(SmokeEnum.OPT).get(deviceEntity.getOpt()));
        entity.setAttri(deviceEntity.getAttri());
        entity.setDataLength(deviceEntity.getDataLength());
        entity.setEncryptType(SmokeMapConstant.SMOKE_MAP.get(SmokeEnum.ENCRYPT).get(deviceEntity.getEncryptType()));
        entity.setTime(DateUtils.covertTimeDeviceToSystem(Long.valueOf(deviceEntity.getTime())));
        entity.setImei(deviceEntity.getImei());
        //body
        entity.setCmd(deviceEntity.getCmd());
        entity.setAlert(deviceEntity.getAlert());
        String devCode = SmokeMapConstant.SMOKE_MAP.get(SmokeEnum.DEV_CODE).get(String.valueOf(deviceEntity.getDevCode()));
        entity.setDevCode(devCode);
        String devMode = SmokeMapConstant.SMOKE_MAP.get(SmokeEnum.DEV_MODE).get(String.valueOf(deviceEntity.getDevMode()));
        entity.setDevMode(devMode);
        entity.setDevVolt(deviceEntity.getDevVolt());
        entity.setDevVoltPer(deviceEntity.getDevVoltPer());
        entity.setDevTemp(deviceEntity.getDevTemp());
        entity.setCellId(deviceEntity.getCellId());
        entity.setRsrp(deviceEntity.getRsrp());
        entity.setSmokeTime(DateUtils.covertTimeDeviceToSystem(Long.valueOf(deviceEntity.getSmokeTime())));
        entity.setSelfDetectTime(DateUtils.covertTimeDeviceToSystem(Long.valueOf(deviceEntity.getSelfDetectTime())));
        entity.setSelfDetectRet(deviceEntity.getSelfDetectRet());
        entity.setReserve(deviceEntity.getReserve());
//        log.info("华强NB烟感-参数格式化" + JSONObject.toJSONString(entity));
        return entity;
    }


}
