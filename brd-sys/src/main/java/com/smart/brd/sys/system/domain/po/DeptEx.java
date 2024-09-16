package com.smart.brd.sys.system.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wuwenze.poi.annotation.Excel;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("t_dept")
@Excel("部门信息表")
public class DeptEx extends Dept{
    private String platformName;
    private String logo;
    private String copyright;
}
