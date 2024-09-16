package com.smart.device.message.data.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuwenze.poi.annotation.ExcelField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 烟感设备警报
 * </p>
 *
 * @author Pano
 * @since 2019-09-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SdDeviceAlarm对象", description = "烟感设备警报")
public class SdDeviceAlarmVO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 设备编号
     */
    private String deviceCode;
    /**
     * 报警时间
     */
    private Date time;
    /**
     * 报警等级 1 正常 2 一般 3 重要 4   严重
     */
    private Integer level;
    /**
     * 警报类型1火灾报警2故障3欠压告警4拆卸
     */
    private Integer type;
    /**
     * 报警内容
     */
    private String info;
    /**
     * 报警处理状态0.未处理，1.被锁定，2.已处理，9.自动消警
     */
    private Integer state;
    /**
     * 确认类型 0.未知 1.确认
     */
    private Integer confirmType;
    /**
     * 处理时间
     */
    private Date dealTime;
    /**
     * 处理结果
     */
    private String result;
    /**
     * 维修人员ID
     */
    private Long repairId;
    /**
     * 维修人员
     */
    private String repairName;
    /**
     * 新增时间
     */
    private Date addTime;
    /**
     * 状态更新时间
     */
    private Date updateTime;

    @ApiModelProperty(value = "建筑物编号")
    private Long buildingId;

    @ApiModelProperty(value = "安装位置")
    private String location;

    @ApiModelProperty(value = "机构ID")
    private Long companyId;

    @ApiModelProperty(value = "机构ID")
    private String deptName;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "报警时间开始")
    private String timeStart;

    @ApiModelProperty(value = "报警时间结束")
    private String timeEnd;

    @ApiModelProperty(value = "报警时间结束")
    private String[] timeStartEnd;

    @ApiModelProperty(value = "软件版本")
    private String softwareVersion;

    @ApiModelProperty(value = "设备状态（0未上报,1在线,2离线）")
    private Integer isOnLine;

    @ApiModelProperty(value = "省市 安装位置")
    private String mulField;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    private int pageSize = 10;
    private int pageNum = 1;

    @ApiModelProperty(value = "附件IDs")
    private String fileIds;

    @ApiModelProperty(value = "设施名称")
    private String facility;

    @ApiModelProperty(value = "设施类型")
    private String facilityType;

    @ApiModelProperty(value = "平面图编号")
    private Long planarGraphId;

    @ApiModelProperty(value = "横坐标")
    private Float abscissa;

    @ApiModelProperty(value = "纵坐标")
    private Float ordinate;

    @ApiModelProperty(value = "区域编号")
    private Long regionId;

    @ApiModelProperty(value = "区域编号")
    private Long regionCode;

    @ApiModelProperty(value = "生产厂家")
    private String company;

    @ApiModelProperty(value = "上报时间间隔")
    private Integer deviceInterval;

    @ApiModelProperty(value = "平台ID")
    private Long platformId;

    @ApiModelProperty(value = "楼层")
    @ExcelField(value = "楼层")
    private Long buildingFloor;

    @ApiModelProperty(value = "设备添加人编号")
    private String addDeviceAccount;

    //此处字段仅是设备导入时使用
    @ExcelField(value="联系人号码",required = true)
    private String contactNumber;

    @ApiModelProperty(value = "设备型号")
    private String deviceModel;

    @ApiModelProperty(value = "省")
    private String areaProvince;
    @ApiModelProperty(value = "市")
    private String areaCity;
    @ApiModelProperty(value = "县")
    private String areaCounty;
    @ApiModelProperty(value = "乡村")
    private String areaTown;

    @ApiModelProperty(value = "地图位置")
    private String mapLocation;

    @ApiModelProperty(value = "报装装态")
    private String reportState;

    @ApiModelProperty(value = "报装人员")
    private String reportMan;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "报装发送时间")
    private LocalDateTime reportTime;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    // 设备ID
    private String deviceId;

    private String deviceIds;

    private String[] deviceCodes;

    private Long buildNum;
    private Long deviceNum;
    private Long WebcamNum;
    private Long dealNum;

    private String confirmTypeString;
    private String typeString;
    private String stateString;

    private String isOnLineString;

    private Integer num;

    private Float pcVoltage;

    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 设备类型1 烟感
     */
    private Integer deviceType;
    /**
     * 生产厂家
     */
    private String productId;
    /**
     * 生产厂家
     */
    private String productName;
    /**
     * 设备状态 0 新安装 1 在线 2 离线 3  报废
     */
    private Integer deviceState;
    /**
     * 设备添加人
     */
    private String addMan;
    /**
     * 维修人员
     */
    private String repairMan;

    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 县
     */
    private String county;
    /**
     * 乡村
     */
    private String town;

    private String buildingName;

    private String imsi;

    private String inchargeName;
    private String inchargePhone;
    private String managerName;
    private String managerPhone;
    private String parttimeName;
    private String parttimePhone;

    private String userId;

    /**
     * 硬件版本
     */
    private String hardwareVersion;
    /**
     * 微机电量
     */
    private Float microVoltage;
    /**
     * 模块电量
     */
    private Float lnbDevVoltage;

    /**
     * 信号强度
     */
    private Integer signalStrength;
    /**
     * 时间间隔
     */
    private Integer timeInterval;
    /**
     * 温度
     */
    private String temperature;
    /**
     * 前一次心跳时间
     */
    private String beforeOnceTime;
    /**
     * 信号覆盖率
     */
    private String ecl;
    /**
     * 信噪比
     */
    private String snr;
    /**
     * iccid
     */
    private String icCid;
    /**
     * 设备心跳时间间隔
     */
    private Integer deviceTimeInterval;

    /**
     * 烟感模块电量
     */
    private Float moduleVoltage;

    /**
     * 心跳时间
     */
    private Date heartbeatTime;
    //  烟雾浓度
    private Float smokeScope;

    private String imei;
    //  协议版本
    private String protocol;
    //最新自检时间
    private String selfdetectTime;
    //最新自检结果
    private String selfdetectRet;



}
