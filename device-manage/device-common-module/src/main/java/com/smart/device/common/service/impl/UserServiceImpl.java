package com.smart.device.common.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.smart.common.utils.EncryptUtil;
import com.smart.common.utils.StringUtils;
import com.smart.device.common.access.entity.vo.DeviceAccessVO;
import com.smart.device.common.constant.RedisConst;
import com.smart.device.common.entity.UserBean;
import com.smart.device.common.install.entity.*;
import com.smart.device.common.install.entity.vo.DeviceCompanyVo;
import com.smart.device.common.install.entity.vo.DeviceMonitorVo;
import com.smart.device.common.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService{


    private UserBean getUserByToken(String token){
        UserBean user = null;
        try {
            if(StringUtils.isNotBlank(token)){
                // 把加过密的token 解密
                EncryptUtil encryptUtil = new EncryptUtil(RedisConst.TOKEN_CACHE_PREFIX);
                String tokenStr = encryptUtil.decrypt(token);
                // 从token 中获取附加的各种参数
                DecodedJWT jwt = JWT.decode(tokenStr);
                Claim userId = jwt.getClaim("userId");
                String username = jwt.getClaim("username").asString();
                Claim deptId = jwt.getClaim("deptId");
                String mobile = jwt.getClaim("mobile").asString();
                Claim isXcx = jwt.getClaim("isXcx");

                String province = jwt.getClaim("province").asString();
                String city = jwt.getClaim("city").asString();
                String county = jwt.getClaim("county").asString();
                String town = jwt.getClaim("town").asString();
                String housing = jwt.getClaim("housing").asString();

                user = new UserBean();
                user.setUsername(username);
                user.setMobile(mobile);
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

    @Override
    public UserBean setDataAuth(HttpServletRequest request,TBaseCompany entity) {
        UserBean user = getUserByToken(request);
        if(user != null){
            if(user.getDeptId() != null){
                entity.setId(user.getDeptId());
            }
            if(StringUtils.isNotBlank(user.getProvince())){
                entity.setProvince(user.getProvince());
            }
            if(StringUtils.isNotBlank(user.getCity())){
                entity.setCity(user.getCity());
            }
            if(StringUtils.isNotBlank(user.getCounty())){
                entity.setCounty(user.getCounty());
            }
            if(StringUtils.isNotBlank(user.getTown())){
                entity.setTown(user.getTown());
            }
        }
        return user;
    }

    @Override
    public UserBean setDataAuth(HttpServletRequest request,DeviceCompanyVo vo) {
        UserBean user = getUserByToken(request);
        try {
            if(user != null ){
                if(user.getIsXcx() != null ){
                    if(user.getIsXcx() == -1 ){
                        vo.setUserId(user.getUserId());
                    }else if(user.getIsXcx() == 1 ){
                        // 小程序的C端用户，用opuserid 过滤
                        vo.setOpUserId(user.getUserId());
                    }else{
                        // 一般用户，根据部门过滤
                        vo.setCompanyId(user.getDeptId());
                    }
                }else{
                    vo.setCompanyId(user.getDeptId());
                }
                if(StringUtils.isNotBlank(user.getProvince())){
                    vo.setProvince(user.getProvince());
                }
                if(StringUtils.isNotBlank(user.getCity())){
                    vo.setCity(user.getCity());
                }
                if(StringUtils.isNotBlank(user.getCounty())){
                    vo.setCounty(user.getCounty());
                }
                if(StringUtils.isNotBlank(user.getTown())){
                    vo.setTown(user.getTown());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    @Override
    public UserBean setDataAuth(HttpServletRequest request, TBaseBuilding entity) {
        UserBean user = getUserByToken(request);
        if(user != null){
            if(user.getDeptId() != null){
                entity.setCompanyId(user.getDeptId());
            }
            if(StringUtils.isNotBlank(user.getProvince())){
                entity.setProvince(user.getProvince());
            }
            if(StringUtils.isNotBlank(user.getCity())){
                entity.setCity(user.getCity());
            }
            if(StringUtils.isNotBlank(user.getCounty())){
                entity.setCounty(user.getCounty());
            }
            if(StringUtils.isNotBlank(user.getTown())){
                entity.setTown(user.getTown());
            }
        }
        return user;
    }

    @Override
    public UserBean setDataAuth(HttpServletRequest request, TManagerSmoke entity) {
        UserBean user = getUserByToken(request);
        if(user != null){
            if(user.getIsXcx() != null && user.getIsXcx() > 0){
                entity.setOpUserId(user.getUserId());
            }else{
                entity.setCompanyId(user.getDeptId());
            }
            if(StringUtils.isNotBlank(user.getProvince())){
                entity.setProvince(user.getProvince());
            }
            if(StringUtils.isNotBlank(user.getCity())){
                entity.setCity(user.getCity());
            }
            if(StringUtils.isNotBlank(user.getCounty())){
                entity.setCounty(user.getCounty());
            }
            if(StringUtils.isNotBlank(user.getTown())){
                entity.setTown(user.getTown());
            }
        }
        return user;
    }

    @Override
    public UserBean setDataAuth(HttpServletRequest request, TManagerElectric entity) {
        UserBean user = getUserByToken(request);
        if(user != null){
            if(user.getIsXcx() != null && user.getIsXcx() > 0){
                entity.setOpUserId(user.getUserId());
            }else{
                entity.setCompanyId(user.getDeptId());
            }
            if(StringUtils.isNotBlank(user.getProvince())){
                entity.setProvince(user.getProvince());
            }
            if(StringUtils.isNotBlank(user.getCity())){
                entity.setCity(user.getCity());
            }
            if(StringUtils.isNotBlank(user.getCounty())){
                entity.setCounty(user.getCounty());
            }
            if(StringUtils.isNotBlank(user.getTown())){
                entity.setTown(user.getTown());
            }
        }
        return user;
    }

    @Override
    public UserBean setDataAuth(HttpServletRequest request, TManagerWaterpress entity) {
        UserBean user = getUserByToken(request);
        if(user != null){
            if(user.getIsXcx() != null && user.getIsXcx() > 0){
                entity.setOpUserId(user.getUserId());
            }else{
                entity.setCompanyId(user.getDeptId());
            }
            if(StringUtils.isNotBlank(user.getProvince())){
                entity.setProvince(user.getProvince());
            }
            if(StringUtils.isNotBlank(user.getCity())){
                entity.setCity(user.getCity());
            }
            if(StringUtils.isNotBlank(user.getCounty())){
                entity.setCounty(user.getCounty());
            }
            if(StringUtils.isNotBlank(user.getTown())){
                entity.setTown(user.getTown());
            }
        }
        return user;
    }

    @Override
    public UserBean setDataAuth(HttpServletRequest request, TBaseFirehydrant entity) {
        UserBean user = getUserByToken(request);
        if(user != null){
            if(user.getIsXcx() != null && user.getIsXcx() > 0){
                entity.setOpUserId(user.getUserId());
            }else{
                entity.setCompanyId(user.getDeptId());
            }
        }
        return user;
    }

    @Override
    public UserBean setDataAuth(HttpServletRequest request, TManagerCameras entity) {
        UserBean user = getUserByToken(request);
        if(user != null){
            if(user.getIsXcx() != null && user.getIsXcx() > 0){
                entity.setOpUserId(user.getUserId());
            }else{
                entity.setCompanyId(user.getDeptId());
            }
            if(StringUtils.isNotBlank(user.getProvince())){
                entity.setProvince(user.getProvince());
            }
            if(StringUtils.isNotBlank(user.getCity())){
                entity.setCity(user.getCity());
            }
            if(StringUtils.isNotBlank(user.getCounty())){
                entity.setCounty(user.getCounty());
            }
            if(StringUtils.isNotBlank(user.getTown())){
                entity.setTown(user.getTown());
            }
        }
        return user;
    }
    @Override
    public UserBean setDataAuth(HttpServletRequest request, DeviceMonitorVo entity) {
        UserBean user = getUserByToken(request);
        if(user != null){
            if(user.getIsXcx() != null ){
                entity.setCompanyId(user.getDeptId());
            }
            if(StringUtils.isNotBlank(user.getProvince())){
                entity.setProvince(user.getProvince());
            }
            if(StringUtils.isNotBlank(user.getCity())){
                entity.setCity(user.getCity());
            }
            if(StringUtils.isNotBlank(user.getCounty())){
                entity.setCounty(user.getCounty());
            }
            if(StringUtils.isNotBlank(user.getTown())){
                entity.setTown(user.getTown());
            }
        }
        return user;
    }

    @Override
    public UserBean setDataAuth(HttpServletRequest request, DeviceAccessVO entity) {
        UserBean user = getUserByToken(request);
        if(user != null){
            if(user.getIsXcx() != null && user.getIsXcx() > 0){
                entity.setOpUserId(user.getUserId());
            }else{
                entity.setOpCompanyId(user.getDeptId());
            }
        }
        return user;
    }

    public static void main(String [] args){
        UserServiceImpl impl = new UserServiceImpl();
        String token = "b25e39b47e774b4a05b3cb1555fc377f209457c3fd339d373d3fca7b1ea8be56fdc6ed05b7ffb0700e7300d242fb83b51ab15dc8e2922c964405e029b5b5dfb5c2f2c5bb1aee0651056a6adbce31e440144d42dda8569f82f28598e8f028ce340d0413690c10ba7c75d5d5bf552045b236fa4773b907a411f5c86f27ff8b54d113bd4ef1b8fa2caf";
        UserBean user = impl.getUserByToken(token);
        System.out.println(12);
    }

}
