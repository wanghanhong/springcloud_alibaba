package com.smart.device.install.common.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class FeignTokenInterceptor implements RequestInterceptor {

    @Override
    public void apply(feign.RequestTemplate template) {
        HttpServletRequest request = getServletRequest();
        if (null == request){
            return;
        }
        template.header("Authorization", getHeaders(request));
    }
    private HttpServletRequest getServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    private String getHeaders(HttpServletRequest request){
        return request.getHeader("Authorization");
    }



}