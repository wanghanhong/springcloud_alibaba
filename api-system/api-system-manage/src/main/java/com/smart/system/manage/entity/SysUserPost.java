package com.smart.system.manage.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户与岗位关联表(SysUserPost)实体类
 *
 * @author SanDuo
 * @since 2020-07-14 09:29:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUserPost implements Serializable {
    private static final long serialVersionUID = -61080094903838144L;
    /**
    * 用户ID
    */
    @NotEmpty(message = "userId不能为空")
    private Long userId;
    /**
    * 岗位ID
    */
    @NotEmpty(message = "postId  不能为空")
    private Long postId;


}