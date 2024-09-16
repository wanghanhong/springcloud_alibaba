package com.smart.card.sys.system.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.smart.card.sys.common.converter.TimeConverter;
import com.smart.card.sys.common.domain.RegexpConstant;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Accessors(chain = true)
@TableName("t_user")
@Excel("用户信息表")
public class User implements Serializable {

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

    // 默认密码  yf5@ka3d 前端已经加密
    public static final String DEFAULT_PASSWORD = "c777284c6553c8e1b05f38b383f81434";

    private static final long serialVersionUID = -4852732617765810959L;

    @TableId(value = "USER_ID", type = IdType.AUTO)
    private Long userId;

    @Size(min = 2, max = 20, message = "{range}")
    @ExcelField(value = "用户名")
    private String username;

    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date passTime;

    private Long deptId;

    @TableField(exist = false)
    private String deptIds;

    /**租户ID*/
    @TableField(value = "tenant_id")
    private Long tenantId;

    @ExcelField(value = "部门")
    private transient String deptName;

    @Size(max = 50, message = "{noMoreThan}")
    @Email(message = "{email}")
    @ExcelField(value = "邮箱")
    private String email;

    @Pattern(regexp = RegexpConstant.MOBILE_REG, message = "{mobile}")
    @ExcelField(value = "手机号")
    private String mobile;

    @ExcelField(value = "状态", writeConverterExp = "0=锁定,1=有效")
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ExcelField(value = "最后登录时间", writeConverter = TimeConverter.class)
    private Date lastLoginTime;

    @ExcelField(value = "性别", writeConverterExp = "0=男,1=女,2=保密")
    private String gender;

    @Size(max = 100, message = "{noMoreThan}")
    @ExcelField(value = "个人描述")
    private String description;

    private String avatar;

    @NotBlank(message = "{required}")
    private transient String roleId;

    @ExcelField(value = "角色")
    private transient String roleName;

    /**
     * 有效期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime expireTime;

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
    private Integer isShow;
    private Long createDept;

    private String province;
    private String city;
    private String county;
    private String town;
    private String housing;

    /**
     * shiro-redis v3.1.0 必须要有 getAuthCacheKey()或者 getId()方法
     * # Principal id field name. The field which you can get unique id to identify this principal.
     * # For example, if you use UserInfo as Principal class, the id field maybe userId, userName, email, etc.
     * # Remember to add getter to this id field. For example, getUserId(), getUserName(), getEmail(), etc.
     * # Default value is authCacheKey or id, that means your principal object has a method called "getAuthCacheKey()" or "getId()"
     *
     * @return userId as Principal id field name
     */
    public Long getAuthCacheKey() {
        return userId;
    }
}
