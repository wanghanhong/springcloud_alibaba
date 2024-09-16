package com.smart.brd.manage.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.smart.brd.manage.base.common.dict.Dict;
import com.smart.brd.manage.base.entity.vo.FileVo;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author dukzzz
 * @date 2021/3/4 14:19:下午
 * @desc
 */
@Data
public class BarnsVo implements Serializable {
    /**
     * 舍ID
     */

    private Long shedId;
    /**
     * 养殖场
     */

    private Long fieldId;
    /**
     * 养殖场名称
     */
    private String fieldName;
    /**
     * 舍名称
     */

    private String shedName;
    /**
     * 栏ID
     */

    private Long bedId;
    /**
     * 栏名称
     */

    private String bedName;
    /**
     * 家畜品种
     */
    @Dict
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

    @TableField(exist = false)
    public String deptIds;

    private String createTimeDisplay;

    private String fileName;
    private String url;
    private List<FileVo> file;

}
