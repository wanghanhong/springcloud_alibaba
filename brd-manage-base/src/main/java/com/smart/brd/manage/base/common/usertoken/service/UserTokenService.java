package com.smart.brd.manage.base.common.usertoken.service;

import com.smart.brd.manage.base.common.usertoken.entity.UserBean;
import javax.servlet.http.HttpServletRequest;

public interface UserTokenService {

    UserBean getUserByToken(HttpServletRequest request);

}
