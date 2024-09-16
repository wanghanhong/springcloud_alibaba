package com.smart.system.manage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.smart.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author sanduo
 * @since 2020-07-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单标识
     */
    private String menuKey;

    /**
     * 菜单布局
     */
    private String component;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 打开方式
     */
    private String target;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    private String menuType;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    private String visible;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 链接
     */
    private String path;

    /**
     * 重定向
     */
    private String redirect;

    /**
     * 强制菜单显示为Item而不是SubItem
     */
    private Boolean hiddenChildren;

    /**
     * 特殊 隐藏 PageHeader 组件中的页面带的 面包屑和页面标题栏
     */
    private Boolean hiddenHeader;


}
