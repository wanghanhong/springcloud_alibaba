package wlw.smart.fire.system.controller;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wlw.smart.fire.common.annotation.Log;
import wlw.smart.fire.common.controller.BaseController;
import wlw.smart.fire.common.domain.FebsResponse;
import wlw.smart.fire.common.domain.QueryRequest;
import wlw.smart.fire.common.domain.router.VueRouter;
import wlw.smart.fire.common.exception.FebsException;
import wlw.smart.fire.system.domain.po.Menu;
import wlw.smart.fire.system.manager.UserManager;
import wlw.smart.fire.system.service.MenuService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Pano
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {

    private String message;

    @Resource
    private UserManager userManager;
    @Resource
    private MenuService menuService;

    @GetMapping("/{username}")
    public FebsResponse getUserRouters(@NotBlank(message = "{required}") @PathVariable String username) {
        ArrayList<VueRouter<Menu>> result = this.userManager.getUserRouters(username);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(result);
    }

    @GetMapping
//    @RequiresPermissions("menu:view")
    public FebsResponse menuList(QueryRequest queryRequest, Menu menu) {
        Map<String, Object> result;
        if (StringUtils.isNotBlank(menu.getSelectType()) && menu.getSelectType().equalsIgnoreCase(Menu.LIST)) {
            result = getDataTable(menuService.listMenu(queryRequest, menu));
        } else {
            result = this.menuService.findMenus(menu);
        }
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(result);
    }

    @Log("新增菜单/按钮")
    @PostMapping
    //  @RequiresPermissions("menu:add")
    public FebsResponse addMenu(@Valid @RequestBody Menu menu) throws FebsException {
        try {
            this.menuService.createMenu(menu);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "新增菜单/按钮失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除菜单/按钮")
    @DeleteMapping("/{menuIds}")
    // @RequiresPermissions("menu:delete")
    public FebsResponse deleteMenus(@NotBlank(message = "{required}") @PathVariable String menuIds) throws FebsException {
        try {
            String[] ids = menuIds.split(StringPool.COMMA);
            this.menuService.deleteMenus(ids);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "删除菜单/按钮失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改菜单/按钮")
    @PutMapping
    // @RequiresPermissions("menu:update")
    public FebsResponse updateMenu(@Valid @RequestBody Menu menu) throws FebsException {
        try {
            this.menuService.updateMenu(menu);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "修改菜单/按钮失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("excel")
    //@RequiresPermissions("menu:export")
    public void export(Menu menu, HttpServletResponse response) throws FebsException {
        try {
            List<Menu> menus = this.menuService.findMenuList(menu);
            ExcelKit.$Export(Menu.class, response).downXlsx(menus, false);
        } catch (Exception e) {
            message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
