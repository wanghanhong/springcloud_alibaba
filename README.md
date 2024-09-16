# Springcloud Alibaba 简介
## 概述

<b>Spring Cloud Alibaba</b><br/> 致力于提供分布式应用服务开发的一站式解决方案。项目包含开发分布式应用服务的必需组件，方便开发者通过 Spring Cloud 编程模型轻松使用这些组件来开发分布式应用服务。
此项目包含的组件内容，主要选取自阿里巴巴开源中间件和阿里云商业化产品，但也不限定于这些产品。
如果您对 Roadmap 有任何建议或想法，欢迎在 issues 中或者通过其他社区渠道向我们提出，一起讨论。

<b>已包含的组件:</b><br/>

<b>Sentinel</b><br/>
阿里巴巴开源产品，把流量作为切入点，从流量控制、熔断降级、系统负载保护等多个维度保护服务的稳定性。

<b>Nacos</b><br/>
阿里巴巴开源产品，一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。

<b>RocketMQ</b><br/>
Apache RocketMQ™ 基于 Java 的高性能、高吞吐量的分布式消息和流计算平台。

<b>Dubbo</b><br/>
Apache Dubbo™ 是一款高性能 Java RPC 框架。

<b>Seata</b><br/>
阿里巴巴开源产品，一个易于使用的高性能微服务分布式事务解决方案。

<b>Alibaba Cloud OSS</b><br/>
阿里云对象存储服务（Object Storage Service，简称 OSS），是阿里云提供的海量、安全、低成本、高可靠的云存储服务。您可以在任何应用、任何时间、任何地点存储和访问任意类型的数据。

<b>Alibaba Cloud SchedulerX</b><br/>
阿里中间件团队开发的一款分布式任务调度产品，支持周期性的任务与固定时间点触发任务。

<b>Alibaba Cloud SMS</b><br/>
覆盖全球的短信服务，友好、高效、智能的互联化通讯能力，帮助企业迅速搭建客户触达通道。

## 下载，安装nacos
### 下载
[下载地址-注意需要vpn](https://github.com/alibaba/nacos/releases)
### 安装
```shell
  #安装：
   $ tar -zxvf nacos-server-$version.tar.gz
   $ cd nacos/bin  
  
```

### 配置 MySQL 数据库
[sql脚本](https://github.com/alibaba/nacos/blob/1.2.1/distribution/conf/nacos-mysql.sql)
### 启动集群
```shell
  $ sh ./nacos-8848/bin/startup.sh -m cluster
  $ sh ./nacos-8849/bin/startup.sh -m cluster
  $ sh ./nacos-8850/bin/startup.sh -m cluster
```
### 防火墙
查看防火墙状态
systemctl status firewalld; 
```shell
  $ firewall-cmd --permanent --add-port=8848/udp --add-port=8848/tcp
  $ firewall-cmd --reload
  $ firewall-cmd --list-ports
```

### 访问
http://172.22.1.21:8848/nacos
http://172.22.1.21:8849/nacos
http://172.22.1.21:8850/nacos
用户名/密码：nacos/nacos
## Seata 安装

### 下载地址
1. [seata-server官网地址](ttps://github.com/seata/seata/releases)
2. 下载编译：seata-server-1.2.0.tar.gz
3. 下载源码：Source code(zip)
### 配置
1. 配置nacos配置（前置条件）
* 将nacos/conf/nacos-mysql.sql导入自己的数据库
* 配置修改nacos/conf/application.properties
```properties
spring.datasource.platform=mysql
db.num=1
db.url.0=jdbc:mysql://127.0.0.1:3306/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
db.user=root
db.password=123456
```
* 启动nacos
2. 下载seata
* 使用db模式导入配置:路径： seata-1.2.0\script\server\db\mysql.sql
* 配置文件：路径：seata-1.2.0\script\config-center\config.txt
* 脚本：路径：seata-1.2.0\script\config-center\nacos\nacos-config.sh
 将上面的config.txt文件复制到seata目录，nacos中的nacos-config.sh  nacos-config.py复制到seata的conf目录

3. 初始化seata
sh nacos-config.sh -h 172.22.1.21 -p 8848 -g SEATA_GROUP

4. 启动 seata
nohup ./bin/seata-server.sh >log.out 2>1 &
nohup sh seata-server.sh -p 8091 -h 127.0.0.1 >log.out 2>1 &
###启动
### 参考
* [springcloud seata nacos环境搭建](https://www.cnblogs.com/javashare/p/12535702.html)
## 工程目录
架构中application与service的区别是什么？
* service提供了基础服务功能；application组装基础服务功能，提供给用户直接可用的业务。
* service服务粒度小、功能基础，不易发生改变；application提供上游业务功能，紧贴业务需求，容易发生改变。
* 形成service支撑application的整体架构，增加多变的application甚至不需要变动service。

```markdown
springcloud-alibaba         整体父工程
├─api-common                公共包
│  ├─api-common-core	    核心工具包
│  ├─api-common-auth	    权限工具包
│  ├─api-common-log	        日志工具包
│  └─api-common-redis       redis工具包

├─xxl-job                   API网关，端口：9600

├─api-gateway               API网关，端口：9200

├─api-system                系统管理
│  ├─api-system-common	    系统管理公共业务
│  ├─api-system-auth	    授权中心      端口：9202  
│  └─api-system-manage      系统管理      端口：9201

├─api-transaction           分布式事务，端口：9300

├─smoke-fire-platform		智慧消防大数据平台，端口：9002

├─smart-fire-platform		智慧消防大数据平台，端口：9901

├─device-manage		        设备管理，端口：(9001-9005)(跳过9002)
│  ├─device-common-module	设备公共module
│  ├─device-access	        设备接入(基础信息接入，设备和CtWing平台对接)，端口：9001
│  ├─device-install	        设备安装(设备安装巡检)，端口：9003
│  └─device-message         设备消息管理，端口：9004	

├─message-manage            消息管理，端口：9006
├─publicize-manage         宣传教育，端口：9007

├─demo		                demo ,端口 10000

├─brd-sys            养殖权限用户，端口：11001
├─brd-manage-base    养殖管理系统，端口：11003
├─card-sys            卡管理权限用户，端口：12001
├─card-manage         卡管理，端口：12003

```

## 组件
1. 注册中心  172.22.1.21：8500  nacos   zlwl@nacos
2. 分布式任务中心 172.22.1.21:9600 admin/admin@wlw
## 实战
[Spring Cloud Alibaba基础教程：使用Nacos实现服务注册与发现](http://blog.didispace.com/spring-cloud-alibaba-1/)
## 参考
1. [github地址](https://github.com/alibaba/spring-cloud-alibaba/blob/master/README-zh.md)
2. [阿里巴巴解决方案之Spring Cloud Alibaba](https://blog.csdn.net/huangjinjin520/article/details/100190670)
3. [Spring Cloud Alibaba实战(一) - 概述](https://developer.aliyun.com/article/718349)
4. [nacos官网]( https://nacos.io/zh-cn/index.html )
5. [集群参考1-Nacos集群部署说明](https://blog.csdn.net/Aria_Miazzy/article/details/98886937)
6. [集群参考2-集群模式部署Nacos](https://www.jianshu.com/p/e878a80a9c30)
7. [Nacos系列：Nacos的三种部署模式-终极版](https://www.jianshu.com/p/4f817a0aaa14)
8. [Spring Cloud Alibaba到底坑不坑？](https://www.cnblogs.com/didispace/p/10675601.html)
9. [springcloud alibaba 版本说明](https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E)
10. [搭建生产可用的Nacos集群-nginx](http://www.imooc.com/article/288153)
11. [RocketMQ Example](https://github.com/alibaba/spring-cloud-alibaba/blob/master/spring-cloud-alibaba-examples/rocketmq-example/readme-zh.md)
12. [ruoyi-cloud-nacos-参考](https://gitee.com/zhangmrit/ruoyi-cloud/tree/nacos/)
13. [spring-cloud-alibaba版本说明](https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E)
14. [Seata 的AT、Saga和TCC模式 ](https://www.sohu.com/a/345515118_673711)
15. [springcloud seata nacos环境搭建](https://www.cnblogs.com/javashare/p/12535702.html)
16. [SPRINGCLOUD 集成 NACOS 1.2.1 和 SEATA 1.1.0](https://www.freesion.com/article/2996488199/)
17. [MybatisPlus 组件集成-官方文档](https://github.com/baomidou/dynamic-datasource-spring-boot-starter/wiki/Integration-With-Seata)
18. [JasperReport生成PDF中文不显示处理](https://bank.jasperblog.csdn.net/clj198606061111/article/details/78536396)
