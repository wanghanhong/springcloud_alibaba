package com.smart.test.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Api("测试")
public class SwaggerController {
    @ApiOperation(value = "hello ~", notes = "欢迎")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "名字", required = true)})
    @GetMapping("/hell")
    public String get(String name) {
        return "hello " + name;
    }
}
