package com.smart.brd.manage.base.screen.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ScreenLineVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ScreenLine> list;

}
