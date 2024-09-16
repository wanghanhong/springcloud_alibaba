package com.smart.common.core.page;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询返回对象
 * @author 三多
 * @Time 2020/5/28
 */
@Data
public class PageResult implements Serializable {
    private static final long serialVersionUID = 1L;
    /**总记录数*/
    private int totalCount;
    /**每页记录数*/
    private int pageSize;
     /**总页数*/
    private int totalPage;
     /**当前页数*/
    private int currPage;
    /**
     * 起始行
     */
    private int rowIndex;
    /**列表数据*/
    private List<?> list;

    /**
     *
     * @param totalCount 总记录数
     * @param pageSize   每页记录数
     * @param currPage   当前页数
     */
    public PageResult(int totalCount, int pageSize, int currPage) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
        this.rowIndex = (this.currPage - 1) * this.pageSize;
    }

    /**
     * 分页
     *
     * @param list       列表数据
     * @param totalCount 总记录数
     * @param pageSize   每页记录数
     * @param currPage   当前页数
     */
    public PageResult(List<?> list, int totalCount, int pageSize, int currPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
        this.rowIndex = (this.currPage - 1) * this.pageSize;
    }

    /**
     * 分页
     */
    public PageResult(IPage<?> page) {
        this.list = page.getRecords();
        this.totalCount = (int) page.getTotal();
        this.pageSize = (int) page.getSize();
        this.currPage = (int) page.getCurrent();
        this.totalPage = (int) page.getPages();
    }


}

