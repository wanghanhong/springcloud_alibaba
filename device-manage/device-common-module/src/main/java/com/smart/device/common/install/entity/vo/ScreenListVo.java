package com.smart.device.common.install.entity.vo;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class ScreenListVo {

    private List<ScreenVo> onLineList = new ArrayList();
    private List<ScreenVo> faultList = new ArrayList();
    private List<ScreenVo> alarmList = new ArrayList();

}
