package com.smart.brd.manage.base.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author junglelocal
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BrdFieldVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 养殖场ID
     */
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
    private List<Integer> types;

    /**
     * 养殖家畜类型
     */
    private String type;
    private String typeName;
    /**
     * 联系人
     */
    private String fieldContact;

    /**
     * 所属地区
     */
    private String area;

    /**
     * 养殖场地址
     */
    private String fieldAddress;
    /**
     * 养殖场联系电话
     */

    private String fieldPhone;

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
    private Long deptId;
    private String deptIds;
    private String fileName;
    private String url;
    private List<FileVo> file;

}
