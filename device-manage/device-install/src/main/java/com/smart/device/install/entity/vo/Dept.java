package com.smart.device.install.entity.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
public class Dept implements Serializable {

    private static final long serialVersionUID = -7790334862410409053L;

    @TableId(value = "DEPT_ID")
    private Long deptId;

    private Long parentId;

    @NotBlank(message = "{required}")
    @Size(max = 20, message = "{noMoreThan}")
    @ExcelField(value = "部门名称")
    private String deptName;

    private Double orderNum;

    private Date createTime;
    private Date modifyTime;

    private transient String createTimeFrom;

    private transient String createTimeTo;

    /**
     * 部门code
     */
    private String deptCode;

    /**
     *  租户id
     */
    private Long tenantId;

    /**
     * 机构地址
     */
    private String deptAddress;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;


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



}