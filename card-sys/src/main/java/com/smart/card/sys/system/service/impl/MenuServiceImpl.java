package com.smart.card.sys.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.card.sys.common.domain.FebsConst;
import com.smart.card.sys.common.domain.QueryRequest;
import com.smart.card.sys.common.domain.Tree;
import com.smart.card.sys.common.utils.SortUtil;
import com.smart.card.sys.common.utils.TreeUtil;
import com.smart.card.sys.system.dao.MenuMapper;
import com.smart.card.sys.system.domain.po.Menu;
import com.smart.card.sys.system.manager.UserManager;
import com.smart.card.sys.system.usertoken.entity.UserBean;
import com.smart.card.sys.system.usertoken.service.UserTokenService;
import com.smart.card.sys.system.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@Service("menuService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Resource
    private UserManager userManager;
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private UserTokenService userTokenService;


    @Override
    public List<Menu> findUserPermissions(String username) {
        if (ObjectUtil.equal(username, FebsConst.ADMIN_ACCOUNT)) {
            return baseMapper.selectList(
                    new LambdaQueryWrapper<Menu>()
                            .isNotNull(Menu::getPerms)
                            .ne(Menu::getPerms, "")
            );
        }
        return this.baseMapper.findUserPermissions(username);
    }

    @Override
    public List<Menu> findUserMenus(String username) {
        return this.baseMapper.findUserMenus(username);
    }

    @Override
    public List<String> findUserMenusButton(String username) {
        return this.baseMapper.findUserMenusButton(username);
    }

    @Override
    public Map<String, Object> findMenus(HttpServletRequest request,Menu menu) {
        Map<String, Object> result = new HashMap<>(16);
        try {
            UserBean userBean = userTokenService.getUserByToken(request);
            List<Menu> menus = new ArrayList<>();
            //为超级管理员和平台管理员可编辑全部权限
            if(userBean != null && Integer.parseInt(userBean.getRoleId()) <2 ){
                LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
                findMenuCondition(queryWrapper, menu);
                queryWrapper.orderByAsc(Menu::getOrderNum);
                menus = baseMapper.selectList(queryWrapper);
            }else{
                menus = menuMapper.queryMenusByRoleId(userBean.getRoleId());
            }
            List<Tree<Menu>> trees = new ArrayList<>();
            List<String> ids = new ArrayList<>();
            buildTrees(trees, menus, ids);

            result.put("ids", ids);
            if (StringUtils.equals(menu.getType(), FebsConst.TYPE_BUTTON)) {
                result.put("rows", trees);
            } else {
                Tree<Menu> menuTree = TreeUtil.build(trees);
                result.put("rows", menuTree);
            }

            result.put("total", menus.size());
        } catch (NumberFormatException e) {
            log.error("查询菜单失败", e);
            result.put("rows", null);
            result.put("total", 0);
        }
        return result;
    }


    @Override
    public List<Menu> findMenuList(Menu menu) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        findMenuCondition(queryWrapper, menu);
        queryWrapper.orderByAsc(Menu::getMenuId);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createMenu(Menu menu) {
        menu.setCreateTime(new Date());
        setMenu(menu);
        this.save(menu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMenu(Menu menu) throws Exception {
        menu.setModifyTime(new Date());
        if(menu.getOrderNum() == null){
            menu.setOrderNum(10d);
        }
        setMenu(menu);
        baseMapper.updateById(menu);

        // 查找与这些菜单/按钮关联的用户
        List<String> userIds = this.baseMapper.findUserIdsByMenuId(String.valueOf(menu.getMenuId()));
        // 重新将这些用户的角色和权限缓存到 Redis中
        this.userManager.loadUserPermissionRoleRedisCache(userIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMenus(String[] menuIds) throws Exception {
        this.delete(Arrays.asList(menuIds));
        for (String menuId : menuIds) {
            // 查找与这些菜单/按钮关联的用户
            List<String> userIds = this.baseMapper.findUserIdsByMenuId(String.valueOf(menuId));
            // 重新将这些用户的角色和权限缓存到 Redis中
            this.userManager.loadUserPermissionRoleRedisCache(userIds);
        }
    }

    @Override
    public IPage<Menu> listMenu(QueryRequest queryRequest, Menu menu) {
        try {
            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
            //匹配姓名
            queryWrapper.like(StringUtils.isNotBlank(menu.getMenuName()), Menu::getMenuName, menu.getMenuName());
            //匹配店铺
            queryWrapper.eq(StringUtils.isNotBlank(menu.getType()), Menu::getType, menu.getType());

            Page<Menu> page = new Page<>();
            SortUtil.handlePageSort(queryRequest, page, true);
            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取菜单信息失败", e);
            return null;
        }
    }

    private void buildTrees(List<Tree<Menu>> trees, List<Menu> menus, List<String> ids) {
        menus.forEach(menu -> {
            ids.add(menu.getMenuId().toString());
            Tree<Menu> tree = new Tree<>();
            tree.setId(menu.getMenuId().toString());
            tree.setMenuId(menu.getMenuId());
            tree.setKey(tree.getId());
            tree.setParentId(menu.getParentId().toString());
            tree.setText(menu.getMenuName());
            tree.setTitle(tree.getText());
            tree.setIcon(menu.getIcon());
            tree.setComponent(menu.getComponent());
            tree.setCreateTime(menu.getCreateTime());
            tree.setModifyTime(menu.getModifyTime());
            tree.setPath(menu.getPath());
            tree.setOrderNum(menu.getOrderNum());
            tree.setPermission(menu.getPerms());
            tree.setType(menu.getType());
            trees.add(tree);
        });
    }

    private void setMenu(Menu menu) {
        if (menu.getParentId() == null) {
            menu.setParentId(0L);
        }
        if (Menu.TYPE_BUTTON.equals(menu.getType())) {
            menu.setPath(null);
            menu.setIcon(null);
            menu.setComponent(null);
            Menu entity = menuMapper.selectById(menu.getParentId());
            String path = entity.getPath();
            path = path.substring(path.lastIndexOf("/")+1);
            menu.setPerms(path+":"+menu.getButtonValue());
            menu.setMenuName(entity.getMenuName()+getButtonName(menu.getButtonValue()));
        }else{
            menu.setType("0");
        }
    }

    private String getButtonName(String buttonValue){
        String res = "";
        switch(buttonValue){
            case "add" :
                res = "新增";
                break;
            case "update" :
                res = "修改";
                break;
            case "del" :
                res = "删除";
                break;
            case "export" :
                res = "导出";
                break;
            default :
                res = "";
        }
        return res;
    }
    private void findMenuCondition(LambdaQueryWrapper<Menu> queryWrapper, Menu menu) {
        if (StringUtils.isNotBlank(menu.getMenuName())) {
            queryWrapper.eq(Menu::getMenuName, menu.getMenuName());
        }
        if (StringUtils.isNotBlank(menu.getType())) {
            queryWrapper.eq(Menu::getType, menu.getType());
        }
        if (StringUtils.isNotBlank(menu.getCreateTimeFrom()) && StringUtils.isNotBlank(menu.getCreateTimeTo())) {
            queryWrapper
                    .ge(Menu::getCreateTime, menu.getCreateTimeFrom())
                    .le(Menu::getCreateTime, menu.getCreateTimeTo());
        }
    }


    private void delete(List<String> menuIds) {
        removeByIds(menuIds);

        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Menu::getParentId, menuIds);
        List<Menu> menus = baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(menus)) {
            List<String> menuIdList = new ArrayList<>();
            menus.forEach(m -> menuIdList.add(String.valueOf(m.getMenuId())));
            this.delete(menuIdList);
        }
    }

}
