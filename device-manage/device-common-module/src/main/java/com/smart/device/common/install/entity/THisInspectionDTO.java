package com.smart.device.common.install.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;

import java.time.LocalDateTime;
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Data
@ApiModel(value = "THisInspection对象", description = "巡检记录")
public class THisInspectionDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "单位ID")
    private Long companyId;

    @ApiModelProperty(value = " 单位名称")
    private String companyName;

    @ApiModelProperty(value = "巡检人ID")
    private Long firefighterId;

    @ApiModelProperty(value = "巡检人电话")
    private Long firefighterNamePhone;


    @ApiModelProperty(value = "巡检人")
    private String firefighterName;

    @ApiModelProperty(value = "巡检点")
    private String point;

    @ApiModelProperty(value = "巡检待修点")
    private String pointRepair;

    @ApiModelProperty(value = "巡检代换点")
    private String pointReplace;

    @ApiModelProperty(value = "巡检丢失")
    private String pointLose;
    @ApiModelProperty(value = "计划id")
    private Long inspectionId;

     @Transient
    private String name;

     @Transient
    private Long phone;

    @ApiModelProperty(value = "添加时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getFirefighterId() {
        return firefighterId;
    }

    public void setFirefighterId(Long firefighterId) {
        this.firefighterId = firefighterId;
    }

    public Long getFirefighterNamePhone() {
        return firefighterNamePhone;
    }

    public void setFirefighterNamePhone(Long firefighterNamePhone) {
        this.firefighterNamePhone = firefighterNamePhone;
    }

    public String getFirefighterName() {
        return firefighterName;
    }

    public void setFirefighterName(String firefighterName) {
        this.firefighterName = firefighterName;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getPointRepair() {
        return pointRepair;
    }

    public void setPointRepair(String pointRepair) {
        this.pointRepair = pointRepair;
    }

    public String getPointReplace() {
        return pointReplace;
    }

    public void setPointReplace(String pointReplace) {
        this.pointReplace = pointReplace;
    }

    public String getPointLose() {
        return pointLose;
    }

    public void setPointLose(String pointLose) {
        this.pointLose = pointLose;
    }

    public Long getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(Long inspectionId) {
        this.inspectionId = inspectionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @ApiModelProperty(value = "删除标识(0 正常 1删除)")
    private Integer deleteFlag;








}
