package com.smart.brd.manage.base.entity.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class TLivestockBedVo implements Serializable {
    //养殖员姓名
    private String username;
    //电话号码
    private String mobile;

    private Long deptId;
    private String deptIds;
    /**
     * 删除标识(0 正常 1删除)
     */

    private Integer isShow;
}
