package com.smart.device.access.service.impl;

import cn.hutool.http.HttpStatus;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.google.common.collect.Lists;
import com.smart.common.core.domain.Result;
import com.smart.common.utils.bean.BeanUtils;
import com.smart.common.utils.spring.SpringUtils;
import com.smart.device.access.common.constant.DeviceMapConstant;
import com.smart.device.common.access.entity.vo.DeviceAccessVO;
import com.smart.device.access.entity.vo.ImportBaseDeviceVO;
import com.smart.device.access.entity.vo.ImportDeviceVO;
import com.smart.device.access.mapper.TDeviceDictMapper;
import com.smart.device.access.service.BaseDeviceFactory;
import com.smart.device.access.service.DeviceDataListener;
import com.smart.device.access.service.SdDeviceService;
import com.smart.device.common.access.entity.TDeviceDict;
import com.smart.device.common.entity.UserBean;
import com.smart.device.common.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

/**
 * 设备基础信息导入服务接口实现类
 *
 * @author ms
 * @since 2020-05-26
 */
@Slf4j
@Service("sdDeviceService")
public class SdDeviceServiceImpl implements SdDeviceService {
    @Resource
    private UserService userService;
    @Resource
    private TDeviceDictMapper tDeviceDictMapper;

    @Override
    public Result devicesImport(HttpServletRequest request,InputStream inputStream,ImportDeviceVO deviceVO) {
        Result result = new Result();
        UserBean user = userService.getUserByToken(request);

        List<ImportDeviceVO> successList = Lists.newArrayList();
        List<ImportBaseDeviceVO> list = Lists.newArrayList();
        //读取导入的excel数据
        try {
            AnalysisEventListener listener = new DeviceDataListener();
            ExcelReader excelReader = EasyExcel.read(inputStream, ImportBaseDeviceVO.class, listener).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
            // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
            excelReader.finish();
            list = ((DeviceDataListener) listener).getDatas();
        } catch (Exception e) {
            e.printStackTrace();
            list = Lists.newArrayList();
        }
        TDeviceDict dict = tDeviceDictMapper.getDictByType(deviceVO.getDeviceType());
        list.forEach(e->{
            ImportDeviceVO vo = new ImportDeviceVO();
            BeanUtils.copyBeanProp(vo,deviceVO);

            vo.setImei(e.getImei());
            vo.setDeviceName(e.getDeviceName());
            vo.setOpCompanyId(user.getDeptId());
            if(dict != null ){
                vo.setDeviceTypeName(dict.getDeviceTypeName());
                vo.setParentType(dict.getParentType());
                vo.setDeviceModel(dict.getDeviceTypeName());
            }
            successList.add(vo);
        });
        //根据设备类型判断那种基础设备
        Class clazz = DeviceMapConstant.DEVICE_MAP.get(dict.getParentType());
        try {
            Object bean = SpringUtils.getBean(clazz);
            if(successList.size() > 0){
                result = ((BaseDeviceFactory) bean).saveDeviceInfo(successList);
            } else {
                result = Result.FAIL(HttpStatus.HTTP_INTERNAL_ERROR, "导入数据失败，请输入合法数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (successList.size() > 0 ) {
            result =  new Result(HttpStatus.HTTP_OK, "导入成功", true);
        }
        return result;
    }

    //查询设备基础信息表
    @Override
    public Result selectDevicesList(DeviceAccessVO deviceVO) {
        Result result = new Result();
        Class clazz = DeviceMapConstant.DEVICE_MAP.get(deviceVO.getDeviceType());
        Object bean = SpringUtils.getBean(clazz);
        result = ((BaseDeviceFactory) bean).selectDeviceInfo(deviceVO);
        return result;
    }

    //设备信息导入ctwing平台
    @Override
    public Result ctwingDeviceAdd(DeviceAccessVO deviceVO) {
        Result result = new Result();
        Class clazz = DeviceMapConstant.DEVICE_MAP.get(deviceVO.getDeviceType());
        Object bean = SpringUtils.getBean(clazz);
        result = ((BaseDeviceFactory) bean).createCtwingDevice(deviceVO);
        return result;
    }

    //  更改设备-状态
    @Override
    public void devicesDelete(DeviceAccessVO deviceVO) {
        Class clazz = DeviceMapConstant.DEVICE_MAP.get(deviceVO.getDeviceType());
        Object bean = SpringUtils.getBean(clazz);
        ((BaseDeviceFactory) bean).devicesDelete(deviceVO.getId());
    }



}
