package com.smart.system.manage.controller;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.smart.common.core.controller.BaseController;
import com.smart.common.core.domain.Result;
import com.smart.common.core.page.PageResult;
import com.smart.common.utils.IpUtils;
import com.smart.common.utils.RandomUtil;
import com.smart.common.valids.Update;
import com.smart.system.common.util.PasswordUtil;
import com.smart.system.manage.entity.SysUser;
import com.smart.system.manage.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author sanduo
 * @since 2020-07-10
 */
@Api("用户管理")
@RestController
@RequestMapping("/v1/sys/user")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService userService;

    /**
     * @param userId 用户ID
     * @return Result
     */
    @ApiOperation("根据用户ID查询用户")
    @GetMapping("/get/{userId}")
    public Result queryByUserId(@PathVariable("userId") @NotNull(message = "用户ID不能为空") String userId) {
        SysUser user = userService.query(userId);
        return Result.SUCCESS(user);
    }

    /**
     * 根据登录名查询用户（手机号、或者逻辑用户名、或者邮箱）
     *
     * @param loginName
     * @return
     */
    @ApiOperation("根据用户名称查询用户")
    @GetMapping("/query/{loginName}")
    public Result queryByUserName(@PathVariable("loginName") String loginName) {

        LambdaQueryChainWrapper<SysUser> condition = this.userService.lambdaQuery().eq(Objects.nonNull(loginName), SysUser::getLoginName, loginName)
                .or()
                .eq(Objects.nonNull(loginName), SysUser::getPhoneNumber, loginName)
                .or()
                .eq(Objects.nonNull(loginName), SysUser::getEmail, loginName);
        SysUser user = this.userService.getOne(condition);
        return Result.SUCCESS(user);
    }

    /**
     * 根据用户名模糊查询(应该市准确查询)
     *
     * @param userName
     * @return
     */
    @ApiOperation("根据用户名称查询用户-模糊查询")
    @ApiImplicitParams({@ApiImplicitParam(name = "userName", value = "用户名", paramType = "path", dataTypeClass = String.class),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数", paramType = "query", dataTypeClass = int.class),
            @ApiImplicitParam(name = "pageNum", value = "当前页码", paramType = "query", dataTypeClass = int.class)})
    @GetMapping("/find/{userName}")
    @Deprecated
    public Result findByUserName(@PathVariable("userName") String userName, @RequestParam(name = "pageSize", required = false) String pageSize, @RequestParam(name = "pageNum", required = false) String pageNum) {

        PageResult pageResult = userService.queryByUserName(startPage(), userName);
        return Result.SUCCESS(pageResult);
    }

    /**
     * 查询列表分页
     * 手机号
     * 邮箱
     * 状态
     * 男女
     * 用户类型
     *
     * @param pageSize
     * @param pageNum
     * @return
     */
    @ApiOperation("查询用户列表")
    @GetMapping("/list")
    public Result list(SysUser user, @RequestParam(name = "pageSize", required = false) String pageSize, @RequestParam(name = "pageNum", required = false) String pageNum) {
        PageResult pageResult = userService.listUserByCondition(startPage(), user);
        return Result.SUCCESS(pageResult);
    }

    /**
     * 创建用户
     *
     * @param user
     * @return
     */
    @ApiOperation("创建用户")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody SysUser user) {
        //手动设置ID
        user.setUserId(IdWorker.getId());
        //加盐
        user.setSalt(RandomUtil.randomStr(6));
        user.setPassword(
                PasswordUtil.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        user.setCreateTime(LocalDateTime.now());
        user.setCreateBy(this.userId + this.loginName);
        user.setLoginIp(IpUtils.getIpAddr(getRequest()));
        user.setLoginDate(LocalDateTime.now());

        int result = userService.create(user);
        return Result.SUCCESS(result);
    }

    @ApiOperation("修改用户")
    @PutMapping("/update")
    public Result update(@Validated(Update.class) @RequestBody SysUser user) {
        user.setUpdateBy(this.userId + this.loginName);
        user.setUpdateTime(LocalDateTime.now());
        user.setLoginIp(IpUtils.getIpAddr(getRequest()));
        user.setLoginDate(LocalDateTime.now());
        if (userService.update(user)) {
            return Result.SUCCESS();
        }
        return Result.FAIL("修改用户失败");

    }

    @ApiOperation("删除用户")
    @DeleteMapping("/delete")
    public Result delete(String[] ids) {
        if (userService.removeByIds(Arrays.asList(ids))) {
            return Result.SUCCESS();
        }
        return Result.FAIL("删除用户失败");

    }

}
