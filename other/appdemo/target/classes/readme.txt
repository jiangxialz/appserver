
appdemo运行步骤：
1、创建数据库test，导入test.sql创建表结构
2、配置jdbc.properties文件参数，更改为自身数据库参数
3、配置options.properties短信服务参数
4、Ctrl+Shift+R查找PlatSendSmsServiceTest.java单元测试文件，更改手机号码mobile参数，执行testSendSms方法

## 建议断点GlobalExternalReqAction.java文件，熟悉执行过程