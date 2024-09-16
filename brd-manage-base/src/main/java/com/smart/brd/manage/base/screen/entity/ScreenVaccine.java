package com.smart.brd.manage.base.screen.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ScreenVaccine implements Serializable {

    private static final long serialVersionUID = 1L;

    private String city;
    private String cityName;
    private String fieldName;
    private String createTime;
    private String vaccineEvent;

}
