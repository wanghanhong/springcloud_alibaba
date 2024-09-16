package com.smart.device.access.service;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.smart.device.access.entity.vo.ImportBaseDeviceVO;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceDataListener extends AnalysisEventListener<ImportBaseDeviceVO> {

    List<ImportBaseDeviceVO> list = new ArrayList<>();

    @Override
    public void invoke(ImportBaseDeviceVO data, AnalysisContext analysisContext) {
        System.out.println("解析到一条数据:{}"+ JSON.toJSONString(data));
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("所有数据解析完成！");
    }

    public List<ImportBaseDeviceVO> getDatas(){
        return list;
    }

}