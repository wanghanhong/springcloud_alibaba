package com.smart.brd.manage.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.smart.brd.manage.base.entity.vo.FileVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
* 栏舍表
*
* @author 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_livestock_bed")
public class TLivestockBed implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 栏ID
    */

     @TableId(value = "bed_id", type = IdType.INPUT)
    private Long bedId;
    /**
    * 舍id
    */

    private Long shedId;
    private String shedName;
    /**
    * 养殖场
    */

    private Long fieldId;
    /**
    * 栏名称
    */

    private String bedName;
    /**
     * 家畜品种
     */
    private Integer type;
    /**
     * 家畜种类
     */

    private String suitable;
    /**
     *畜栏容量
     */

    private Integer capacity;
    /**
     *存栏周期
     */

    private String period;
    /**
     * 养殖员
     */

    private String breeder;
    /**
     * 联系电话
     */

    private String phone;
    /**
    * 新增时间
    */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime;

    /**
     * 删除标识(0 正常 1删除)
     */

    private Integer deleteFlag;

    /**
     * 部门id
     */

    private Long deptId;
    /**
     * 文件地址
     */
    private String fileName;
    private String url;
    @TableField(exist = false)
    private String deptIds;
    @TableField(exist = false)
    private String supplierName;
    /**
     * 统一社会信用代码
     */
    @TableField(exist = false)
    private String creditCode;
    @TableField(exist = false)
    private List<FileVo> file;
}
