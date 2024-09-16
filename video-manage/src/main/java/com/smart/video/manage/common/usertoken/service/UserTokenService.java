package com.smart.video.manage.common.usertoken.service;

import com.smart.video.manage.common.usertoken.entity.UserBean;

import javax.servlet.http.HttpServletRequest;

public interface UserTokenService {

    UserBean getUserByToken(HttpServletRequest request);

}
