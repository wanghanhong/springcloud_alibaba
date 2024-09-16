package com.smart.common.core.page;

import com.smart.common.constant.Constants;
import com.smart.common.utils.StringUtils;
import lombok.Data;

/**
 * @description: 分页数据
 * @author: SanDuo
 * @date: 2020/5/26 16:57
 * @version: 1.0
 */
@Data
public class PageDomain {
    /**
     * 当前记录起始索引
     */
    private Integer pageNum = Constants.DEFAULT_PAGE_NUM;
    /**
     * 每页显示记录数
     */
    private Integer pageSize = Constants.DEFAULT_PAGE_SIZE;
    /**
     * 排序列
     */
    private String orderByColumn;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public String getIsAsc() {
        return isAsc;
    }

    public void setIsAsc(String isAsc) {
        this.isAsc = isAsc;
    }

    /**
     * 排序的方向 "desc" false或者 "asc". true
     */
    private String isAsc;

    public String getOrderBy() {
        if (StringUtils.isEmpty(orderByColumn)) {
            return "";
        }
        return StringUtils.toUnderScoreCase(orderByColumn) + " " + isAsc;
    }



}
