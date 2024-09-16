package com.smart.card.sys.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.card.sys.common.domain.QueryRequest;
import com.smart.card.sys.system.domain.po.Menu;
import org.apache.ibatis.annotations.Param;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface MenuService extends IService<Menu> {

    List<Menu> findUserPermissions(String username);

    List<Menu> findUserMenus(String username);

    Map<String, Object> findMenus(HttpServletRequest request, Menu menu);

    List<Menu> findMenuList(Menu menu);

    void createMenu(Menu menu);

    void updateMenu(Menu menu) throws Exception;

    /**
     * 递归删除菜单/按钮
     *
     * @param menuIds menuIds
     */
    void deleteMenus(String[] menuIds) throws Exception;

    IPage<Menu> listMenu(@Param("queryRequest") QueryRequest queryRequest, @Param("menu") Menu menu);

    List<String> findUserMenusButton(String username);
}
