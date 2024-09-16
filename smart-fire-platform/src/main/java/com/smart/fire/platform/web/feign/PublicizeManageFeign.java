package com.smart.fire.platform.web.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Map;

@Component
@FeignClient(name = "publicize-manage")
public interface PublicizeManageFeign {

    @GetMapping("/api/v2/notice/queryNoticeListLimit")
    public List<Map<String,Object>> queryNoticeListLimit();

}
