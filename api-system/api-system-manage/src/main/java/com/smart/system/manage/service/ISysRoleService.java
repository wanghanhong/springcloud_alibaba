package com.smart.system.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.common.core.page.PageResult;
import com.smart.system.manage.entity.SysRole;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author sanduo
 * @since 2020-07-10
 */
public interface ISysRoleService extends IService<SysRole> {
    /**
     * 查询角色列表
     *
     * @param startPage
     * @param role
     * @return
     */
    PageResult list(Page<T> startPage, SysRole role);
}
