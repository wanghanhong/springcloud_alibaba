package com.smart.device.common.install.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author f
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_his_inspection_device")
@ApiModel(value = "THisInspectionDevice对象", description = "")
public class THisInspectionDevice implements Serializable {

    private static final long serialVersionUID = 1L;

    private String companyName;

    private String location;

    private String deviceName;

    private Long deviceCode;

    private String deviceQuestion;

    public Long getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(Long inspectionId) {
        this.inspectionId = inspectionId;
    }

    private Long inspectionId;

    private LocalDateTime createTime;

    private Integer deviceStatus;
    private String buildingName;
    private Long id;
    private Integer deviceId;
    private Integer buildingFloor;

    @Transient
    private Integer pointRepair;
    @Transient
    private Integer pointReplace;
    @Transient
    private Integer pointLost;


}
