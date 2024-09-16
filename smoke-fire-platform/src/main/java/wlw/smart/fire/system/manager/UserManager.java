package wlw.smart.fire.system.manager;

import org.springframework.stereotype.Service;
import wlw.smart.fire.common.domain.router.RouterMeta;
import wlw.smart.fire.common.domain.router.VueRouter;
import wlw.smart.fire.common.service.CacheService;
import wlw.smart.fire.common.utils.FebsUtil;
import wlw.smart.fire.common.utils.TreeUtil;
import wlw.smart.fire.system.domain.po.Menu;
import wlw.smart.fire.system.domain.po.Role;
import wlw.smart.fire.system.domain.po.User;
import wlw.smart.fire.system.domain.po.UserConfig;
import wlw.smart.fire.system.service.MenuService;
import wlw.smart.fire.system.service.RoleService;
import wlw.smart.fire.system.service.UserConfigService;
import wlw.smart.fire.system.service.UserService;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 封装一些和 User相关的业务操作
 *
 * @author Pano
 */
@Service
public class UserManager {

    @Resource
    private CacheService cacheService;
    @Resource
    private RoleService roleService;
    @Resource
    private MenuService menuService;
    @Resource
    private UserService userService;
    @Resource
    private UserConfigService userConfigService;


    /**
     * 通过用户名获取用户基本信息
     *
     * @param username 用户名
     * @return 用户基本信息
     */
    public User getUser(String username) {
        return FebsUtil.selectCacheByTemplate(
                () -> this.cacheService.getUser(username),
                () -> this.userService.findByName(username));
    }

    /**
     * 通过用户名获取用户角色集合
     *
     * @param username 用户名
     * @return 角色集合
     */
    public Set<String> getUserRoles(String username) {
        List<Role> roleList = FebsUtil.selectCacheByTemplate(
                () -> this.cacheService.getRoles(username),
                () -> this.roleService.findUserRole(username));
        return roleList.stream().map(Role::getRoleName).collect(Collectors.toSet());
    }

    /**
     * 通过用户名获取用户权限集合
     *
     * @param username 用户名
     * @return 权限集合
     */
    public Set<String> getUserPermissions(String username) {
        List<Menu> permissionList = FebsUtil.selectCacheByTemplate(
                () -> this.cacheService.getPermissions(username),
                () -> this.menuService.findUserPermissions(username));
        return permissionList.stream().map(Menu::getPerms).collect(Collectors.toSet());
    }

    /**
     * 通过用户名构建 Vue路由
     *
     * @param username 用户名
     * @return 路由集合
     */
    public ArrayList<VueRouter<Menu>> getUserRouters(String username) {
        List<VueRouter<Menu>> routes = new ArrayList<>();
        List<Menu> menus = this.menuService.findUserMenus(username);
        menus.forEach(menu -> {
            VueRouter<Menu> route = new VueRouter<>();
            route.setId(menu.getMenuId().toString());
            route.setMenuId(menu.getMenuId());
            route.setParentId(menu.getParentId().toString());
            route.setIcon(menu.getIcon());
            route.setPath(menu.getPath());
            route.setComponent(menu.getComponent());
            route.setName(menu.getMenuName());
            route.setOrderNum(menu.getOrderNum());
            route.setMeta(new RouterMeta(true, null));
            routes.add(route);
        });
        ArrayList<VueRouter<Menu>> list  = TreeUtil.buildVueRouter(routes);
        for (VueRouter<Menu> topRoot : list) {
            List<VueRouter<Menu>>  list2 = topRoot.getChildren();
            if(list2 != null){
                for(VueRouter<Menu> root:list2){
                    List<VueRouter<Menu>>  childs = root.getChildren();
                    if(childs != null){
                        Collections.sort(childs, new Comparator<VueRouter>() {
                            @Override
                            public int compare(VueRouter u1, VueRouter u2) {
                                Double d1 = u1.getOrderNum()==null?0:u1.getOrderNum();
                                Double d2 = u2.getOrderNum()==null?0:u2.getOrderNum();
                                Double diff = d1 - d2;
                                if (diff > 0) {
                                    return 1;
                                }else if (diff < 0) {
                                    return -1;
                                }
                                return 0; //相等为0
                            }
                        });
                    }
                    root.setChildren(childs);
                }
            }
            topRoot.setChildren(list2);
        }
        return list;
    }

    /**
     * 通过用户 ID获取前端系统个性化配置
     *
     * @param userId 用户 ID
     * @return 前端系统个性化配置
     */
    public UserConfig getUserConfig(String userId) {
        return FebsUtil.selectCacheByTemplate(
                () -> this.cacheService.getUserConfig(userId),
                () -> this.userConfigService.findByUserId(userId));
    }

    /**
     * 将用户相关信息添加到 Redis缓存中
     *
     * @param user user
     */
    public void loadUserRedisCache(User user) throws Exception {
        // 缓存用户
        cacheService.saveUser(user.getUsername());
        // 缓存用户角色
        cacheService.saveRoles(user.getUsername());
        // 缓存用户权限
        cacheService.savePermissions(user.getUsername());
        // 缓存用户个性化配置
        cacheService.saveUserConfigs(String.valueOf(user.getUserId()));
    }

    /**
     * 将用户角色和权限添加到 Redis缓存中
     *
     * @param userIds userIds
     */
    public void loadUserPermissionRoleRedisCache(List<String> userIds) throws Exception {
        for (String userId : userIds) {
            User user = userService.getById(userId);
            if (Objects.nonNull(user)) {
                // 缓存用户角色
                cacheService.saveRoles(user.getUsername());
                // 缓存用户权限
                cacheService.savePermissions(user.getUsername());
            }

        }
    }

    /**
     * 通过用户 id集合批量删除用户 Redis缓存
     *
     * @param userIds userIds
     */
    public void deleteUserRedisCache(String... userIds) throws Exception {
        for (String userId : userIds) {
            User user = userService.getById(userId);
            if (user != null) {
                cacheService.deleteUser(user.getUsername());
                cacheService.deleteRoles(user.getUsername());
                cacheService.deletePermissions(user.getUsername());
            }
            cacheService.deleteUserConfigs(userId);
        }
    }

    public List<Menu> getUserMenus(String username) {
        return this.menuService.findUserMenus(username);
    }
}
