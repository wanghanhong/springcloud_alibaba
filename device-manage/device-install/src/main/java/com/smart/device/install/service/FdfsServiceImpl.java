package com.smart.device.install.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("fdfsService")
public class FdfsServiceImpl {

    @Value("${fdfsUrl}")
    public String fdfsUrl;

    public String getResAccessUrl(String path) {
        String url = fdfsUrl + path;
        return url;
    }

}
