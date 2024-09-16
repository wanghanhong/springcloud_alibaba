package com.smart.brd.manage.base.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dukzzz
 * @date 2021/3/29 9:26:上午
 * @desc
 */
@Data
public class ComponentVo implements Serializable {
    /**
     * 存栏数量
     */
    private Integer livestockNum;
    /**
     *异常数量
     */
    private Integer abnormalNum;
    /**
     *温度
     */
    private String temperature;
    /**
     *湿度
     */
    private String humidity;
    /**
     *氨气
     */
    private String ammonia;
    /**
     *硫化氢
     */
    private String sulfide;
    /**
     * url
     */
    private String urlVlc;

}
