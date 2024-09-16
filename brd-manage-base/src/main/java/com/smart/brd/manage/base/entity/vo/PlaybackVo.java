package com.smart.brd.manage.base.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author dukzzz
 * @date 2021/4/15 16:13:下午
 * @desc
 */
@Data
public class PlaybackVo implements Serializable {
    private String endRow;
    private String firstPage;
    private String lastPage;
    private List<PlaybackVideoVo> list;
    private String pageNum;
    private String pageSize;
    private String pages;
    private String size;
    private String total;

}
