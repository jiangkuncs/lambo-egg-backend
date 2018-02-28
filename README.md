# lambo-egg
轻量级的javaweb后端开发框架

### 技术选型

技术 | 名称 | 官网
----|------|----
Spring Framework | 容器  | [http://projects.spring.io/spring-framework/](http://projects.spring.io/spring-framework/)
SpringMVC | MVC框架  | [http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc](http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc)
Apache Shiro | 安全框架  | [http://shiro.apache.org/](http://shiro.apache.org/)
MyBatis | ORM框架  | [http://www.mybatis.org/mybatis-3/zh/index.html](http://www.mybatis.org/mybatis-3/zh/index.html)
PageHelper | MyBatis物理分页插件  | [http://git.oschina.net/free/Mybatis_PageHelper](http://git.oschina.net/free/Mybatis_PageHelper)
Druid | 数据库连接池  | [https://github.com/alibaba/druid](https://github.com/alibaba/druid)
FluentValidator | 校验框架  | [https://github.com/neoremind/fluent-validator](https://github.com/neoremind/fluent-validator)
Quartz | 作业调度框架  | [http://www.quartz-scheduler.org/](http://www.quartz-scheduler.org/)
Ehcache | 进程内缓存框架  | [http://www.ehcache.org/](http://www.ehcache.org/)
Log4J | 日志组件  | [http://logging.apache.org/log4j/1.2/](http://logging.apache.org/log4j/1.2/)
Swagger2 | 接口测试框架  | [http://swagger.io/](http://swagger.io/)
Maven | 项目构建管理  | [http://maven.apache.org/](http://maven.apache.org/)

### 模块介绍

> lambo-common

Spring+SpringMVC+Mybatis框架集成公共模块，包括公共配置、通用BaseService、工具类等。

> lambo-demo

示例应用

> lambo-upms

本系统是基于RBAC授权和基于用户授权的细粒度权限控制通用平台，并提供单点登录、会话管理和日志管理。接入的系统可自由定义组织、角色、权限、资源等。用户权限=所拥有角色权限合集+用户加权限-用户减权限，优先级：用户减权限>用户加权限>角色权限

### 开发指南
- 1、本机安装Jdk7+、Mysql并**启动相关服务**，使用默认配置默认端口即可
- 2、克隆源代码到本地并打开，**推荐使用IntelliJ IDEA**，本地编译并安装到本地maven仓库

### 编译流程
- 打开IDEA,import project选择lambo根目录下的pom.xml(注意一定要选择到pom.xml)
- 打开Maven-Project窗口(view-Tool Windows-Maven Projects),双击运行lambo-Lifecycle-install选项

### 启动
- 新建lambo数据库，导入project-datamodel文件夹下的lambo.sql
- 修改lambo-demo模块的jdbc.properties数据库连接等配置信息，其中master.jdbc.password、slave.jdbc.password密码值使用了AES加密，请使用lambo-commmon项目下的com.lambo.common.util.AESUtil工具类修改这些值
- 在Maven Project窗口里,打开lambo-demo-sample下的Plugins,在jetty - jetty:run上右键debug lambo-upms-server
- 访问 [http://127.0.0.1:1114/demo/swagger-ui.html]("后台Swagger接口文档地址")
- 在sso-controller中使用/sso/login登录系统(admin 123456),然后就可以测试各个接口了

### 文档

- [DAO层代码生成器](https://github.com/btmagm/lambo-egg-backend/wiki/DAO%E5%B1%82%E4%BB%A3%E7%A0%81%E7%94%9F%E6%88%90)