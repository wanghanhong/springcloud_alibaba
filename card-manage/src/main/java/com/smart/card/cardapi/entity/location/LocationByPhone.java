package com.smart.card.cardapi.entity.location;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author junglelocal
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LocationByPhone implements Serializable {

    private static final long serialVersionUID = -1590337662810409056L;

    /**
     * 	返回状态标识
     * 	返回0，表示查询成功；返回-1，表示没有权限（用户名密码错误或iccid/imsi不属于该用户）；返回-2，表示参数错误（iccid或imsi号码错误）
     * 	；返回-3，表示sign值错误（加密方式错误）
     */
    private String resultCode;

    /**
     * 返回标识说明
     * 1、处理成功2、用户名或密码错误，无权限3、未查询到数据4、未找到4G IMSI号码5、未找到接入号码6、未找到ICCID
     */
    private String resultMsg;

    /**
     * 返回结果标识
     */
    private String msid_type;

    /**
     * 定位码号
     */
    private String msid;

    /**
     * 定位信息列表
     */
    private String latitudetype;

    /**
     * 定义漫游城市
     */
    private String roamcity;

    /**
     * 	经度
     */
    private String longitude;

    /**
     * 定位时间
     */
    private String localtime;

    /**
     * 定位结果值
     */
    private String positionresult;

    /**
     * 位置信息返回的扇区半径信息
     */
    private String radius;

    /**
     * 定义位置来源
     */
    private String posour;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 用于表示返回经纬度所对应的GIS的区号
     */
    private String areacode;

    /**
     * 经度类型
     */
    private String longitudetype;
}
