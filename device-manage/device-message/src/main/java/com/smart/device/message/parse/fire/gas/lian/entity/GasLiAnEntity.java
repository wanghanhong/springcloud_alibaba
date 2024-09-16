package com.smart.device.message.parse.fire.gas.lian.entity;

import lombok.Data;

/**
 * @Desc 力安的气感实体类
 * @author yangfei
 * @Date 2020/12/3 10:05
*/
@Data
public class GasLiAnEntity {
    /*
    {
    "deviceCode":"869162052818257","productId":"15017088","IMEI":"869162052818257","protocol":"LWM2M",
    "payload":{"valve_state":2,"temperature":0,"medium":0,"gas_value":0,"gas_sensor_state":1,
    "fan_state":2,"battery_voltage":1.1,"battery_value":100}
    * */
    private String productId;       //产品id
    private String protocol;        //通信协议

    private String payload;
    private Integer valveState;     //联动阀门状态 0：阀门开； 1：阀门关； 2：无阀门；
    private Integer temperature;    //监测温度
    private Integer medium;         //监测介质 0：天然气； 1：液化石油气； 2：煤层气； 3：一氧化碳； 4：硫化氢
    private Integer gasValue;       //气体浓度百分比 0-100%
    private Integer gasSensorState;  //燃气检测状态 0：正常；1：低浓度报警；2：高浓度报警；
    private Integer fanState;         //联动风扇状态 0：风扇关； 1：风扇开； 2：无风扇； 3：风扇未知
    private float batteryVoltage;       //电池电压 取值范围：0-5；
    private Integer batteryValue;        //电池电量      取值范围：1-100%
    private Integer errorCode;        //故障

    private String terminalType;       //终端型号
    private String software_version;     //软件版本
    private Integer sinr;             //信号与干扰加噪声比
    private Integer rsrp;             //参考信号接收功率
    private Integer pci;             //物理小区标识
    private String manufacturer_name;          //厂家名称
    private float heartbeat_time;               //心跳周期
    private String hardware_version;           //硬件版本
    private Integer ecl;                      //无线信号覆盖等级
    private Integer cell_id;            //小区位置信息
    private String ICCID;              //ICCID

    private String deviceId;
    private String IMSI;
    private String IMEI;
    private String deviceCode;
    /**
     * 1001报警 1002故障 1003低压 -1心跳 7正常
     */
    private String event;
    private String eventString;

    private Long eventdate;
    private String signalIntensity;
}
