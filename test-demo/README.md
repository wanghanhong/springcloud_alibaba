#集成组件教程
## 集成分布式事务seata
1. 引入依赖

```xml
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-seata</artifactId>
       <!-- <exclusions>
            <exclusion>
                <artifactId>seata-all</artifactId>
                <groupId>io.seata</groupId>
            </exclusion>
        </exclusions>-->
    </dependency>
    <dependency>
        <groupId>io.seata</groupId>
        <artifactId>seata-all</artifactId>
        <version>1.2.0</version>
    </dependency>
```
2.配置bootstrap.yml
```yaml
info:
  nacos:
    address: 172.22.1.21:8500
server:
  port: 10000
spring:
  application:
    name: test-demo
  cloud:
    nacos:
      discovery:
        server-addr: ${info.nacos.address}
      config:
        server-addr: ${info.nacos.address}
        file-extension: yaml
        prefix: ${spring.application.name}
    inetutils:
      preferred-networks: 172.22
    alibaba:
      seata:
        tx-service-group: my_test_tx_group
  profiles:
    active: dev
# seata 的配置
seata:
  enabled: true
  application-id: test-demo
  tx-service-group: my_test_tx_group
  config:
    type: nacos
    nacos:
      namespace:
      serverAddr: ${info.nacos.address}
  registry:
    type: nacos
    nacos:
      server-addr: ${info.nacos.address}
      namespace:
```
3. 使用
需要全局事务的业务上添加一个注解 @GlobalTransactional。
## 集成mybatisPlus
1. 引入依赖
```xml
    <!--mybatisPlus-->
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-generator</artifactId>
    </dependency>
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-extension</artifactId>
    </dependency>
    <!--Sql打印：生产环境建议注销有性能损耗-->
    <dependency>
        <groupId>p6spy</groupId>
        <artifactId>p6spy</artifactId>
        <version>3.9.0</version>
    </dependency>
```
2. 配置bootstrap.yml

```yaml
spring:
  servlet:
    #上传大小限制
    multipart:
      enabled: true
      max-file-size: 30MB
      max-request-size: 30MB
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss
    time-zone: GMT+8
    default-property-inclusion: non_null
  # 配置数据源
  datasource:
    hikari:
      auto-commit: true
      connection-test-query: SELECT 1
      connection-timeout: 30000
      idle-timeout: 30000
      max-lifetime: 1800000
      maximum-pool-size: 15
      minimum-idle: 5
      pool-name: DatebookHikariCP
    driver-class-name: com.p6spy.engine.spy.P6SpyDriver
    url: jdbc:p6spy:mysql://172.22.1.21:3306/test_demo?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull
    username: root
    password: Developer@2019
    type: com.zaxxer.hikari.HikariDataSource
# mybatis-plus相关配置
mybatis-plus:
  mapper
  mapper-locations: classpath:mapper/*.xml
  # 以下配置均有默认值,可以不设置
  global-config:
    db-config:
      #主键类型 AUTO:"数据库ID自增" INPUT:"用户输入ID",ID_WORKER:"全局唯一ID (数字类型唯一ID)", UUID:"全局唯一ID UUID";
      id-type: ID_WORKER
      #字段策略 IGNORED:"忽略判断"  NOT_NULL:"非 NULL 判断")  NOT_EMPTY:"非空判断"
      field-strategy: NOT_EMPTY
      #数据库类型
      db-type: MYSQL
    banner: off
  configuration:
    # 是否开启自动驼峰命名规则映射:从数据库列名到Java属性驼峰命名的类似映射
    map-underscore-to-camel-case: true
    # 如果查询结果中包含空值的列，则 MyBatis 在映射的时候，不会映射这个字段
    call-setters-on-nulls: true
    # 这个配置会将执行的sql打印出来，在开发或测试的时候可以用
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  mapper
  type-aliases-package: com.smart.**.entity
  #启动时是否检查 MyBatis XML 文件的存在，默认不检查
  check-config-location: false
```
3. 配置spy.properties
```properties
#3.2.1以上使用
modulelist=com.baomidou.mybatisplus.extension.p6spy.MybatisPlusLogFactory,com.p6spy.engine.outage.P6OutageFactory
#3.2.1以下使用或者不配置
#modulelist=com.p6spy.engine.logging.P6LogFactory,com.p6spy.engine.outage.P6OutageFactory
# 自定义日志打印
logMessageFormat=com.baomidou.mybatisplus.extension.p6spy.P6SpyLogger
#日志输出到控制台
appender=com.baomidou.mybatisplus.extension.p6spy.StdoutLogger
# 使用日志系统记录 sql
#appender=com.p6spy.engine.spy.appender.Slf4JLogger
# 设置 p6spy driver 代理
deregisterdrivers=true
# 取消JDBC URL前缀
useprefix=true
# 配置记录 Log 例外,可去掉的结果集有error,info,batch,debug,statement,commit,rollback,result,resultset.
excludecategories=info,debug,result,commit,resultset
# 日期格式
dateformat=yyyy-MM-dd HH:mm:ss
# 实际驱动可多个
#driverlist=org.h2.Driver
# 是否开启慢SQL记录
outagedetection=true
# 慢SQL记录标准 2 秒
outagedetectioninterval=2
```
4.开启分页查询
```java
package com.smart.test.demo.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 三多
 * @Time 2020/5/26
 */
@Configuration
@MapperScan("com.smart.**.dao")
public class MybatisPlusConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}

```