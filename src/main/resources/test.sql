/*
Navicat MySQL Data Transfer

Source Server Version : 50173
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-08-27 11:54:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for app_visit_log
-- ----------------------------
DROP TABLE IF EXISTS `app_visit_log`;
CREATE TABLE `app_visit_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '访问用户id',
  `ip` varchar(15) DEFAULT NULL COMMENT '访问者IP',
  `interface_name` varchar(50) NOT NULL COMMENT '接口名称',
  `visit_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '访问时间',
  `elapse` int(11) NOT NULL COMMENT '耗时',
  `result` int(1) NOT NULL COMMENT '调用接口结果(1:成功；2:失败)',
  `err_msg` varchar(200) DEFAULT NULL COMMENT '出错原因',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14889 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_visit_log
-- ----------------------------

-- ----------------------------
-- Table structure for plat_sms_code
-- ----------------------------
DROP TABLE IF EXISTS `plat_sms_code`;
CREATE TABLE `plat_sms_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `mobile` varchar(20) NOT NULL COMMENT '手机号码',
  `sms_code` varchar(10) NOT NULL COMMENT '短信验证码',
  `create_time` datetime NOT NULL COMMENT '生成时间',
  `expire_time` datetime NOT NULL COMMENT '过期时间',
  `is_use` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否使用(1:是：0否)',
  `use_time` datetime DEFAULT NULL COMMENT '使用时间',
  `use_key` varchar(50) NOT NULL COMMENT '用途',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COMMENT='短信验证码记录表';

-- ----------------------------
-- Records of plat_sms_code
-- ----------------------------
