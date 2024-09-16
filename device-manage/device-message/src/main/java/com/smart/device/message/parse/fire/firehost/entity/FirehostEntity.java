package com.smart.device.message.parse.fire.firehost.entity;

import com.smart.device.message.parse.fire.smoke.entity.DeviceBaseInfo;
import lombok.Data;

/**
 * 万林可燃其他（甲烷）
 *
 * @author 三多
 * @Time 2020/4/8
 */
@Data
public class FirehostEntity extends DeviceBaseInfo {
    /**
     *{{"iccid":"","client_id":"7f0000010c1f00000176","dcode":"868000000001003196",
     * "event":"0003","loop":"000","address":"251","eventdate":1589871907,
     * "content":"报警_251防区主机SOS"}
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
     * 0001	心跳	0002	登录	0003	报警
     * 0006	故障	0007	复位	000c	主机上传配件情况
     * 000d	校准主机时间
     */
    private String event;

    private String address;
    private String loop;
    /**
     * 上报时间
     */
    private Long eventdate;
    /**
     * 报警内容
     */
    private String content;


}
