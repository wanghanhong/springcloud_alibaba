package com.smart.card.sys.system.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserVo {

    private Long userId;

    private String username;

    private String password;

    private String status;

    private String avatar;

    private String userNames;

    private String openId;

    private String oldPassword;

    private Long timeExpire;
}
