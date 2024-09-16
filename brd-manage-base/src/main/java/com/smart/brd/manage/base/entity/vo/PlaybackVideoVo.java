package com.smart.brd.manage.base.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dukzzz
 * @date 2021/4/12 17:43:下午
 * @desc
 */
@Data
public class PlaybackVideoVo implements Serializable {
    /**
     *回放地址
     */
    private String httpUri;
    /**
     *大小
     */
    private String playDataSize;
    /**
     *结束时间
     */
    private String playEndTime;
    /**
     *开始时间
     */
    private String playStartTime;
    /**
     *截图
     */
    private String snapShotUri;
    /**
     *页码
     */
    private String pageNum;
    /**
     *每页数量
     */
    private String pageSize;
    /**
     * 通道号
     */
    private String deviceCode;
}
