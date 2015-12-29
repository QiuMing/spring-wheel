/*
Navicat MySQL Data Transfer

Source Server         : 
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : wh_blog

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2015-12-29 22:54:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for wh_permission
-- ----------------------------
DROP TABLE IF EXISTS `wh_permission`;
CREATE TABLE `wh_permission` (
  `permission_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `permission` char(30) NOT NULL,
  `name` char(30) NOT NULL,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='permission table';

-- ----------------------------
-- Records of wh_permission
-- ----------------------------

-- ----------------------------
-- Table structure for wh_role
-- ----------------------------
DROP TABLE IF EXISTS `wh_role`;
CREATE TABLE `wh_role` (
  `role_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role` char(25) NOT NULL,
  `parent_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='role table';

-- ----------------------------
-- Records of wh_role
-- ----------------------------

-- ----------------------------
-- Table structure for wh_role_permission_link
-- ----------------------------
DROP TABLE IF EXISTS `wh_role_permission_link`;
CREATE TABLE `wh_role_permission_link` (
  `role_id` int(11) unsigned NOT NULL,
  `permission_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `permission_id` (`permission_id`),
  CONSTRAINT `wh_role_permission_link_ibfk_1` FOREIGN KEY (`permission_id`) REFERENCES `wh_permission` (`permission_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `wh_role_permission_link_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `wh_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='user role link table';

-- ----------------------------
-- Records of wh_role_permission_link
-- ----------------------------

-- ----------------------------
-- Table structure for wh_user
-- ----------------------------
DROP TABLE IF EXISTS `wh_user`;
CREATE TABLE `wh_user` (
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `account` char(50) NOT NULL,
  `nick_name` char(25) NOT NULL,
  `password` char(100) NOT NULL,
  `regist_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `salt` char(50) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `unique_name` (`nick_name`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='user table';

-- ----------------------------
-- Records of wh_user
-- ----------------------------
INSERT INTO `wh_user` VALUES ('9', '1', '631663525@qq.com', 'Ming', 'bb665efc4d1c814c7364e64c5e92def2755e833351b9d53ee395fe863a5322c1', '2015-12-13 23:45:05', '4cd59f494098478a8d1a76a80a33cae4');
INSERT INTO `wh_user` VALUES ('10', '1', 'test@163.com', 'test', '5b7e5ba557f3f644550af5236e963092a22c775350099f30e89eacf256c49f9d', '2015-12-29 22:32:56', '7f8a970dd6c048dcba739a8d53c66efa');

-- ----------------------------
-- Table structure for wh_user_role_link
-- ----------------------------
DROP TABLE IF EXISTS `wh_user_role_link`;
CREATE TABLE `wh_user_role_link` (
  `user_id` int(11) unsigned NOT NULL,
  `role_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `wh_user_role_link_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `wh_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `wh_user_role_link_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `wh_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='user role link table';

-- ----------------------------
-- Records of wh_user_role_link
-- ----------------------------
