package com.smart.card.common.usertoken.service;

import com.smart.card.common.usertoken.entity.UserBean;
import javax.servlet.http.HttpServletRequest;

public interface UserTokenService {

    UserBean getUserByToken(HttpServletRequest request);

}
