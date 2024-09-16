package com.smart.device.common.message.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * <p>
 * 烟感设备警报
 * </p>
 *
 */
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "deviceAlarm对象", description = "烟感设备警报")
public class AlarmVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "设备ID")
    private Long deviceId;
    @ApiModelProperty(value = "设备名称")
    private String deviceName;
    @ApiModelProperty(value = "设备编号")
    private Long deviceCode;
    @ApiModelProperty(value = "imei")
    private Long imei;
    @ApiModelProperty(value = "设备类型")
    private Integer deviceType;
    @ApiModelProperty(value = "设备类型")
    private Integer parentType;
    @ApiModelProperty(value = "设备类型名称")
    private String deviceTypeName;

    @ApiModelProperty(value = "设备状态")
    private String deviceStateName;
    @ApiModelProperty(value = "最后上报时间")
    private String finalTime;

    @ApiModelProperty(value = "建筑物ID")
    private Long buildingId;
    @ApiModelProperty(value = "建筑物名称")
    private String buildingName;
    @ApiModelProperty(value = "楼层")
    private Integer buildingFloor;
    @ApiModelProperty(value = "位置")
    private String location;
    @ApiModelProperty(value = "经度")
    private String longitude;
    @ApiModelProperty(value = "纬度")
    private String latitude;
    @ApiModelProperty(value = " 备注")
    private String content;

    @ApiModelProperty(value = "单位ID")
    private Long companyId;
    @ApiModelProperty(value = " 单位名称")
    private String companyName;

    @ApiModelProperty(value = "省")
    private String province;
    @ApiModelProperty(value = "市")
    private String city;
    @ApiModelProperty(value = "县")
    private String county;
    @ApiModelProperty(value = "乡村")
    private String town;
    @ApiModelProperty(value = "小区")
    private String housing;

    @ApiModelProperty(value = "报警事件时间")
    private String eventTime;
    @ApiModelProperty(value = "事件code")
    private String eventCode;

    @ApiModelProperty(value = "报警处理状态0.未处理，1.被锁定，2.处理中，3.已处理，9.自动消警")
    private Integer state;
    private String stateName;
    @ApiModelProperty(value = "处理时间")
    private String dealTime;
    @ApiModelProperty(value = "处理结果")
    private String result;
    @ApiModelProperty(value = "新增时间")
    private String createTime;

    @ApiModelProperty(value = "消防责任人")
    private String inchargeName;
    @ApiModelProperty(value = "消防责任人电话")
    private String inchargePhone;
    @ApiModelProperty(value = "消防管理人")
    private String managerName;
    @ApiModelProperty(value = "消防管理人电话")
    private String managerPhone;
    @ApiModelProperty(value = "兼职消防管理人")
    private String parttimeName;
    @ApiModelProperty(value = "兼职消防管理人电话")
    private String parttimePhone;

    public String getEventTime() {
        String date = eventTime;
        if(date != null && date.length() >= 19){
            date = date.substring(0,19);
        }
        return date;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Long getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(Long deviceCode) {
        this.deviceCode = deviceCode;
    }

    public Long getImei() {
        return imei;
    }

    public void setImei(Long imei) {
        this.imei = imei;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getParentType() {
        return parentType;
    }

    public void setParentType(Integer parentType) {
        this.parentType = parentType;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public String getDeviceStateName() {
        return deviceStateName;
    }

    public void setDeviceStateName(String deviceStateName) {
        this.deviceStateName = deviceStateName;
    }

    public String getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(String finalTime) {
        this.finalTime = finalTime;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Integer getBuildingFloor() {
        return buildingFloor;
    }

    public void setBuildingFloor(Integer buildingFloor) {
        this.buildingFloor = buildingFloor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getHousing() {
        return housing;
    }

    public void setHousing(String housing) {
        this.housing = housing;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getInchargeName() {
        return inchargeName;
    }

    public void setInchargeName(String inchargeName) {
        this.inchargeName = inchargeName;
    }

    public String getInchargePhone() {
        return inchargePhone;
    }

    public void setInchargePhone(String inchargePhone) {
        this.inchargePhone = inchargePhone;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getParttimeName() {
        return parttimeName;
    }

    public void setParttimeName(String parttimeName) {
        this.parttimeName = parttimeName;
    }

    public String getParttimePhone() {
        return parttimePhone;
    }

    public void setParttimePhone(String parttimePhone) {
        this.parttimePhone = parttimePhone;
    }

}
