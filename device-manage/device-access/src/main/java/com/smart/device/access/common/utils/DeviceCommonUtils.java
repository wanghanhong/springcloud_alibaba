package com.smart.device.access.common.utils;

import com.smart.common.utils.StringUtils;
import com.smart.device.access.mapper.TDeviceDictMapper;
import com.smart.device.access.mapper.TDeviceProductMapper;
import com.smart.device.access.mapper.TDeviceProtocolMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

/**
 * 公共方法工具类
 * @author ms
 * @Time 2020/5/27
 */
@Service
public class DeviceCommonUtils {

    @Resource
    private TDeviceProtocolMapper tDeviceProtocolMapper;
    @Resource
    private TDeviceProductMapper tDeviceProductMapper;
    @Resource
    private TDeviceDictMapper tDeviceDictMapper;

    public Long getDeviceId(Integer deviceType,String protocol,String productName,Long imei,String deviceModel){
        Long deviceId = imei;
        String protocolType = "01";// 默认：LWM2M
        String productType = "01";// 默认：
        String modelType = "000";
        try {
            if(deviceType == null ){
                deviceType = 11;
            }
            try {
                if(StringUtils.isNotBlank(protocol)){
                    protocolType = tDeviceProtocolMapper.getTypeByName(protocol);
                }
                if(StringUtils.isBlank(protocolType)){
                    protocolType = "01";
                }
            }catch (Exception e){
                e.printStackTrace();
                protocolType = "01";
            }
            try {
                if(StringUtils.isNotBlank(productName)){
                    productType = tDeviceProductMapper.getProductType(productName);
                }
                if(StringUtils.isBlank(productType)){
                    productType = "01";
                }
            }catch (Exception e){
                e.printStackTrace();
                productType = "01";
            }
            try {
                //根据导入的设备型号查询其deviceType
                if(StringUtils.isNotBlank(deviceModel)){
                    modelType = tDeviceDictMapper.getModelType(deviceModel);
                    if(null != modelType){
                        modelType  = "0" +  modelType;
                    }
                }
                if(StringUtils.isBlank(modelType)){
                    modelType = "000";
                }
            }catch (Exception e){
                e.printStackTrace();
                modelType = "000";
            }
            String imeiNew = imei.toString().substring(imei.toString().length()-8,imei.toString().length());
            int systemCode = new Random().nextInt(9);
            //deviceType 前端页面控制传过来的设备类型，代表某一类型设备
            //deviceId生成规则 设备类型 + 协议类型 + 产品类型 + 设备型号类型 + imei号后8位 + 1位系统随机验证码
            String deviceIds = deviceType + protocolType + productType + modelType + imeiNew + systemCode;
            deviceId = Long.valueOf(deviceIds);
        }catch (Exception e){
            e.printStackTrace();
            deviceId = imei;
        }
       return deviceId;
   }

    public Long getProductId(String productName){
        Long productId = tDeviceProductMapper.getProductId(productName);
        return  productId;
    }
}
