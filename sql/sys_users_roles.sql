/*
 Navicat Premium Data Transfer

 Source Server         : 127MySql
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 30/01/2023 16:11:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_users_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_roles`;
CREATE TABLE `sys_users_roles`  (
  `user_code` bigint NOT NULL COMMENT '用户ID',
  `role_code` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_code`, `role_code`) USING BTREE,
  INDEX `FKq4eq273l04bpu4efj0jd0jb98`(`role_code` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联' ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
