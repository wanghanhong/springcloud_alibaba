package wlw.smart.fire.common.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import wlw.smart.fire.common.authentication.JWTUtil;
import wlw.smart.fire.common.domain.FebsConstant;
import wlw.smart.fire.common.utils.SnowflakeIdWorker;
import wlw.smart.fire.system.domain.po.User;
import wlw.smart.fire.system.manager.UserManager;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Pano
 */
@Component
public class BaseController {

    @Resource
    private UserManager userManager;
    @Resource
    private SnowflakeIdWorker idWorker;

    /**请求对象*/
    protected HttpServletRequest request;
    /**响应对象*/
    protected HttpServletResponse response;
    /**公司Id*/
    protected long tenantId;
    /**公司名称*/
    protected String tenantName;

    protected Map<String, Object> getDataTable(IPage<?> pageInfo) {
        Map<String, Object> rspData = new HashMap<>(16);
        rspData.put("rows", pageInfo.getRecords());
        rspData.put("total", pageInfo.getTotal());
        return rspData;
    }

    /**
     * 1 超级管理员，生成
     *      不能获取租户ID
     * 2 租户：获取当前租户的id
     *      可以获取租户ID
     * @param request
     * @param response
     */
    @ModelAttribute
    public void setResAndReq(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
        //TODO正式环境获取
        //getTenantId();
    }

    public void getTenantId(){
        //从当前登录用户中获取租户当前ID保存
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        String username = JWTUtil.getUsername(token);
        // 通过用户名查询用户信息
        User user = userManager.getUser(username);
        /**
         * 用户类型： 1 SaaS平台用户 2 租户平台用户
         * 1、SaaS平台用户
         *      创建用户(默认不传)
         *          userType = 1
         *      创建租户(传)
         *          userType = 2
         * 2、租户平台用户
         *      获取当前用户的租户ID
         */
        if(user.getUserType()== FebsConstant.SAAS_USER){
            //创建租户ID
            tenantId = idWorker.nextId();
        }
        if(user.getUserType()== FebsConstant.USUAL_USER){
            //获取当前用户的租户ID
            tenantId= user.getTenantId();
        }

    }
    

}
