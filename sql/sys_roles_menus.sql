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

 Date: 07/11/2022 15:41:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_roles_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_menus`;
CREATE TABLE `sys_roles_menus`  (
  `menu_code` bigint NOT NULL COMMENT '菜单ID',
  `role_code` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`menu_code`, `role_code`) USING BTREE,
  INDEX `FKcngg2qadojhi3a651a5adkvbq`(`role_code` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单关联' ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
