package com.smart.device.message.parse.fire.electric.wanlin.entity;


import lombok.Data;

@Data
public class ElectricWanlinEntity {

    /**
     * {"type":"0301","chipcode":"701904012751507255","signal_intensity":17,"current1":"0","current2":"0_02","current3":"0_01","current4":"0_04","temperature1":"0","temperature2":"0","temperature3":"0","temperature4":"0","client_id":"7f0000010bba00001ba9","eventdate":1589944430}
     * <p>
     * {"type":"0301","chipcode":"701904012751507255","signal_intensity":17,"current1":"0",
     * "current2":"0_02","current3":"0_01","current4":"0_04","temperature1":"0","temperature2":"0",
     * "temperature3":"0","temperature4":"0","client_id":"7f0000010bba00001ba9","eventdate":1589944430}
     * <p>
     * /**
     * -------------------------
     */
    private String type; //  类型
    private String chipcode;//  设备号码
    private Integer signalIntensity; //  信号强度
    private String current1; //  漏 电 电 流 （ 毫 安 ）
    private String current2; //  漏 电 电 流 （ 毫 安 ）
    private String current3; //  漏 电 电 流 （ 毫 安 ）
    private String current4; //  漏 电 电 流 （ 毫 安 ）

    private String temperature1; // 温度（摄氏度）
    private String temperature2; // 温度（摄氏度）
    private String temperature3; // 温度（摄氏度）
    private String temperature4; // 温度（摄氏度）

    private String clientId; //  系统 ID
    private Long eventdate; // 事件时间
    private String event; // 事件代码

    /**
     * -----------------------------------------------------------
     */


}
