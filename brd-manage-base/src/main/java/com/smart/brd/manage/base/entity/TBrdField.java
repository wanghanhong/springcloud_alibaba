package com.smart.brd.manage.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* 养殖场
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_brd_field")
public class TBrdField implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 养殖场ID
    */

    @TableId(value = "field_id", type = IdType.INPUT)
    private Long fieldId;
    /**
    * 上级部门id
    */

    private Long parentId;
    /**
    * 养殖场名称
    */

    private String fieldName;
    /**
    * 养殖户身份编号/统一社会信用代码
    */

    private String identity;

    /**
    * 养殖场编号
    */
    private Long fieldNumber;

    /**
     * 养殖家畜类型  1-猪 2-牛 3-羊
     */
    private String type;
    private String typeName;
    /**
    * 养殖场地址
    */
    private String fieldAddress;
    /**
    * 养殖场联系电话
    */

    private String fieldPhone;
    /**
    * 联系人
    */

    private String fieldContact;
    /**
    * 电子邮箱
    */

    private String fieldEmail;
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
    /**
    * 添加时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 删除标记 0-未删除 1-已删除
     */
    private Integer deleteFlag;

    private Long deptId;
    @TableField(exist = false)
    private String deptIds;
    private String url;
    private String fileName;


}
