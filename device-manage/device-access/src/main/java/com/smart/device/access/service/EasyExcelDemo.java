package com.smart.device.access.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.smart.device.access.entity.vo.ImportBaseDeviceVO;
import java.io.FileInputStream;
import java.io.InputStream;

public class EasyExcelDemo {

    public static void main(String[] args) throws Exception {
        InputStream fis = new FileInputStream("D:\\12.xlsx");

        AnalysisEventListener listener = new DeviceDataListener();

        ExcelReader excelReader = EasyExcel.read(fis, ImportBaseDeviceVO.class, listener).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
    }

}