/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80300 (8.3.0)
 Source Host           : localhost:3306
 Source Schema         : gemini

 Target Server Type    : MySQL
 Target Server Version : 80300 (8.3.0)
 File Encoding         : 65001

 Date: 04/03/2024 17:44:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `info_id` bigint NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `username` varchar(50) NOT NULL DEFAULT '' COMMENT '用户账号',
  `ip_address` varchar(128) NOT NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) NOT NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) NOT NULL DEFAULT '' COMMENT '浏览器类型',
  `operation_system` varchar(50) NOT NULL DEFAULT '' COMMENT '操作系统',
  `status` smallint NOT NULL DEFAULT '0' COMMENT '登录状态（1成功 0失败）',
  `msg` varchar(255) NOT NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统访问日志';

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `parent_id` bigint NOT NULL DEFAULT '0' COMMENT '父菜单ID',
  `menu_name` varchar(64) NOT NULL COMMENT '菜单名称',
  `menu_type` smallint NOT NULL DEFAULT '0' COMMENT '菜单的类型(1为普通菜单2为目录3为内嵌iFrame4为外链跳转5为按钮)',
  `router` varchar(255) NOT NULL DEFAULT '' COMMENT '路由名称',
  `path` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `is_hidden` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否隐藏',
  `order_num` int DEFAULT NULL COMMENT '排序',
  `permission` varchar(128) DEFAULT NULL COMMENT '权限标识',
  `status` smallint NOT NULL DEFAULT '0' COMMENT '菜单状态（1启用 0停用）',
  `remark` varchar(256) DEFAULT '' COMMENT '备注',
  `creator_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新者ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `menu_name`, `menu_type`, `router`, `path`, `icon`, `is_hidden`, `order_num`, `permission`, `status`, `remark`, `creator_id`, `create_time`, `updater_id`, `update_time`, `deleted`) VALUES (10, 0, '工作台', 1, '/dashboard', 'dashboard', NULL, 0, NULL, 'dashboard', 1, '控制台', NULL, '2024-02-20 20:39:50', NULL, NULL, 0);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `menu_name`, `menu_type`, `router`, `path`, `icon`, `is_hidden`, `order_num`, `permission`, `status`, `remark`, `creator_id`, `create_time`, `updater_id`, `update_time`, `deleted`) VALUES (11, 0, '菜单管理', 2, '/system', 'system', NULL, 0, NULL, 'system', 1, '系统管理', NULL, '2024-02-20 20:40:52', NULL, NULL, 0);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `menu_name`, `menu_type`, `router`, `path`, `icon`, `is_hidden`, `order_num`, `permission`, `status`, `remark`, `creator_id`, `create_time`, `updater_id`, `update_time`, `deleted`) VALUES (12, 11, '用户管理', 1, '/user', 'system/user', NULL, 0, NULL, 'system:user', 1, '用户管理', NULL, '2024-02-20 20:42:37', NULL, NULL, 0);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `menu_name`, `menu_type`, `router`, `path`, `icon`, `is_hidden`, `order_num`, `permission`, `status`, `remark`, `creator_id`, `create_time`, `updater_id`, `update_time`, `deleted`) VALUES (13, 11, '角色管理', 1, '/role', 'system/role', NULL, 0, NULL, 'system:role', 1, '角色管理', NULL, '2024-02-20 20:44:01', NULL, '2024-02-23 14:56:46', 0);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `menu_name`, `menu_type`, `router`, `path`, `icon`, `is_hidden`, `order_num`, `permission`, `status`, `remark`, `creator_id`, `create_time`, `updater_id`, `update_time`, `deleted`) VALUES (14, 11, '菜单管理', 1, '/menu', 'system/menu', NULL, 0, NULL, 'system:menu', 1, '菜单管理\n', NULL, '2024-02-20 20:50:57', NULL, '2024-02-23 14:56:53', 0);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `menu_name`, `menu_type`, `router`, `path`, `icon`, `is_hidden`, `order_num`, `permission`, `status`, `remark`, `creator_id`, `create_time`, `updater_id`, `update_time`, `deleted`) VALUES (15, 11, '组织管理', 1, '/org', 'system/org', NULL, 0, NULL, 'system:org', 1, '组织管理', NULL, '2024-02-20 21:03:00', NULL, '2024-02-23 14:56:58', 0);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `menu_name`, `menu_type`, `router`, `path`, `icon`, `is_hidden`, `order_num`, `permission`, `status`, `remark`, `creator_id`, `create_time`, `updater_id`, `update_time`, `deleted`) VALUES (16, 0, '测试管理', 2, 'test', 'test', NULL, 0, NULL, 'test', 1, '', NULL, '2024-02-20 21:17:35', NULL, '2024-02-20 21:24:12', 1);
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `menu_name`, `menu_type`, `router`, `path`, `icon`, `is_hidden`, `order_num`, `permission`, `status`, `remark`, `creator_id`, `create_time`, `updater_id`, `update_time`, `deleted`) VALUES (17, 16, '测试1', 1, 'test', 'test', NULL, 0, NULL, 'test', 1, '', NULL, '2024-02-20 21:17:52', NULL, '2024-02-20 21:24:09', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log` (
  `operation_id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `business_type` smallint NOT NULL DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `request_method` smallint NOT NULL DEFAULT '0' COMMENT '请求方式',
  `request_module` varchar(64) NOT NULL DEFAULT '' COMMENT '请求模块',
  `request_url` varchar(256) NOT NULL DEFAULT '' COMMENT '请求URL',
  `method_name` varchar(128) NOT NULL DEFAULT '' COMMENT '方法名称',
  `operator_type` smallint NOT NULL DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `user_id` bigint DEFAULT '0' COMMENT '用户ID',
  `username` varchar(32) DEFAULT '' COMMENT '操作人员',
  `operator_ip` varchar(128) DEFAULT '' COMMENT '操作人员ip',
  `operator_location` varchar(256) DEFAULT '' COMMENT '操作地点',
  `operation_param` varchar(2048) DEFAULT '' COMMENT '请求参数',
  `operation_result` varchar(2048) DEFAULT '' COMMENT '返回参数',
  `status` smallint NOT NULL DEFAULT '1' COMMENT '操作状态（1正常 0异常）',
  `error_stack` varchar(2048) DEFAULT '' COMMENT '错误消息',
  `operation_time` datetime NOT NULL COMMENT '操作时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`operation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志记录';

-- ----------------------------
-- Records of sys_operation_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `org_id` bigint NOT NULL AUTO_INCREMENT COMMENT '组织id',
  `parent_id` bigint NOT NULL DEFAULT '0' COMMENT '父组织id',
  `ancestors` text NOT NULL COMMENT '祖级列表',
  `org_name` varchar(64) NOT NULL DEFAULT '' COMMENT '组织名称',
  `order_num` int NOT NULL DEFAULT '0' COMMENT '显示顺序',
  `leader_name` varchar(64) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(16) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `status` smallint NOT NULL DEFAULT '0' COMMENT '组织状态（0停用 1启用）',
  `creator_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新者ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='组织表';

-- ----------------------------
-- Records of sys_org
-- ----------------------------
BEGIN;
INSERT INTO `sys_org` (`org_id`, `parent_id`, `ancestors`, `org_name`, `order_num`, `leader_name`, `phone`, `email`, `status`, `creator_id`, `create_time`, `updater_id`, `update_time`, `deleted`) VALUES (1, 0, '0', 'Gemini', 0, 'edison', '18888888888', '123232@qq.com', 1, NULL, '2024-02-17 16:09:34', NULL, '2024-02-17 16:14:51', 0);
INSERT INTO `sys_org` (`org_id`, `parent_id`, `ancestors`, `org_name`, `order_num`, `leader_name`, `phone`, `email`, `status`, `creator_id`, `create_time`, `updater_id`, `update_time`, `deleted`) VALUES (2, 1, '0,1', 'Gemini大连', 5, 'edison', '18828238233', '123232@qq.com', 1, NULL, '2024-02-17 16:16:12', NULL, NULL, 0);
INSERT INTO `sys_org` (`org_id`, `parent_id`, `ancestors`, `org_name`, `order_num`, `leader_name`, `phone`, `email`, `status`, `creator_id`, `create_time`, `updater_id`, `update_time`, `deleted`) VALUES (3, 1, '0,1', 'Gemini北京', 4, 'edison', '18828238233', '123232@qq.com', 1, NULL, '2024-02-17 16:16:20', NULL, NULL, 0);
INSERT INTO `sys_org` (`org_id`, `parent_id`, `ancestors`, `org_name`, `order_num`, `leader_name`, `phone`, `email`, `status`, `creator_id`, `create_time`, `updater_id`, `update_time`, `deleted`) VALUES (4, 1, '0,1', 'Gemini天津', 3, 'edison', '18828238233', '123232@qq.com', 1, NULL, '2024-02-17 16:16:26', NULL, NULL, 0);
INSERT INTO `sys_org` (`org_id`, `parent_id`, `ancestors`, `org_name`, `order_num`, `leader_name`, `phone`, `email`, `status`, `creator_id`, `create_time`, `updater_id`, `update_time`, `deleted`) VALUES (5, 1, '0,1', 'Gemini上海', 2, 'edison', '18828238233', '123232@qq.com', 1, NULL, '2024-02-17 16:16:30', NULL, NULL, 0);
INSERT INTO `sys_org` (`org_id`, `parent_id`, `ancestors`, `org_name`, `order_num`, `leader_name`, `phone`, `email`, `status`, `creator_id`, `create_time`, `updater_id`, `update_time`, `deleted`) VALUES (6, 1, '0,1', 'Gemini东京', 1, 'edison', '18828238233', '123232@qq.com', 1, NULL, '2024-02-17 16:16:35', NULL, '2024-02-17 16:17:18', 1);
INSERT INTO `sys_org` (`org_id`, `parent_id`, `ancestors`, `org_name`, `order_num`, `leader_name`, `phone`, `email`, `status`, `creator_id`, `create_time`, `updater_id`, `update_time`, `deleted`) VALUES (7, 0, '0', 'Fantasy', 2, 'jay', '1238832882', 'z34324@qq.com', 1, NULL, '2024-02-26 23:47:21', NULL, '2024-02-26 23:53:37', 1);
INSERT INTO `sys_org` (`org_id`, `parent_id`, `ancestors`, `org_name`, `order_num`, `leader_name`, `phone`, `email`, `status`, `creator_id`, `create_time`, `updater_id`, `update_time`, `deleted`) VALUES (8, 7, '0,7', 'Fantasy大连', 1, 'jj', '123232234234', 'jj@qq.com', 1, NULL, '2024-02-26 23:50:20', NULL, '2024-02-26 23:53:35', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(32) NOT NULL COMMENT '角色名称',
  `role_key` varchar(128) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `data_scope` smallint DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3: 本组织数据权限 4: 本组织及以下数据权限 5: 本人权限）',
  `data_org_set` varchar(1024) DEFAULT '' COMMENT '角色拥有的组织数据权限',
  `status` smallint NOT NULL COMMENT '角色状态（1正常 0停用）',
  `creator_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新者ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`role_id`, `role_name`, `role_key`, `role_sort`, `data_scope`, `data_org_set`, `status`, `creator_id`, `create_time`, `updater_id`, `update_time`, `remark`, `deleted`) VALUES (1, '超级管理员', 'super', 0, 1, '', 1, NULL, '2024-02-17 12:06:23', NULL, '2024-02-23 14:17:52', '超级管理员角色', 0);
INSERT INTO `sys_role` (`role_id`, `role_name`, `role_key`, `role_sort`, `data_scope`, `data_org_set`, `status`, `creator_id`, `create_time`, `updater_id`, `update_time`, `remark`, `deleted`) VALUES (2, '普通角色', 'common', 1, 2, '', 1, NULL, '2024-02-17 12:30:50', NULL, '2024-02-23 14:17:38', '普通角色权限', 0);
INSERT INTO `sys_role` (`role_id`, `role_name`, `role_key`, `role_sort`, `data_scope`, `data_org_set`, `status`, `creator_id`, `create_time`, `updater_id`, `update_time`, `remark`, `deleted`) VALUES (3, '测试角色', 'test', 10, 4, '', 1, NULL, '2024-02-17 12:31:21', NULL, '2024-02-17 12:32:27', '测试角色权限', 1);
INSERT INTO `sys_role` (`role_id`, `role_name`, `role_key`, `role_sort`, `data_scope`, `data_org_set`, `status`, `creator_id`, `create_time`, `updater_id`, `update_time`, `remark`, `deleted`) VALUES (4, '测试角色', 'test', 3, 1, '', 0, NULL, '2024-02-18 10:40:10', NULL, '2024-02-18 16:41:51', '测试角色', 1);
INSERT INTO `sys_role` (`role_id`, `role_name`, `role_key`, `role_sort`, `data_scope`, `data_org_set`, `status`, `creator_id`, `create_time`, `updater_id`, `update_time`, `remark`, `deleted`) VALUES (5, '测试角色1', 'test1', 3, 1, '', 0, NULL, '2024-02-18 10:42:47', NULL, '2024-02-25 11:01:02', '测试角色的司法会计法律框架发酵肯定是激发快乐减肥阿拉山口的飞机啊的反馈爱上', 0);
INSERT INTO `sys_role` (`role_id`, `role_name`, `role_key`, `role_sort`, `data_scope`, `data_org_set`, `status`, `creator_id`, `create_time`, `updater_id`, `update_time`, `remark`, `deleted`) VALUES (6, '测试角色2', 'rest', 1, 1, '', 1, NULL, '2024-02-18 13:39:04', NULL, '2024-02-18 14:41:25', 'test', 1);
INSERT INTO `sys_role` (`role_id`, `role_name`, `role_key`, `role_sort`, `data_scope`, `data_org_set`, `status`, `creator_id`, `create_time`, `updater_id`, `update_time`, `remark`, `deleted`) VALUES (7, '测试角色2', 'teste2', 6, 1, '', 1, NULL, '2024-02-18 16:42:42', NULL, '2024-02-18 16:42:46', '啊啊啊啊', 1);
INSERT INTO `sys_role` (`role_id`, `role_name`, `role_key`, `role_sort`, `data_scope`, `data_org_set`, `status`, `creator_id`, `create_time`, `updater_id`, `update_time`, `remark`, `deleted`) VALUES (8, '测试角色2', 'test3', 4, 1, '', 1, NULL, '2024-02-25 16:50:39', NULL, '2024-02-25 16:51:09', 'afsfsdfsfsf', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 10);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 11);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 12);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 13);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 14);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (1, 15);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 10);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 11);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 12);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 13);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 10);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 11);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (5, 12);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `role_id` bigint DEFAULT NULL COMMENT '角色id',
  `org_id` bigint DEFAULT NULL COMMENT '组织ID',
  `username` varchar(64) NOT NULL COMMENT '用户账号',
  `nickname` varchar(32) NOT NULL COMMENT '用户昵称',
  `user_type` smallint DEFAULT '0' COMMENT '用户类型（0 系统用户 1 web用户 2 移动端用户）',
  `email` varchar(128) DEFAULT '' COMMENT '用户邮箱',
  `phone` varchar(18) DEFAULT '' COMMENT '手机号码',
  `sex` smallint DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(512) DEFAULT '' COMMENT '头像地址',
  `password` varchar(128) NOT NULL DEFAULT '' COMMENT '密码',
  `status` smallint NOT NULL DEFAULT '0' COMMENT '帐号状态（1正常 2停用 3冻结）',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `is_admin` tinyint(1) NOT NULL DEFAULT '0' COMMENT '超级管理员标志（1是，0否）',
  `creator_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新者ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`user_id`, `role_id`, `org_id`, `username`, `nickname`, `user_type`, `email`, `phone`, `sex`, `avatar`, `password`, `status`, `login_ip`, `login_date`, `is_admin`, `creator_id`, `create_time`, `updater_id`, `update_time`, `remark`, `deleted`) VALUES (1, 1, 1, 'admin', 'edison', 0, '123@qq.com', '18888888888', 1, '', '$2a$10$tL.J9UD/p0cpOAz0GgCoAuasJ0bCXJCukBhSiHeLe/baKmSUetgia', 1, '', NULL, 1, NULL, '2024-02-17 16:43:58', NULL, NULL, '系统管理员', 0);
INSERT INTO `sys_user` (`user_id`, `role_id`, `org_id`, `username`, `nickname`, `user_type`, `email`, `phone`, `sex`, `avatar`, `password`, `status`, `login_ip`, `login_date`, `is_admin`, `creator_id`, `create_time`, `updater_id`, `update_time`, `remark`, `deleted`) VALUES (2, 2, 5, 'test', 'edison', 1, '1233@qq.com', '18888888885', 1, '', '$2a$10$bJm6svGV03QFQ3sF8kqsQO3acqYUYbJ8n4KXkMdeWGn4HIKgS8fvu', 1, '', NULL, 0, NULL, '2024-02-17 16:44:35', NULL, '2024-02-28 09:49:15', '测试用户', 0);
INSERT INTO `sys_user` (`user_id`, `role_id`, `org_id`, `username`, `nickname`, `user_type`, `email`, `phone`, `sex`, `avatar`, `password`, `status`, `login_ip`, `login_date`, `is_admin`, `creator_id`, `create_time`, `updater_id`, `update_time`, `remark`, `deleted`) VALUES (3, 5, 2, 'test2', 'edison', 1, '123366@qq.com', '18888888886', 1, '', '$2a$10$Y2XMhYpJ8vUixYNu/kujJuBK4lTCd6USuYRYUTEGStK55ocIlXDL2', 1, '', NULL, 0, NULL, '2024-02-17 16:44:50', NULL, '2024-02-17 16:54:29', '测试用户2', 0);
INSERT INTO `sys_user` (`user_id`, `role_id`, `org_id`, `username`, `nickname`, `user_type`, `email`, `phone`, `sex`, `avatar`, `password`, `status`, `login_ip`, `login_date`, `is_admin`, `creator_id`, `create_time`, `updater_id`, `update_time`, `remark`, `deleted`) VALUES (4, 2, 1, 'sdfas', 'swerw', 0, '1d122@qq.com', '13223243241', 2, '', '$2a$10$LJ/474SWnkQ6RQ3lIQFFAOAbV.cSsCtIlD6WlTXbDlP9uRAaBqM8m', 3, '', NULL, 0, NULL, '2024-02-27 19:05:46', NULL, '2024-02-27 19:47:53', 'sdfsf', 0);
INSERT INTO `sys_user` (`user_id`, `role_id`, `org_id`, `username`, `nickname`, `user_type`, `email`, `phone`, `sex`, `avatar`, `password`, `status`, `login_ip`, `login_date`, `is_admin`, `creator_id`, `create_time`, `updater_id`, `update_time`, `remark`, `deleted`) VALUES (5, 5, 3, 'test1111', 'test1111', 0, '1d12d2@qq.com', '1322324322', 1, '', '$2a$10$R/FPvgEhk31RsyZaRpvfw.cRaVunykNYZQrt2hnkEq8WKul3iSqwW', 2, '', NULL, 0, NULL, '2024-02-27 19:14:41', NULL, '2024-02-27 19:47:58', 'sdfxcvxv', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
