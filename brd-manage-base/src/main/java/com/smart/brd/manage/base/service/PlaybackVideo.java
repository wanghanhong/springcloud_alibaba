package com.smart.brd.manage.base.service;

import com.smart.brd.manage.base.entity.vo.PlaybackVideoVo;
import com.smart.brd.manage.base.entity.vo.PlaybackVo;

import java.util.List;

/**
 * @author dukzzz
 * @date 2021/4/13 8:06:上午
 * @desc
 */
public interface PlaybackVideo {
    String getMd5(PlaybackVideoVo playbackVideoVo);
    PlaybackVo getUrlList(PlaybackVideoVo playbackVideoVo);
}
