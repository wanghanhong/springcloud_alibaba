package com.smart.device.access.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ctg.ag.sdk.biz.AepDeviceManagementClient;
import com.ctg.ag.sdk.biz.AepDeviceStatusClient;
import com.ctg.ag.sdk.biz.aep_device_management.*;
import com.ctg.ag.sdk.biz.aep_device_status.QueryDeviceStatusRequest;
import com.ctg.ag.sdk.biz.aep_device_status.QueryDeviceStatusResponse;
import com.smart.device.access.entity.vo.CtWingVo;
import com.smart.device.access.entity.vo.SdDeviceCtWingVO;
import com.smart.device.access.mapper.TDeviceDictMapper;
import com.smart.device.access.service.CtWingAPIService;
import com.smart.device.common.access.entity.TDeviceDict;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class CtWingAPIServiceImpl implements CtWingAPIService {

    @Resource
    private TDeviceDictMapper tDeviceDictMapper;

    @Value("${appKey}")
    public String appKey;
    @Value("${appSecret}")
    public String appSecret;

    @Override
    public JSONObject GetSubscription(String deviceId) throws Exception {
        return null;
    }

    @Override
    public JSONObject queryEventList(String deviceId, Integer type) throws Exception {
        return null;
    }


    @Override
    public CtWingVo QueryCtwingParam(Integer type) throws Exception {
        CtWingVo vo = new CtWingVo();
        List<TDeviceDict> tDeviceDictList =  tDeviceDictMapper.getProjectId(type);
        for(TDeviceDict tDeviceDict: tDeviceDictList){
          if(null != tDeviceDict.getProductId() && StringUtils.isNotEmpty(tDeviceDict.getMasterKey())){
              vo.setProductId(tDeviceDict.getProductId());
              vo.setMasterKey(tDeviceDict.getMasterKey());
          }
        }
        return vo;
    }


    @Override
    public JSONObject createDevice(SdDeviceCtWingVO vo) throws Exception {
        CtWingVo ctWingVo = QueryCtwingParam(vo.getDeviceType());

        JSONObject device = new JSONObject();
        if (StringUtils.isNotEmpty(vo.getDeviceName())) {
            device.put("deviceName", vo.getDeviceName());
        } else {
            device.put("deviceName", vo.getImei());
        }
        if (StringUtils.isNotEmpty(vo.getDeviceSn())) {
            device.put("deviceSn", vo.getDeviceSn());
        } else {
            device.put("deviceSn", vo.getImei());
        }
        if (StringUtils.isNotEmpty(vo.getImei())) {
            device.put("imei", vo.getImei());
        }
        if (StringUtils.isNotEmpty(vo.getOperator())) {
            device.put("operator", vo.getOperator());
        } else {
            device.put("operator", "system");
        }
        device.put("productId", ctWingVo.getProductId());

        JSONObject other = new JSONObject();
        other.put("autoObserver", vo.getAutoObserver());

        device.put("other", other);

        String deviceStr = JSONObject.toJSONString(device);

        AepDeviceManagementClient client = AepDeviceManagementClient.newClient().appKey(appKey).appSecret(appSecret).build();
        CreateDeviceRequest request = new CreateDeviceRequest();

        request.setParamMasterKey(ctWingVo.getMasterKey());
        request.setBody(deviceStr.getBytes("utf-8"));

        CreateDeviceResponse response = client.CreateDevice(request);
        client.shutdown();

        //使用构造函数转换成字符串
        String res = new String(response.getBody());
        JSONObject obj = JSONObject.parseObject(res);
        return obj;
    }

    @Override
    public JSONObject queryDevice(String deviceId, Integer type) throws Exception {
        CtWingVo ctWingVo = QueryCtwingParam(type);

        AepDeviceManagementClient client = AepDeviceManagementClient.newClient().appKey(appKey).appSecret(appSecret).build();
        QueryDeviceRequest request = new QueryDeviceRequest();
        request.setParamMasterKey(ctWingVo.getMasterKey());
        request.setParamDeviceId(deviceId);
        request.setParamProductId(ctWingVo.getProductId());

        QueryDeviceResponse query = client.QueryDevice(request);
        byte[] arr = query.getBody();
        client.shutdown();

        //使用构造函数转换成字符串
        String res = new String(arr);
        JSONObject obj = JSONObject.parseObject(res);
        return obj;
    }

    @Override
    public JSONObject queryDeviceList(SdDeviceCtWingVO vo) throws Exception {
        CtWingVo ctWingVo = QueryCtwingParam(vo.getType());

        AepDeviceManagementClient client = AepDeviceManagementClient.newClient().appKey(appKey).appSecret(appSecret).build();

        QueryDeviceListRequest request = new QueryDeviceListRequest();

        request.setParamMasterKey(ctWingVo.getMasterKey());
        request.setParamProductId(ctWingVo.getProductId());
        if (null != vo.getPageNow()) {
            request.setParamPageNow(vo.getPageNow());
        }
        if (null != vo.getPageSize()) {
            request.setParamPageSize(vo.getPageSize());
        }
        if (StringUtils.isNotEmpty(vo.getSearchValue())) {
            request.setParamSearchValue(vo.getSearchValue());
        }
        QueryDeviceListResponse query = client.QueryDeviceList(request);
        byte[] arr = query.getBody();
        client.shutdown();

        //使用构造函数转换成字符串
        String res = new String(arr);
        JSONObject obj = JSONObject.parseObject(res);
        return obj;
    }


    @Override
    public JSONObject queryDeviceStatus(String deviceId, Integer type) throws Exception {
        CtWingVo ctWingVo = QueryCtwingParam(type);

        AepDeviceStatusClient client = AepDeviceStatusClient.newClient().appKey(appKey).appSecret(appSecret).build();
        QueryDeviceStatusRequest request = new QueryDeviceStatusRequest();

        JSONObject device = new JSONObject();
        device.put("productId", ctWingVo.getProductId());
        device.put("deviceId", deviceId);
        device.put("datasetId", "temperature");

        request.setBody(JSONObject.toJSONString(device).getBytes());

        QueryDeviceStatusResponse response = client.QueryDeviceStatus(request);
        client.shutdown();

        System.out.println();
        JSONObject obj = JSONObject.parseObject(new String(response.getBody()));
        String code = String.valueOf(obj.get("code"));
        if ("0".equals(code)) {
            JSONObject result = JSONObject.parseObject(String.valueOf(obj.get("result")));
            return result;
        }
        return null;
    }

    @Override
    public JSONObject deleteDevice(String deviceIds, Integer type) throws Exception {
        CtWingVo cvo = QueryCtwingParam(type);

        AepDeviceManagementClient client = AepDeviceManagementClient.newClient()
                .appKey(appKey).appSecret(appSecret).build();

        DeleteDeviceRequest request = new DeleteDeviceRequest();

        // single value
        request.setParamMasterKey(cvo.getMasterKey());
        // or multi values
//       request.addParamMasterKey(MasterKey);
        // single value
        request.setParamProductId(cvo.getProductId());
        // or multi values
//       request.addParamProductId(productId);
        // single value
        request.setParamDeviceIds(deviceIds);

        DeleteDeviceResponse response = client.DeleteDevice(request);
        client.shutdown();

        JSONObject obj = JSONObject.parseObject(new String(response.getBody()));
        return obj;
    }

    @Override
    public JSONObject updateDevice(SdDeviceCtWingVO vo) throws Exception {
        CtWingVo ctWingVo = QueryCtwingParam(vo.getType());

        JSONObject device = new JSONObject();
        if (StringUtils.isNotEmpty(vo.getDeviceName())) {
            device.put("deviceName", vo.getDeviceName());
        }
        if (StringUtils.isNotEmpty(vo.getOperator())) {
            device.put("operator", vo.getOperator());
        }
        device.put("productId", ctWingVo.getProductId());

        JSONObject other = new JSONObject();
        other.put("autoObserver", vo.getAutoObserver());
        if (StringUtils.isNotEmpty(vo.getImsi())) {
            other.put("imsi", vo.getImsi());
        }

        device.put("other", other);

        String deviceStr = JSONObject.toJSONString(device);

        AepDeviceManagementClient client = AepDeviceManagementClient.newClient().appKey(appKey).appSecret(appSecret).build();
        UpdateDeviceRequest request = new UpdateDeviceRequest();

        request.setParamMasterKey(ctWingVo.getMasterKey());
        request.setParamDeviceId(vo.getDeviceId());
        request.setBody(deviceStr.getBytes("utf-8"));

        UpdateDeviceResponse response = client.UpdateDevice(request);
        client.shutdown();

        //使用构造函数转换成字符串
        String res = new String(response.getBody());
        JSONObject obj = JSONObject.parseObject(res);
        return obj;
    }


}
