package com.smart.device.common.access.entity.vo;

import lombok.Data;

/*
* 设备前端所传字段
* */
@Data
public class DeviceAccessVO {

    private Long id;
    /**
     * 设备类型
     */
    private Integer deviceType;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备编号
     */
    private Long deviceCode;

    /**
     * 设备型号
     */
    private String deviceModel;

    /**
     * 移动设备识别码
     */
    private Long imei;
    //每页条数
    private Integer pageSize = 10;
    //当前页
    private Integer pageNum = 1;
    //批量id
    private Long[] idlist;
    //  删除标识(0 正常 1删除)
    private Integer deleteFlag;
    // 操作单位
    private Long opCompanyId;
    // 操作人
    private Long opUserId;

    private String username;
}
