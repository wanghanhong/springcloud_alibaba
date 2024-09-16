package com.smart.test.demo.event.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 日志实体
 * @author 三多
 * @Time 2020/6/12
 */
@Data
@TableName("t_log")
public class LogEntity extends BaseSource{

    @TableId
    private String id;
    private String ip;
}
