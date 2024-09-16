# 请求规范
```
查询所有          list	GET
获取单个资源      query/find/get/select	GET	
创建单个资源      create	POST	
更新单个资源      update	PUT
删除单个资源      delete	DELETE
```
## mybatisPlus 
mybatis-plus使用对象属性进行SQL操作，经常会出现对象属性非表字段的情况，忽略映射字段使用以下注解：
@TableField(exist = false)：表示该属性不为数据库表字段，但又是必须使用的。
@TableField(exist = true)：表示该属性为数据库表字段。

Mybatis-Plus 插件有这个功能，可以看一下
@TableName：数据库表相关
@TableId：表主键标识
@TableField：表字段标识
@TableLogic：表字段逻辑处理注解（逻辑删除）
### 用户管理 :SysUserController




