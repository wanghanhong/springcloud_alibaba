package wlw.smart.fire.system.controller;

import cn.hutool.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wlw.smart.fire.common.domain.FebsResponse;
import wlw.smart.fire.common.domain.RedisInfo;
import wlw.smart.fire.common.service.RedisService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Pano
 */
@RestController
@RequestMapping("redis")
public class RedisController {

    @Resource
    private RedisService redisService;

    @GetMapping("info")
    public FebsResponse getRedisInfo() throws Exception {
        List<RedisInfo> infoList = this.redisService.getRedisInfo();
        return new FebsResponse().code(HttpStatus.HTTP_OK).data(infoList);
    }

    @GetMapping("keysSize")
    public Map<String, Object> getKeysSize() throws Exception {
        return redisService.getKeysSize();
    }

    @GetMapping("memoryInfo")
    public Map<String, Object> getMemoryInfo() throws Exception {
        return redisService.getMemoryInfo();
    }
}
