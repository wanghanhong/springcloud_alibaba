package com.smart.device.install.service.inspection;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.common.core.page.PageDomain;
import com.smart.device.common.install.entity.TBaseInspection;
import com.smart.device.common.install.entity.THisInspection;
import com.smart.device.install.entity.vo.HisInspectionVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author f
 */
public interface ITHisInspectionService extends IService<THisInspection> {

    //  PC端的巡检记录接口
    IPage<TBaseInspection> hisInspectionList(PageDomain page, TBaseInspection entity);

    // 更改状态
    int hisInspectionAddOne(HisInspectionVo entity);


}
