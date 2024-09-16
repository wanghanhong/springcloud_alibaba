package com.smart.brd.manage.base.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserDeptVo {

    private Long userId;

    private String username;

    private String password;

    private String openId;

    private String oldPassword;

    private Long deptId;

    private Long parentId;

    private String deptName;

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
