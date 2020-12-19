# 项目设计方案
## 1.项目层次结构
>   * obtain: --------主项目
>>  * auth:--------项目认证模块
>>> * com.obtain.study
>>>> * config 配置地址 
>>>> * AuthApplication 项目启动类
>>>> * controller 项目api访问成
>>>> * service 应用服务层
>>>> * entity 应用实体类
>>>> * mapper mybatis映射类
>> * book:--------项目书籍模块
>>> * com.obtain.book
>>>> * config 配置地址
>>>> * BookApplication 项目启动类
>>>> * controller 项目api访问成
>>>> * service 应用服务层
>>>> * entity 应用实体类
>>>> * mapper mybatis映射类
>> *  chat:--------项目聊天模块
>>> * com.obtain.chat
>>>> * config 配置地址
>>>> * ChatApplication 项目启动类
>>>> * controller 项目api访问成
>>>> * service 应用服务层
>>>> * entity 应用实体类
>>>> * mapper mybatis映射类
>> *  common:--------项目通用模块
>>> * com.obtain.common
>>>> * config 配置地址
>>>> * CommonApplication 项目启动类
>>>> * controller 项目api访问成
>>>> * service 应用服务层
>>>> * entity 应用实体类
>>>> * mapper mybatis映射类
>> *  monitor:--------项目监控模块
>>> * com.obtain.monitor
>>>> * config 配置地址
>>>> * MonitorApplication 项目启动类
>>>> * controller 项目api访问成
>>>> * service 应用服务层
>>>> * entity 应用实体类
>>>> * mapper mybatis映射类

##api定义
```json
{
  "status": 200,
  "msg": "success",
  "data": {}
}
```
##统一错误处理
```json
{
  "status": 505,
  "msg": "server exception"
}
```
##数据库定义
* 数据库统一开头obtain_