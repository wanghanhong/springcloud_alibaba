package com.smart.card.sys.system.usertoken.service;

import com.smart.card.sys.system.usertoken.entity.UserBean;
import javax.servlet.http.HttpServletRequest;

public interface UserTokenService {

    UserBean getUserByToken(HttpServletRequest request);

}
