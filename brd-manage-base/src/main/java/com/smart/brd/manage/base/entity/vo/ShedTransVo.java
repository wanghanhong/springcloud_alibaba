package com.smart.brd.manage.base.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author junglelocal
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShedTransVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 家畜ID
     */
    private List<Long> livestockId;

    /**
     * 转出栏ID
     */
    private Long sourceId;

    /**
     * 转出栏名称
     */
    private String sourceName;

    /**
     * 转入舍ID
     */
    private Long shedId;

    /**
     * 转入舍名称
     */
    private String shedName;

    /**
     * 转入栏ID
     */
    private Long bedId;

    /**
     * 转入栏名称
     */
    private String bedName;

    /**
     * 转移时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    protected LocalDate transferTime;

    /**
     * 转出原因
     */
    private Integer transferReason;

    /**
     * 部门ID
     */
    private Long deptId;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 删除标记
     */
    private Integer deleteFlag;

    public String deptIds;
}
