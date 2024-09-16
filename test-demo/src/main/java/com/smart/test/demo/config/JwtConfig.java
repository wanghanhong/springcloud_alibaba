package com.smart.test.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 只有包装成ConfigurationProperties，刷新才起作用
 *
 * @author 三多
 * @Time 2020/4/28
 */
@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtConfig {

    /**
     * token 密钥
     */
    //@Value("${jwt.secret}")
    public String secret;

    /**
     * token 失效时间，单位秒
     */
    //@Value("${jwt.expirationTimeInSecond}")
    public String expirationTimeInSecond;

}

