package com.smart.card.sys.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.smart.card.sys.common.annotation.Log;
import com.smart.card.sys.common.controller.BaseController;
import com.smart.card.sys.common.domain.QueryRequest;
import com.smart.card.sys.common.domain.router.VueRouter;
import com.smart.card.sys.system.domain.po.Menu;
import com.smart.card.sys.system.domain.vo.MenuVo;
import com.smart.card.sys.system.manager.UserManager;
import com.smart.card.sys.system.service.MenuService;
import com.smart.common.core.domain.Result;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/menu")
public class MenuController extends BaseController {
    private Integer error_code = 10001;
    private String message;

    @Resource
    private UserManager userManager;
    @Resource
    private MenuService menuService;

    @GetMapping("/{username}")
    public Result getUserRouters(@NotBlank(message = "{required}") @PathVariable String username) {
        ArrayList<VueRouter<Menu>> result = this.userManager.getUserRouters(username);
        List<String> buttons = this.menuService.findUserMenusButton(username);
        MenuVo vo = new MenuVo();
        vo.setList(result);vo.setButtons(buttons);
        return Result.SUCCESS(vo);
    }

    @GetMapping("/list")
//  @RequiresPermissions("menu:view")
    public Result menuList(HttpServletRequest request,QueryRequest queryRequest, Menu menu) {
        Map<String, Object> result;
        if (StringUtils.isNotBlank(menu.getSelectType()) && menu.getSelectType().equalsIgnoreCase(Menu.LIST)) {
            result = getDataTable(menuService.listMenu(queryRequest, menu));
        } else {
            result = this.menuService.findMenus(request,menu);
        }
        return Result.SUCCESS(result);
    }

    @Log("新增菜单/按钮")
    @PostMapping("/add")
//    @RequiresPermissions("menu:add")
    public Result addMenu(@RequestBody Menu menu){
        try {
            this.menuService.createMenu(menu);
            return Result.SUCCESS();
        } catch (Exception e) {
            message = "新增菜单/按钮失败";
            log.error(message, e);
            return Result.FAIL(message);
        }
    }

    @Log("删除菜单/按钮")
    @GetMapping("/del/{menuIds}")
//    @RequiresPermissions("menu:delete")
    public Result deleteMenus(@NotBlank(message = "{required}") @PathVariable String menuIds){
        try {
            String[] ids = menuIds.split(StringPool.COMMA);
            for (String menuId : ids) {
               if(Long.parseLong(menuId) < 10){
                   message = "系统菜单，无法操作";
                   return Result.FAIL(message);
               }
            }
            this.menuService.deleteMenus(ids);
            return Result.SUCCESS();
        } catch (Exception e) {
            message = "删除菜单/按钮失败";
            log.error(message, e);
            return Result.FAIL(message);
        }
    }

    @Log("修改菜单/按钮")
    @PostMapping("/update")
//    @RequiresPermissions("menu:update")
    public Result updateMenu(@RequestBody Menu menu){
        try {
            if(menu.getMenuId() < 10){
                message = "系统菜单，无法操作";
                return Result.FAIL(message);
            }
            this.menuService.updateMenu(menu);
            return Result.SUCCESS();
        } catch (Exception e) {
            message = "修改菜单/按钮失败";
            log.error(message, e);
            return Result.FAIL(message);
        }
    }

    @PostMapping("/excel")
//    @RequiresPermissions("menu:export")
    public Result export(Menu menu, HttpServletResponse response){
        try {
            List<Menu> menus = this.menuService.findMenuList(menu);
            ExcelKit.$Export(Menu.class, response).downXlsx(menus, false);
            return Result.SUCCESS();
        } catch (Exception e) {
            message = "导出Excel失败";
            log.error(message, e);
            return Result.FAIL(message);
        }
    }
}
