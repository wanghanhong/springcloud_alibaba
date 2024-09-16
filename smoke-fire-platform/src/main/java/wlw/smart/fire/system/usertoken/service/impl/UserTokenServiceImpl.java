package wlw.smart.fire.system.usertoken.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import wlw.smart.fire.system.usertoken.constant.RedisConst;
import wlw.smart.fire.system.usertoken.entity.UserBean;
import wlw.smart.fire.system.usertoken.service.UserTokenService;
import com.smart.common.utils.EncryptUtil;
import com.smart.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class UserTokenServiceImpl implements UserTokenService {

    private UserBean getUserByToken(String token){
        UserBean user = new UserBean();
        try {
            if(StringUtils.isNotBlank(token)){
                // 把加过密的token 解密
                EncryptUtil encryptUtil = new EncryptUtil(RedisConst.TOKEN_CACHE_PREFIX);
                String tokenStr = encryptUtil.decrypt(token);
                if(StringUtils.isNotBlank(tokenStr)){
                    // 从token 中获取附加的各种参数
                    DecodedJWT jwt = JWT.decode(tokenStr);
                    Claim userId = jwt.getClaim("userId");
                    String username = jwt.getClaim("username").asString();
                    Claim deptId = jwt.getClaim("deptId");
                    String roleId = jwt.getClaim("roleId").asString();
                    String mobile = jwt.getClaim("mobile").asString();
                    Claim isXcx = jwt.getClaim("isXcx");

                    String province = jwt.getClaim("province").asString();
                    String city = jwt.getClaim("city").asString();
                    String county = jwt.getClaim("county").asString();
                    String town = jwt.getClaim("town").asString();
                    String housing = jwt.getClaim("housing").asString();
                    String deptIds = jwt.getClaim("deptIds").asString();

                    user.setUsername(username);
                    user.setMobile(mobile);
                    user.setRoleId(roleId);
                    if(userId != null){
                        user.setUserId(userId.asLong());
                    }
                    if(deptId != null){
                        user.setDeptId(deptId.asLong());
                    }
                    if(isXcx != null){
                        user.setIsXcx(isXcx.asInt());
                    }
                    user.setProvince(province);
                    user.setCity(city);
                    user.setCounty(county);
                    user.setTown(town);
                    user.setHousing(housing);

                    user.setDeptIds(deptIds);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public UserBean getUserByToken(HttpServletRequest request){
        String auth = request.getHeader("Authorization");
        UserBean user = getUserByToken(auth);
        return user;
    }

    public static void main(String [] args){
        UserTokenServiceImpl impl = new UserTokenServiceImpl();
        String token = "b25e39b47e774b4a05b3cb1555fc377f209457c3fd339d373d3fca7b1ea8be56fdc6ed05b7ffb0700e7300d242fb83b51ab15dc8e2922c964405e029b5b5dfb5c2f2c5bb1aee0651056a6adbce31e440144d42dda8569f82f28598e8f028ce340d0413690c10ba7c75d5d5bf552045b236fa4773b907a411f5c86f27ff8b54d113bd4ef1b8fa2caf";
        UserBean user = impl.getUserByToken(token);
        System.out.println(12);
    }

}
