package com.smart.brd.manage.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.smart.brd.manage.base.common.dict.Dict;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
/**
* 疫苗防疫记录
*
* @author
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_vaccine_record")
public class TVaccineRecord implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
    * 防疫id
    */

    private Long preventionId;
    /**
    * 动物id
    */

    private Long livestockId;
    /**
    * 舍
    */

    private String shedName;
    /**
    * 栏
    */

    private String bedName;
    /**
    * 类别
    */
    @Dict
    private String suitable;
    /**
    * 设备编号
    */

    private String deviceCode;
    /**
    * 出生日期
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    /**
    * 入栏日期
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate entryDate;

    /**
     * 新增时间
     */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 删除标识(0 正常 1删除)
     */

    private Integer deleteFlag;

    private Long deptId;

    @TableField(exist = false)
    public String deptIds;
    /**
     * 种类
     */
    @Dict
    private String varieties;
}
