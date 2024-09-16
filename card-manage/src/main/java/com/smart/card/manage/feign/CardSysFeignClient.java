package com.smart.card.manage.feign;

import com.smart.common.core.domain.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Component
@FeignClient(name = "card-sys")
public interface CardSysFeignClient {

//    @PostMapping("/api/v2/dept/deptForUpdate")
//    public Result deptForUpdate(@RequestBody Dept dept);


}
