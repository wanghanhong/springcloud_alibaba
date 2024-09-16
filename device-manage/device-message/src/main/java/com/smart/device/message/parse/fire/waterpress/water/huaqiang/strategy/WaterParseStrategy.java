package com.smart.device.message.parse.fire.waterpress.water.huaqiang.strategy;

import com.alibaba.fastjson.JSONObject;
import com.smart.device.common.message.entity.THeartWaterpressSon;
import com.smart.device.message.common.utils.DateUtils;
import com.smart.device.message.common.utils.HexUtil;
import com.smart.device.message.parse.fire.ParseStrategy;
import com.smart.device.message.parse.fire.waterpress.water.constant.WaterEnum;
import com.smart.device.message.parse.fire.waterpress.water.constant.WaterMapConstant;
import com.smart.device.message.parse.fire.waterpress.water.huaqiang.entity.WaterEntity;
import com.smart.device.message.parse.fire.waterpress.water.huaqiang.entity.WaterEntityVo;
import com.smart.device.message.parse.fire.waterpress.water.huaqiang.util.WaterUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 烟感通用数据解析类
 *
 * @author 三多
 * @Time 2020/4/8
 */
@Component
@Slf4j
public class WaterParseStrategy implements ParseStrategy<WaterEntity> {

    /**
     * @param
     * @return 返回值
     */
    @Override
    public WaterEntity assemblyData(String dataStr) {
        JSONObject obj = JSONObject.parseObject(dataStr);

        String IMEI = String.valueOf(obj.get("IMEI"));// NB终端设备识别号
        String IMSI = String.valueOf(obj.get("IMSI"));// NB终端sim卡标识
        JSONObject payload = obj.getJSONObject("payload");
        String APPdata = String.valueOf(payload.get("APPdata"));

        List<String> hexList = parseBase64Str(APPdata);
        WaterEntity entity = new WaterEntity();
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
        // TODO: 2020/5/12 为什么二进制
        String attriBinary = HexUtil.hexToBinaryString(attri);
        entity.setAttri(attriBinary);

        if (StringUtils.isEmpty(attriBinary) || attriBinary.length() != 16) {
            return new WaterEntity();
        }
        String dataLengthBinary = attriBinary.substring(6);
        /*
         * 加密方式
         */
        String encryptType = attriBinary.substring(4, 6);
        entity.setEncryptType(encryptType);
        //data的数据长度
        Integer dataLength = HexUtil.binaryToDecimal(dataLengthBinary);
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
        // 采集间隔时间单位
        int colletUint = HexUtil.hexToLong(hexList.get(35)).intValue();
        entity.setColletUint(colletUint);
        // 采集间隔时间值
        int colletValue = HexUtil.hexToLong(hexList.get(36)).intValue();
        entity.setColletValue(colletValue);
        // 上传间隔时间单位
        int transUint = HexUtil.hexToLong(hexList.get(37)).intValue();
        entity.setTransUint(transUint);
        //  上传间隔时间值
        int transValue = HexUtil.hexToLong(hexList.get(38)).intValue();
        entity.setTransValue(transValue);
        // 设备水压低压报警门限
        int wplowThd = HexUtil.hexToLong(hexList.get(39) + hexList.get(40) + hexList.get(41) + hexList.get(42)).intValue();
        entity.setWplowThd(wplowThd);
        // 设备水压高压报警门限
        int wphighThd = HexUtil.hexToLong(hexList.get(43) + hexList.get(44) + hexList.get(45) + hexList.get(46)).intValue();
        entity.setWphighThd(wphighThd);
        //保留
        long reserve = HexUtil.hexToLong(hexList.get(47) + hexList.get(48) + hexList.get(49) + hexList.get(50));
        entity.setReserve(reserve);
        // 水压数据包数量
        int wpNum = HexUtil.hexToLong(hexList.get(51)).intValue();
        entity.setWpNum(wpNum);
        if (wpNum > 48) {
            return new WaterEntity();
        }
        // 根据水压报数量，判断走几条取值。
        Map<String, String> keyMap = new HashMap<>();
        for (int i = 0; i < wpNum; i++) {
            keyMap.put("wpTime" + (i+1) , DateUtils.covertTimeDeviceToSystem(Long.parseLong(HexUtil.getCaculateData(hexList.get(52 + 8 * i) + hexList.get(52 + 8 * i + 1) + hexList.get(52 + 8 * i + 2) + hexList.get(52 + 8 * i + 3)))));
            keyMap.put("wpData" + (i+1) , String.valueOf(HexUtil.hexToLong(hexList.get(52 + 8 * i + 4) + hexList.get(52 + 8 * i + 5) + hexList.get(52 + 8 * i + 6) + hexList.get(52 + 8 * i + 7)).intValue()));
        }
        if(wpNum == 1) {
            entity.setWpData1(Integer.parseInt(keyMap.get("wpData1")));
            entity.setWpTime1(keyMap.get("wpTime1"));
        }
        if(wpNum > 1){
            entity.setWpData2(Integer.parseInt(keyMap.get("wpData2")));
            entity.setWpTime2(keyMap.get("wpTime2"));
        }
        if(wpNum > 2){
            entity.setWpData3(Integer.parseInt(keyMap.get("wpData3")));
            entity.setWpTime3(keyMap.get("wpTime3"));
        }
        if(wpNum > 3){
            entity.setWpData4(Integer.parseInt(keyMap.get("wpData4")));
            entity.setWpTime4(keyMap.get("wpTime4"));
        }
        if (wpNum <= 4){
            entity.setWpSon(null);
        } else{
            try {
                THeartWaterpressSon son = new THeartWaterpressSon();
                WaterUtil.setFieldValue(son, keyMap);
                entity.setWpSon(son);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return entity;
    }

    /**
     * 参考数据：
     * type=10, index=02, ver=2F, dev=08, manu=01, initiator=00, opt=03, attri=0000000000011011,
     * dataLength=27, encryptType=00, time=49734088, imei=865484029251022, cmd=1536, alert=0,
     * devCode=0, devMode=0, devVolt=2.91, devVoltPer=65, devTemp=24, cellId=128502100, rsrp=-92,
     * colletUint=秒, colletValue=0, transUint=秒, transValue=0, wpLowPhd=70, wpHighPhd=0, reserve=0, wpNum=48
     * 将数据字典转换成可以识别的表达式
     *
     * @param deviceEntity 设备实体
     * @return 转换后的设备实体
     */
    public WaterEntityVo convertVo(WaterEntity deviceEntity) {
        WaterEntityVo entity = new WaterEntityVo();
        //目前
        //header
        entity.setType(WaterMapConstant.HQ_MAP.get(WaterEnum.TYPE).get(deviceEntity.getType()));
        entity.setIndex(deviceEntity.getIndex());
        entity.setVer(WaterMapConstant.HQ_MAP.get(WaterEnum.VERSION).get(deviceEntity.getVer()));
        entity.setDev(WaterMapConstant.HQ_MAP.get(WaterEnum.DEV).get(deviceEntity.getDev()));
        entity.setManu(WaterMapConstant.HQ_MAP.get(WaterEnum.MANU).get(deviceEntity.getManu()));
        entity.setInitiator(deviceEntity.getInitiator());
        entity.setOpt(WaterMapConstant.HQ_MAP.get(WaterEnum.OPT).get(deviceEntity.getOpt()));
        entity.setAttri(deviceEntity.getAttri());
        entity.setDataLength(deviceEntity.getDataLength());
        entity.setEncryptType(WaterMapConstant.HQ_MAP.get(WaterEnum.ENCRYPT).get(deviceEntity.getEncryptType()));
        entity.setTime(DateUtils.covertTimeDeviceToSystem(Long.parseLong(deviceEntity.getTime())));
        entity.setImei(deviceEntity.getImei());
        //body
        entity.setCmd(WaterMapConstant.HQ_MAP.get(WaterEnum.DATA_TYPE).get(String.valueOf(deviceEntity.getCmd())));
        entity.setAlert(deviceEntity.getAlert());

        String devCode = WaterMapConstant.HQ_MAP.get(WaterEnum.DEV_CODE).get(String.valueOf(deviceEntity.getDevCode()));
        entity.setDevCode(devCode);
        String devMode = WaterMapConstant.HQ_MAP.get(WaterEnum.DEV_MODE).get(String.valueOf(deviceEntity.getDevMode()));
        entity.setDevMode(devMode);
        entity.setDevVolt(deviceEntity.getDevVolt());
        entity.setDevVoltPer(deviceEntity.getDevVoltPer());
        entity.setDevTemp(deviceEntity.getDevTemp());
        entity.setCellId(deviceEntity.getCellId());
        entity.setRsrp(deviceEntity.getRsrp());
        entity.setColletUint(WaterMapConstant.HQ_MAP.get(WaterEnum.UINT).get(String.valueOf(deviceEntity.getColletUint())));
        entity.setColletValue(deviceEntity.getColletValue());
        entity.setTransUint(WaterMapConstant.HQ_MAP.get(WaterEnum.UINT).get(String.valueOf(deviceEntity.getTransUint())));
        entity.setTransValue(deviceEntity.getTransValue());
        entity.setWpLowPhd(deviceEntity.getWplowThd());
        entity.setWpHighPhd(deviceEntity.getWphighThd());
        entity.setReserve(deviceEntity.getReserve());
        entity.setWpNum(deviceEntity.getWpNum());
        entity.setWpSon(deviceEntity.getWpSon());
        entity.setWpData1(deviceEntity.getWpData1());
        entity.setWpTime1(deviceEntity.getWpTime1());
        entity.setWpData2(deviceEntity.getWpData2());
        entity.setWpTime2(deviceEntity.getWpTime2());
        entity.setWpData3(deviceEntity.getWpData3());
        entity.setWpTime3(deviceEntity.getWpTime3());
        entity.setWpData4(deviceEntity.getWpData4());
        entity.setWpTime4(deviceEntity.getWpTime4());

        log.info("华强消防栓设备-参数格式化" + JSONObject.toJSONString(entity));
        return entity;
    }


    public static void main(String[] args) {
        String data = "{\"upPacketSN\":\"\",\"upDataSN\":\"\",\"topic\":\"v1/up/ad\",\"timestamp\":1589444235861,\"tenantId\":\"10461180\",\"serviceId\":\"\",\"protocol\":\"lwm2m\",\"productId\":\"10053421\",\"payload\":{\"APPdata\":\"UFUAPBAAAAYBAAMAJwMnuwpogDAwYGckCQYAAQAADjxkZAJVcVCnAAAAAAAAAEYAAAAAAAAAAAEDJ7sKAAAAAA==\"},\"messageType\":\"dataReport\",\"deviceType\":\"\",\"deviceId\":\"44d0b9e48d404094bbbcf889e4e64ece\",\"assocAssetId\":\"\",\"IMSI\":\"undefined\",\"IMEI\":\"860803030676429\"}";
        String data2 = "{\"upPacketSN\":\"\",\"upDataSN\":\"\",\"topic\":\"v1/up/ad\",\"timestamp\":1589444235861,\"tenantId\":\"10461180\",\"serviceId\":\"\",\"protocol\":\"lwm2m\",\"productId\":\"10053421\",\"payload\":{\"APPdata\":\"UFUBtBABAAYBAAMBnwMpETxogDAwYGckCQYAAQAADbJkZAJVcVCgAAAAAAAAAEYAAAAAAAAAADADKRE8AAAAAAMpChsAAAAAAykC+QAAAAADKPvWAAAAAAMo9LYAAAAAAyjtlAAAAAADKOZyAAAAAAMo31AAAAAAAyjYLwAAAAADKNENAAAAAAMoyesAAAAAAyjCywAAAAADKLuoAAAAAAMotIYAAAAAAyitZgAAAAADKKZFAAAAAAMonyMAAAAAAyiYAQAAAAADKJDhAAAAAAMoib8AAAAAAyiCnQAAAAADKHt9AAAAAAModFsAAAAAAyhtOQAAAAADKGYYAAAAAAMoXvgAAAAAAyhX1gAAAAADKFC0AAAAAAMoSZQAAAAAAyhCcgAAAAADKDtQAAAAAAMoNDAAAAAAAygtDgAAAAADKCXtAAAAAAMoHssAAAAAAygXqwAAAAADKBCJAAAAAAMoCWcAAAAAAygCRwAAAAADJ/slAAAAAAMn9AQAAAAAAyfs4gAAAAADJ+XBAAAAAAMn3p8AAAAAAyfXfQAAAAADJ9BcAAAAAAMnyToAAAAAAyfCGAAAAAA=\"},\"messageType\":\"dataReport\",\"deviceType\":\"\",\"deviceId\":\"44d0b9e48d404094bbbcf889e4e64ece\",\"assocAssetId\":\"\",\"IMSI\":\"undefined\",\"IMEI\":\"860803030676429\"}";
        WaterParseStrategy strategy = new WaterParseStrategy();
        WaterEntity e = strategy.assemblyData(data2);
        WaterEntityVo e2 = strategy.convertVo(e);

        System.out.println(e2);
    }
}
