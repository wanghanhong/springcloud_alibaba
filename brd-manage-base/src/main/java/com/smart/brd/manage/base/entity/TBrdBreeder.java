package com.smart.brd.manage.base.entity;

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
* 养殖员
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_brd_breeder")
public class TBrdBreeder implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
    * 养殖员姓名
    */

    private String breederName;
    /**
    * 养殖员编号
    */

    private String breederId;
    /**
    * 部门ID
    */

    private Long deptId;
    /**
    * 联系电话
    */

    private String breederContact;
    /**
    * 电子邮箱
    */

    private String breederEmail;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;


    @TableField(exist = false)
    public String deptIds;
}
