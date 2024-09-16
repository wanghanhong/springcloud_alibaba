package com.smart.brd.manage.base.screen.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ScreenEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private String name;
    private Integer value;
    private String percent;

    private Integer fieldNum;// 养殖场数量
    private Integer live;// 存栏数量
    private Integer liveOut;

    private String city;
    private Integer countNum;
    private String suitable;
    private String month;

    public ScreenEntity() {
    }

    public ScreenEntity(String month) {
        this.month = month;
    }
}
