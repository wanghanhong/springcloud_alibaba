package com.smart.brd.manage.base.screen.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ScreenLine implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonProperty("xAxisData")
    private List<String> xAxisData;
    @JsonProperty("yAxisData")
    private List<Object> yAxisData;
    private String name;

}
