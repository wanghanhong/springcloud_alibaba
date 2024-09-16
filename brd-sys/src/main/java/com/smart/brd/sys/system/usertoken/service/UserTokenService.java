package com.smart.brd.sys.system.usertoken.service;

import com.smart.brd.sys.system.usertoken.entity.UserBean;
import javax.servlet.http.HttpServletRequest;

public interface UserTokenService {

    UserBean getUserByToken(HttpServletRequest request);

}
