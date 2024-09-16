package com.smart.brd.manage.base.common.aspect;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.smart.brd.manage.base.common.exception.CustomException;
import com.smart.brd.manage.base.common.usertoken.constant.RedisConst;
import com.smart.brd.manage.base.entity.*;
import com.smart.brd.manage.base.entity.vo.*;
import com.smart.common.utils.EncryptUtil;
import com.smart.common.utils.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * userId切面类
 * 对于所有的Controller传入的参数通过解析用户token获取userId-deptids，赋值到Vo中
 * 前端免去了传入userId-deptids的麻烦
 *
 */
@Aspect
@Component
public class UserIdAdvice {

    /**
     * 切入点，定义所有的控制器
     */
    @Pointcut("execution(public * com.smart.brd.manage.base.controller.*.*(..))||execution(public * com.smart.brd.manage.base.common.test.*.*(..))||execution(public * com.smart.brd.manage.base.screen.controller.*.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        //获取上下文request，获取token并解析
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        String s = "Token异常！";
            if(requestAttributes != null){
                request = requestAttributes.getRequest();
            }
            String token = null;
            if(request != null){
                token = request.getHeader("Authorization");
            }
            if(token == null){
                throw new CustomException(s);
            }
            String tokenStr = "";
        try {
            // 把加过密的token 解密
            EncryptUtil encryptUtil = new EncryptUtil(RedisConst.TOKEN_CACHE_PREFIX);
            tokenStr = encryptUtil.decrypt(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(StringUtils.isNotBlank(tokenStr)){
                // 从token 中获取附加的各种参数
                DecodedJWT jwt = JWT.decode(tokenStr);
                Claim userId = jwt.getClaim("userId");
                if(userId == null || userId.asLong() == null){
                    throw new SecurityException(s);
                }
                Long deptId = jwt.getClaim("deptId").asLong();
                Long parentId = jwt.getClaim("parentId").asLong();
                String deptIds = jwt.getClaim("deptIds").asString();
                //获取参数并赋值
                Object[] args = joinPoint.getArgs();
                for (Object arg : args){
                    getAndSetEntity(deptId,parentId,deptIds,arg);
                }
            }else{
                throw new CustomException(s);
            }

    }

    private void getAndSetEntity(Long deptId,Long parentId,String deptIds, Object arg) {
        //对于不同的Vo类进行相应的赋值
        if (arg instanceof TAlarmInfo) {
            TAlarmInfo vo = (TAlarmInfo) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof TAlarmRules) {
            TAlarmRules vo = (TAlarmRules) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof TBrdBreeder) {
            TBrdBreeder vo = (TBrdBreeder) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof BrdFieldVo) {
            BrdFieldVo vo = (BrdFieldVo) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof TDCamerasConfig) {
            TDCamerasConfig vo = (TDCamerasConfig) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof TDevice) {
            TDevice vo = (TDevice) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof TDeviceCameras) {
            TDeviceCameras vo = (TDeviceCameras) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof TDeviceInstall) {
            TDeviceInstall vo = (TDeviceInstall) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof DiseaseVo) {
            DiseaseVo vo = (DiseaseVo) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof TDisease) {
            TDisease vo = (TDisease) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof TDiseaseRecord) {
            TDiseaseRecord vo = (TDiseaseRecord) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof TDiseaseTreat) {
            TDiseaseTreat vo = (TDiseaseTreat) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof TDrug) {
            TDrug vo = (TDrug) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof TFeedAdditive) {
            TFeedAdditive vo = (TFeedAdditive) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof TFeed) {
            TFeed vo = (TFeed) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof BarnsVo) {
            BarnsVo vo = (BarnsVo) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof LiveStockVo) {
            LiveStockVo vo = (LiveStockVo) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof DeadVo) {
            DeadVo vo = (DeadVo) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof EscapeVo) {
            EscapeVo vo = (EscapeVo) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof TLivestockLog) {
            TLivestockLog vo = (TLivestockLog) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof TLivestockPurchase) {
            TLivestockPurchase vo = (TLivestockPurchase) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof TLivestockShed) {
            TLivestockShed vo = (TLivestockShed) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof ShedOutAllVo) {
            ShedOutAllVo vo = (ShedOutAllVo) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof ShedTransVo) {
            ShedTransVo vo = (ShedTransVo) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof TVaccine) {
            TVaccine vo = (TVaccine) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof TVaccinePrevention) {
            TVaccinePrevention vo = (TVaccinePrevention) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof TVaccineRecord) {
            TVaccineRecord vo = (TVaccineRecord) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof TBrdField) {
            TBrdField vo = (TBrdField) arg;
            vo.setFieldId(deptId);
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof TLivestockAnalysis) {
            TLivestockAnalysis vo = (TLivestockAnalysis) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof ShedOutVo) {
            ShedOutVo vo = (ShedOutVo) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof TLivestockBed) {
            TLivestockBed vo = (TLivestockBed) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof TLivestock) {
            TLivestock vo = (TLivestock) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }else if (arg instanceof TLivestockBedVo) {
            TLivestockBedVo vo = (TLivestockBedVo) arg;
            vo.setDeptId(deptId);
            vo.setDeptIds(deptIds);
        }
    }

}
