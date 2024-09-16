package com.smart.device.install.feign;

import com.smart.common.core.domain.Result;
import com.smart.device.install.entity.vo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Component
@FeignClient(name = "smoke-fire-platform")
public interface SmokeFirePlatformFeignClient {

    @GetMapping("/api/v1/addXCXUser")
    public Map<String, Object> addXCXUser(@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("deptId") Long deptId);

    @GetMapping("/user/check/{username}")
    public Map checkUserName(@RequestParam("username") String username);

    @PostMapping("/api/v2/dept/deptForUpdate")
    public Result deptForUpdate(@RequestBody Dept dept);


}
