package com.smart.brd.manage.base.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
/**
* 畜病表
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_disease")
public class TDisease implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 畜病id
    */

     @TableId(value = "disease_id", type = IdType.INPUT)
    private Long diseaseId;
    /**
     *畜病编码
     */

    private String diseaseCode;
    /**
     * 症状
     */
    @Excel(name = "症状", orderNum = "2")
    private String symptom;
    /**
    * 畜病
    */
    @Excel(name = "疾病种类", orderNum = "1")
    private String diseaseName;
    /**
    * 部门id
    */

    private Long deptId;
    /**
    * 发病类型
    */
    private String suitable;
    private String suitableName;
    /**
    * 新增时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
    * 删除标识(0 正常 1删除)
    */

    private Integer deleteFlag;


    @TableField(exist = false)
    public String deptIds;

    @TableField(exist = false)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    public String startTime;

    @TableField(exist = false)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private String endTime;

    @TableField(exist = false)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String lastTime;

}
