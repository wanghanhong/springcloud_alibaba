package com.smart.common.core.controller;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart.common.constant.Constants;
import com.smart.common.core.page.PageDomain;
import com.smart.common.core.page.TableSupport;
import com.smart.common.utils.DateUtils;
import com.smart.common.utils.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.checkerframework.checker.units.qual.C;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.Objects;

/**
 * @description: web层通用数据处理
 * @author: SanDuo
 * @date: 2020/5/26 16:58
 * @version: 1.0
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected long userId;
    protected String loginName;

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据 Page<T>
     *
     */
    protected Page<T> startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        pageNum = Objects.nonNull(pageNum) ? pageNum : 1;
        pageSize = Objects.nonNull(pageSize) ? pageSize : 10;
        Page<T> resultPage = new Page<T>(pageNum, pageSize);
        OrderItem item = null;
        if (StringUtils.isNotBlank(pageDomain.getOrderByColumn()) && StringUtils.isNotBlank(pageDomain.getIsAsc())) {
            item = new OrderItem();
            item.setColumn(pageDomain.getOrderByColumn());
            if (Constants.ASC.equalsIgnoreCase(pageDomain.getIsAsc())) {
                item.setAsc(true);
            } else {
                item.setAsc(false);
            }
            resultPage.addOrder(item);
        }
        return resultPage;
    }

    /**
     * 获取request
     */
    public HttpServletRequest getRequest() {
        return ServletUtils.getRequest();
    }

    /**
     * 获取response
     */
    public HttpServletResponse getResponse() {
        return ServletUtils.getResponse();
    }

    /**
     * 获取session
     */
    public HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 解析token获取用户ID
     *
     * @return
     */
    public void getCurrentUserId() {
        String currentId = getRequest().getHeader(Constants.CURRENT_ID);
        this.userId = StringUtils.isNotBlank(currentId) ? Long.valueOf(currentId) : 0L;
    }

    /**
     * 解析token获取用户名
     *
     * @return
     */
    public void getLoginName() {
        String loginName = getRequest().getHeader(Constants.CURRENT_USERNAME);
        this.loginName = StringUtils.isNotBlank(loginName) ? loginName : "默认超级管理员";
    }

}
