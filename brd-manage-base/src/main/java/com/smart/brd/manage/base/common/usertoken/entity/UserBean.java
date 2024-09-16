package com.smart.brd.manage.base.common.usertoken.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@Accessors(chain = true)
public class UserBean implements Serializable {

    /**
     * 账户状态
     */
    public static final String STATUS_VALID = "1";
    public static final String STATUS_LOCK = "0";
    public static final String DEFAULT_AVATAR = "default.jpg";
    /**
     * 性别
     */
    public static final String SEX_MALE = "0";
    public static final String SEX_FEMALE = "1";
    public static final String SEX_UNKNOW = "2";

    private static final long serialVersionUID = -4852732617765810959L;

    @TableId(value = "USER_ID", type = IdType.AUTO)
    private Long userId;

    private String username;

    private String password;

    private Long deptId;
    private Long parentId;

    /**租户ID*/
    @TableField(value = "tenant_id")
    private Long tenantId;

    private transient String deptName;

    private String email;

    private String mobile;

    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

    private String gender;

    private String description;

    private String avatar;

    private transient String roleId;

    private transient String roleName;

    /**
     * 有效期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expireTime;

    /**
     * 排序字段
     */
    private transient String sortField;

    /**
     * 排序规则 ascend 升序 descend 降序
     */
    private transient String sortOrder;

    private transient String createTimeFrom;
    private transient String createTimeTo;

    private transient String id;
    /**
     * 用户类型： 1 SaaS平台用户 2 租户平台用户
     */
    private int userType;

    /**
     * 查询条件
     */
    private transient String condition;

    private String openId;

    private Integer isXcx;

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

    private String deptIds;

}
