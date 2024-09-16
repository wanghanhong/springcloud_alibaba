package wlw.smart.fire.system.usertoken.service;

import wlw.smart.fire.system.usertoken.entity.UserBean;
import javax.servlet.http.HttpServletRequest;

public interface UserTokenService {

    UserBean getUserByToken(HttpServletRequest request);

}
