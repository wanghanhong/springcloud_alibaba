package com.smart.device.message.parse.fire.gas.wanlin.entity;

import com.smart.device.message.parse.fire.smoke.entity.DeviceBaseInfo;
import lombok.Data;

/**
 * 万林可燃其他（甲烷）
 *
 * @author 三多
 * @Time 2020/4/8
 */
@Data
public class GasEntity extends DeviceBaseInfo {
    /**
     *{"client_id":"7f0000010c820000012f","dcode":"361000000030000088",
     * "event":"0009","state":1,"eventdate":1589352628,
     * "content":"机械手状态_已打开","Signal_Intensity":"6"}
     */
    /**
     * 设备客户端ID
     */
    private String clientId;
    /**
     * 设备编码
     */
    private String deviceCode;
    /**
     * 0009心跳，0002登录，0003报警
     * 0006故障，0007复位，0008机械手控制
     */
    private String event;
    private String eventString;
    /**
     * 机械手当前状态（1打开2关闭）
     */
    private Integer handState;
    private String handStateString;
    /**
     * 上报时间
     */
    private Long eventdate;
    /**
     * 机械手当前状态
     */
    private String content;
    /**
     * 信号强度
     */
    private String signalIntensity;

}
