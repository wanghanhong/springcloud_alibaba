package com.smart.brd.manage.base.entity.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.smart.brd.manage.base.common.dict.Dict;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author junglelocal
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DiseaseVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 畜病id
     */

    @TableId(value = "disease_id", type = IdType.INPUT)
    private Long diseaseId;
    /**
     *畜病编码
     */

    private String diseaseCode;
    /**
     * 畜病
     */
    @Excel(name = "疾病种类", orderNum = "2")
    private String diseaseName;
    /**
     * 部门id
     */

    private Long deptId;

    /**
     * 发病类型
     */
    private List<Integer> suitableList;

    /**
     * 发病类型
     */
    private String suitable;
    /**
     * 发病类别
     */
    private String suitableName;
    @Excel(name = "症状", orderNum = "1")
    private String symptom;
    /**
     * 新增时间
     */

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 删除标识(0 正常 1删除)
     */
    private Integer deleteFlag;

    public String deptIds;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime startTime;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime endTime;

}
