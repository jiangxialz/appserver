# appserver
app开发服务端web工程项目

## appserver运行步骤：
* `1`、创建数据库test，导入test.sql创建表结构
* `2`、配置conf/jdbc.properties文件参数，更改为自身数据库参数
* `3`、配置conf/options.properties短信服务参数(在此声明此短信服务与我无关，不是打广告！)
* `4`、Ctrl+Shift+R查找PlatSendSmsServiceTest.java单元测试文件，更改手机号码mobile参数，执行testSendSms方法

### ```建议断点GlobalExternalReqAction.java文件，熟悉执行过程```

## appserver打包部署：
* `1`、修改assembly.xml中的formats参数，自定义打包文件格式，一般为zip或者war，关于assembly，请参考：http://maven.apache.org/plugins/maven-assembly-plugin/assembly.html
* `2`、选择项目 --> Run As --> Run Configurations --> Goals 设置为-Ptest clean assembly:assembly

### ```默认打包为测试环境配置文件（见pom中的<activeByDefault>节点），当需要打包为正式环境时配置为-Pprod clean assembly:assembly```

## 技术支持
* `QQ`: 554582346
* `Email`: 554582346@qq.com

## 结束语
* 开源分享，共同学习，共同进步。欢迎交流并指正不足之处！谢谢！！