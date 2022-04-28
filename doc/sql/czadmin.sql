/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : czadmin

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 28/04/2022 11:07:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for code_column_config
-- ----------------------------
DROP TABLE IF EXISTS `code_column_config`;
CREATE TABLE `code_column_config` (
  `column_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `table_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `column_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `column_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `dict_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `extra` varchar(255) DEFAULT NULL,
  `form_show` tinyint(1) DEFAULT NULL,
  `form_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `key_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `list_show` tinyint(1) DEFAULT NULL,
  `not_null` tinyint(1) DEFAULT NULL,
  `query_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`column_id`) USING BTREE,
  KEY `idx_table_name` (`table_name`)
) ENGINE=InnoDB AUTO_INCREMENT=412 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='代码生成字段信息存储';

-- ----------------------------
-- Records of code_column_config
-- ----------------------------
BEGIN;
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (191, 'mnt_deploy', 'deploy_id', 'bigint', NULL, 'auto_increment', 1, NULL, 'PRI', 1, 0, NULL, 'ID');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (192, 'mnt_deploy', 'app_id', 'bigint', NULL, '', 1, NULL, 'MUL', 1, 0, NULL, '应用编号');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (193, 'mnt_deploy', 'create_by', 'varchar', NULL, '', 1, NULL, '', 1, 0, NULL, '创建者');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (194, 'mnt_deploy', 'update_by', 'varchar', NULL, '', 1, NULL, '', 1, 0, NULL, '更新者');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (195, 'mnt_deploy', 'create_time', 'datetime', NULL, '', 1, NULL, '', 1, 0, NULL, '');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (196, 'mnt_deploy', 'update_time', 'datetime', NULL, '', 1, NULL, '', 1, 0, NULL, '更新时间');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (197, 'mnt_app', 'app_id', 'bigint', NULL, 'auto_increment', 1, NULL, 'PRI', 1, 0, NULL, 'ID');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (198, 'mnt_app', 'name', 'varchar', NULL, '', 1, NULL, '', 1, 0, NULL, '应用名称');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (199, 'mnt_app', 'upload_path', 'varchar', NULL, '', 1, NULL, '', 1, 0, NULL, '上传目录');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (200, 'mnt_app', 'deploy_path', 'varchar', NULL, '', 1, NULL, '', 1, 0, NULL, '部署路径');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (201, 'mnt_app', 'backup_path', 'varchar', NULL, '', 1, NULL, '', 1, 0, NULL, '备份路径');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (202, 'mnt_app', 'port', 'int', NULL, '', 1, NULL, '', 1, 0, NULL, '应用端口');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (203, 'mnt_app', 'start_script', 'varchar', NULL, '', 1, NULL, '', 1, 0, NULL, '启动脚本');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (204, 'mnt_app', 'deploy_script', 'varchar', NULL, '', 1, NULL, '', 1, 0, NULL, '部署脚本');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (205, 'mnt_app', 'create_by', 'varchar', NULL, '', 1, NULL, '', 1, 0, NULL, '创建者');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (206, 'mnt_app', 'update_by', 'varchar', NULL, '', 1, NULL, '', 1, 0, NULL, '更新者');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (207, 'mnt_app', 'create_time', 'datetime', NULL, '', 1, NULL, '', 1, 0, NULL, '创建日期');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (208, 'mnt_app', 'update_time', 'datetime', NULL, '', 1, NULL, '', 1, 0, NULL, '更新时间');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (209, 'mnt_deploy_server', 'deploy_id', 'bigint', NULL, '', 1, NULL, 'PRI', 1, 1, NULL, '部署ID');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (210, 'mnt_deploy_server', 'server_id', 'bigint', NULL, '', 1, NULL, 'PRI', 1, 1, NULL, '服务ID');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (211, 'mnt_deploy_history', 'history_id', 'varchar', NULL, '', 1, NULL, 'PRI', 1, 1, NULL, 'ID');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (212, 'mnt_deploy_history', 'app_name', 'varchar', NULL, '', 1, NULL, '', 1, 1, NULL, '应用名称');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (213, 'mnt_deploy_history', 'deploy_date', 'datetime', NULL, '', 1, NULL, '', 1, 1, NULL, '部署日期');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (214, 'mnt_deploy_history', 'deploy_user', 'varchar', NULL, '', 1, NULL, '', 1, 1, NULL, '部署用户');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (215, 'mnt_deploy_history', 'ip', 'varchar', NULL, '', 1, NULL, '', 1, 1, NULL, '服务器IP');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (216, 'mnt_deploy_history', 'deploy_id', 'bigint', NULL, '', 1, NULL, '', 1, 0, NULL, '部署编号');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (217, 'sys_menu', 'menu_id', 'int', '', 'auto_increment', 1, '', 'PRI', 1, 0, '', 'ID');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (218, 'sys_menu', 'pid', 'int', 'user_status', '', 1, '', '', 1, 0, '', '上级菜单ID');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (221, 'sys_menu', 'title', 'varchar', '', '', 1, '', '', 1, 0, '', '菜单标题');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (222, 'sys_menu', 'name', 'varchar', '', '', 1, '', '', 1, 0, '', '组件名称');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (223, 'sys_menu', 'component', 'varchar', '', '', 1, '', '', 1, 0, '', '组件');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (224, 'sys_menu', 'menu_sort', 'int', '', '', 1, '', '', 1, 0, '', '排序');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (225, 'sys_menu', 'icon', 'varchar', '', '', 1, '', '', 1, 0, '', '图标');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (226, 'sys_menu', 'path', 'varchar', '', '', 1, '', '', 1, 0, '', '链接地址');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (227, 'sys_menu', 'i_frame', 'tinyint', '', '', 1, '', '', 1, 0, '', '是否外链');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (229, 'sys_menu', 'hidden', 'tinyint', '', '', 1, '', '', 1, 0, '', '隐藏');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (230, 'sys_menu', 'permission', 'varchar', '', '', 1, '', '', 1, 0, '', '权限');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (231, 'sys_menu', 'create_by', 'varchar', '', '', 1, '', '', 1, 0, '', '创建者');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (232, 'sys_menu', 'update_by', 'varchar', '', '', 1, '', '', 1, 0, '', '更新者');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (233, 'sys_menu', 'create_time', 'datetime', '', '', 1, '', '', 1, 0, '', '创建日期');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (234, 'sys_menu', 'update_time', 'datetime', '', '', 1, '', '', 1, 0, '', '更新时间');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (336, 'code_column_config', 'column_id', 'int', '', 'auto_increment', 0, '', 'PRI', 0, 0, '', 'ID');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (337, 'code_column_config', 'table_name', 'varchar', '', '', 0, '', 'MUL', 0, 1, '', '');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (338, 'code_column_config', 'column_name', 'varchar', '', '', 0, '', '', 0, 1, '', '');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (339, 'code_column_config', 'column_type', 'varchar', '', '', 0, '', '', 0, 1, 'NotNull', '');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (340, 'code_column_config', 'dict_name', 'varchar', '', '', 0, '', '', 0, 1, '', '');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (341, 'code_column_config', 'extra', 'varchar', '', '', 0, '', '', 0, 1, '', '');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (342, 'code_column_config', 'form_show', 'tinyint', '', '', 0, '', '', 0, 1, '', '');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (343, 'code_column_config', 'form_type', 'varchar', '', '', 0, '', '', 0, 1, '', '');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (344, 'code_column_config', 'key_type', 'varchar', 'user_status', '', 0, '', '', 0, 1, '', '');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (345, 'code_column_config', 'list_show', 'tinyint', '', '', 0, '', '', 0, 1, '', '');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (346, 'code_column_config', 'not_null', 'tinyint', '', '', 0, '', '', 0, 1, '', '');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (347, 'code_column_config', 'query_type', 'varchar', '', '', 0, '', '', 0, 1, '', '');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (348, 'code_column_config', 'remark', 'varchar', '', '', 0, '', '', 0, 1, '', '');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (349, 'code_column_config', 'date_annotation', 'varchar', '', '', 0, '', '', 0, 1, '', '');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (352, 'sys_dept', 'dept_id', 'int', '', 'auto_increment', 0, '', 'PRI', 0, 0, '=', 'ID');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (353, 'sys_dept', 'pid', 'int', '', '', 0, '', '', 0, 1, '!=', '上级部门');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (354, 'sys_dept', 'name', 'varchar', 'dept_status', '', 1, '', '', 1, 1, '>=', '名称');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (355, 'sys_dept', 'path', 'varchar', '', '', 0, '', '', 0, 1, 'Like', '组织路径');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (356, 'sys_dept', 'dept_sort', 'int', '', '', 0, '', '', 0, 1, 'NotNull', '排序');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (357, 'sys_dept', 'create_by', 'varchar', '', '', 0, '', '', 0, 0, 'BetWeen', '创建者');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (358, 'sys_dept', 'update_by', 'varchar', '', '', 0, '', '', 0, 0, '', '更新者');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (359, 'sys_dept', 'create_time', 'datetime', '', '', 0, '', '', 0, 0, '', '创建日期');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (360, 'sys_dept', 'update_time', 'datetime', '', '', 0, '', '', 0, 0, '', '更新时间');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (377, 'sys_menu', 'menu_type', 'int', '', '', 0, '', '', 0, 0, '', '菜单类型,0:菜单1：路由2：按钮');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (378, 'sys_menu', 'is_cache', 'tinyint', '', '', 0, '', '', 0, 0, '', '缓存');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (379, 'sys_menu', 'has_children', 'tinyint', '', '', 0, '', '', 0, 0, '', '是否有子菜单');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (380, 'sys_files', 'file_id', 'int', '', 'auto_increment', 0, '', 'PRI', 0, 0, '', '主键');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (381, 'sys_files', 'file_name', 'varchar', '', '', 0, '', '', 0, 0, '', '文件名称');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (382, 'sys_files', 'file_original_name', 'varchar', '', '', 0, '', '', 0, 0, '', '文件原名称');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (383, 'sys_files', 'file_url', 'varchar', '', '', 0, '', '', 0, 0, '', '文件路径');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (384, 'sys_files', 'file_url_thumb', 'varchar', '', '', 0, '', '', 0, 0, '', '压缩图地址');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (385, 'sys_files', 'file_size', 'bigint', '', '', 0, '', '', 0, 0, '', '文件大小kb');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (386, 'sys_files', 'file_type', 'varchar', '', '', 0, '', '', 0, 0, '', '文件类型');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (387, 'sys_files', 'create_date', 'date', '', '', 0, '', '', 0, 0, '', '创建日期');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (388, 'sys_files', 'create_time', 'timestamp', '', 'DEFAULT_GENERATED', 0, '', '', 0, 0, '', '创建时间');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (389, 'sys_files', 'app_code', 'varchar', 'app_name', '', 1, 'Select', '', 0, 0, '', '应用名称');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (390, 'sys_files', 'file_input_path', 'varchar', 'file_path', '', 1, 'Select', '', 0, 0, '', '输入路径');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (391, 'sys_files', 'create_by', 'varchar', '', '', 0, '', '', 0, 0, '', '操作人');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (402, 'sys_email_config', 'email_id', 'int', '', 'auto_increment', 0, '', 'PRI', 0, 0, '', 'ID');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (403, 'sys_email_config', 'from_user', 'varchar', '', '', 1, 'Input', '', 1, 1, 'Like', '发件人');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (404, 'sys_email_config', 'host', 'varchar', '', '', 1, 'Input', '', 1, 1, 'Like', '邮件服务器SMTP地址');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (405, 'sys_email_config', 'pass', 'varchar', '', '', 1, 'Input', '', 1, 1, '', '密码');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (406, 'sys_email_config', 'port', 'int', '', '', 1, 'Input', '', 1, 1, '', '端口');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (407, 'sys_email_config', 'user', 'varchar', '', '', 1, 'Input', '', 1, 1, 'Like', '发件人用户名');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (408, 'sys_email_config', 'create_time', 'datetime', '', '', 0, '', '', 1, 0, '', '创建时间');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (409, 'sys_email_config', 'create_by', 'varchar', '', '', 0, '', '', 1, 0, '', '创建人');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (410, 'sys_email_config', 'update_time', 'datetime', '', '', 0, '', '', 0, 0, '', '更新时间');
INSERT INTO `code_column_config` (`column_id`, `table_name`, `column_name`, `column_type`, `dict_name`, `extra`, `form_show`, `form_type`, `key_type`, `list_show`, `not_null`, `query_type`, `remark`) VALUES (411, 'sys_email_config', 'update_by', 'varchar', '', '', 0, '', '', 0, 0, '', '更新人');
COMMIT;

-- ----------------------------
-- Table structure for code_gen_config
-- ----------------------------
DROP TABLE IF EXISTS `code_gen_config`;
CREATE TABLE `code_gen_config` (
  `config_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `table_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '表名',
  `author` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '作者',
  `cover` tinyint(1) DEFAULT '0' COMMENT '是否覆盖',
  `module_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '模块名称',
  `pack` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '至于哪个包下',
  `path` varchar(255) DEFAULT NULL COMMENT '前端代码生成的路径',
  `api_path` varchar(255) DEFAULT NULL COMMENT '前端Api文件路径',
  `prefix` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '表前缀',
  `api_alias` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '接口名称',
  PRIMARY KEY (`config_id`) USING BTREE,
  KEY `idx_table_name` (`table_name`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='代码生成器配置';

-- ----------------------------
-- Records of code_gen_config
-- ----------------------------
BEGIN;
INSERT INTO `code_gen_config` (`config_id`, `table_name`, `author`, `cover`, `module_name`, `pack`, `path`, `api_path`, `prefix`, `api_alias`) VALUES (11, 'sys_dept', 'chaizi', 0, 'cz-logger', 'com.cz', 'sysdept', 'sysdept/', '', '部门接口');
INSERT INTO `code_gen_config` (`config_id`, `table_name`, `author`, `cover`, `module_name`, `pack`, `path`, `api_path`, `prefix`, `api_alias`) VALUES (12, 'code_column_config', 'aq', 0, 'asd', 'asd', 'asd', 'asd/', '', 'asd');
INSERT INTO `code_gen_config` (`config_id`, `table_name`, `author`, `cover`, `module_name`, `pack`, `path`, `api_path`, `prefix`, `api_alias`) VALUES (13, 'sys_quartz_job', '柴旭红', 1, 'cz-logger', 'com.cz', 'system/timin', 'system/timin/', '', '定时任务');
INSERT INTO `code_gen_config` (`config_id`, `table_name`, `author`, `cover`, `module_name`, `pack`, `path`, `api_path`, `prefix`, `api_alias`) VALUES (14, 'sys_menu', 'chaizi', 0, 'cz-system', 'com.cz', 'menu', 'menu/', '', '菜单');
INSERT INTO `code_gen_config` (`config_id`, `table_name`, `author`, `cover`, `module_name`, `pack`, `path`, `api_path`, `prefix`, `api_alias`) VALUES (15, 'sys_files', 'chaizi', 0, 'cz-system', 'com.cz', 'files', 'files/', '', '文件存储');
INSERT INTO `code_gen_config` (`config_id`, `table_name`, `author`, `cover`, `module_name`, `pack`, `path`, `api_path`, `prefix`, `api_alias`) VALUES (16, 'sys_email_config', 'chaizi', 0, 'cz-system', 'com.cz', 'email', 'email/', '', '邮箱配置');
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` int(11) DEFAULT NULL COMMENT '上级部门',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `path` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组织路径',
  `dept_sort` int(5) DEFAULT '999' COMMENT '排序',
  `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='部门';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` (`dept_id`, `pid`, `name`, `path`, `dept_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (2, 7, '研发部', '华南分部_研发部', 3, 'admin', 'admin', '2019-03-25 09:15:32', '2020-08-02 14:48:47');
INSERT INTO `sys_dept` (`dept_id`, `pid`, `name`, `path`, `dept_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (5, 8, '运维部', '华北分部_运维部', 4, 'admin', 'admin', '2019-03-25 09:20:44', '2022-03-18 17:38:58');
INSERT INTO `sys_dept` (`dept_id`, `pid`, `name`, `path`, `dept_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (7, 0, '华南分部', '华南分部', 0, 'admin', 'admin', '2019-03-25 11:04:50', '2022-03-18 17:32:04');
INSERT INTO `sys_dept` (`dept_id`, `pid`, `name`, `path`, `dept_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (8, 0, '华北分部', '华北分部', 1, 'admin', 'admin', '2019-03-25 11:04:53', '2020-05-14 12:54:00');
INSERT INTO `sys_dept` (`dept_id`, `pid`, `name`, `path`, `dept_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (15, 8, 'UI部门', '华北分部_UI部门', 7, 'admin', 'admin', '2020-05-13 22:56:53', '2020-05-14 12:54:13');
INSERT INTO `sys_dept` (`dept_id`, `pid`, `name`, `path`, `dept_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (17, 2, '研发一组', '华南分部_研发部_研发一组', 999, 'admin', 'admin', '2020-08-02 14:49:07', '2020-08-02 14:49:07');
INSERT INTO `sys_dept` (`dept_id`, `pid`, `name`, `path`, `dept_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (20, 15, 'asdasd', '华北分部_UI部门_asdasd', 999, 'admin', 'admin', '2022-03-18 17:08:01', '2022-03-18 17:10:21');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `dict_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `dict_sort` int(4) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='数据字典';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` (`dict_id`, `name`, `description`, `dict_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (1, 'user_status', '用户状态', 12, NULL, 'admin', '2019-10-27 20:31:36', '2022-03-30 10:32:17');
INSERT INTO `sys_dict` (`dict_id`, `name`, `description`, `dict_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (4, 'dept_status', '部门状态', 3, NULL, 'admin', '2019-10-27 20:31:36', '2022-03-30 10:31:58');
INSERT INTO `sys_dict` (`dict_id`, `name`, `description`, `dict_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (5, 'job_status', '岗位状态', 3, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict` (`dict_id`, `name`, `description`, `dict_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (8, 'file_path', '文件路径使用', 4, 'admin', 'admin', '2022-04-22 17:42:59', '2022-04-22 17:43:20');
INSERT INTO `sys_dict` (`dict_id`, `name`, `description`, `dict_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (9, 'app_name', '业务名称', 5, 'admin', NULL, '2022-04-22 17:47:09', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `dict_id` int(11) DEFAULT NULL COMMENT '字典id',
  `label` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典标签',
  `value` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典值',
  `dict_sort` int(2) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`detail_id`) USING BTREE,
  KEY `FK5tpkputc6d9nboxojdbgnpmyb` (`dict_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='数据字典详情';

-- ----------------------------
-- Records of sys_dict_detail
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_detail` (`detail_id`, `dict_id`, `label`, `value`, `dict_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (1, 1, '激活', 'true', 1, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict_detail` (`detail_id`, `dict_id`, `label`, `value`, `dict_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (2, 1, '禁用', 'false', 2, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` (`detail_id`, `dict_id`, `label`, `value`, `dict_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (3, 4, '启用', 'true', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` (`detail_id`, `dict_id`, `label`, `value`, `dict_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (4, 4, '停用', 'false', 2, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict_detail` (`detail_id`, `dict_id`, `label`, `value`, `dict_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (5, 5, '启用', 'true', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_detail` (`detail_id`, `dict_id`, `label`, `value`, `dict_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (6, 5, '停用', 'false', 2, NULL, NULL, '2019-10-27 20:31:36', NULL);
INSERT INTO `sys_dict_detail` (`detail_id`, `dict_id`, `label`, `value`, `dict_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (7, 1, 'asdas', 'asdasd', 999, 'admin', NULL, '2022-03-30 14:24:51', NULL);
INSERT INTO `sys_dict_detail` (`detail_id`, `dict_id`, `label`, `value`, `dict_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (10, 9, '后台管理', 'system', 1, 'admin', 'admin', '2022-04-22 17:47:29', '2022-04-22 17:59:30');
INSERT INTO `sys_dict_detail` (`detail_id`, `dict_id`, `label`, `value`, `dict_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (11, 8, '图片', 'image', 1, 'admin', 'admin', '2022-04-22 17:47:45', '2022-04-22 17:58:52');
INSERT INTO `sys_dict_detail` (`detail_id`, `dict_id`, `label`, `value`, `dict_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (12, 8, '文档', 'txt', 2, 'admin', 'admin', '2022-04-22 17:48:09', '2022-04-22 17:58:57');
INSERT INTO `sys_dict_detail` (`detail_id`, `dict_id`, `label`, `value`, `dict_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (13, 8, '音乐', 'music', 3, 'admin', 'admin', '2022-04-22 17:48:24', '2022-04-22 17:59:03');
INSERT INTO `sys_dict_detail` (`detail_id`, `dict_id`, `label`, `value`, `dict_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (14, 8, '视频', 'video', 4, 'admin', 'admin', '2022-04-22 17:48:58', '2022-04-22 17:59:08');
INSERT INTO `sys_dict_detail` (`detail_id`, `dict_id`, `label`, `value`, `dict_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (15, 8, '其他', 'other', 5, 'admin', 'admin', '2022-04-22 17:53:30', '2022-04-22 17:59:13');
INSERT INTO `sys_dict_detail` (`detail_id`, `dict_id`, `label`, `value`, `dict_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (16, 8, '活动', 'activity', 6, 'admin', NULL, '2022-04-22 18:18:28', NULL);
INSERT INTO `sys_dict_detail` (`detail_id`, `dict_id`, `label`, `value`, `dict_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (17, 8, '资讯', 'infomation', 7, 'admin', NULL, '2022-04-22 18:18:42', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_email_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_email_config`;
CREATE TABLE `sys_email_config` (
  `email_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `from_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发件人',
  `host` varchar(255) DEFAULT NULL COMMENT '邮件服务器SMTP地址',
  `pass` varchar(255) DEFAULT NULL COMMENT '密码',
  `port` int(11) DEFAULT NULL COMMENT '端口',
  `user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发件人用户名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`email_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='邮箱配置';

-- ----------------------------
-- Records of sys_email_config
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_files
-- ----------------------------
DROP TABLE IF EXISTS `sys_files`;
CREATE TABLE `sys_files` (
  `file_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `file_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件名称',
  `file_original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件原名称',
  `file_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件路径',
  `file_url_thumb` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '压缩图地址',
  `file_size` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件大小',
  `file_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件类型',
  `create_date` date DEFAULT NULL COMMENT '创建日期',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `app_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '应用名称',
  `file_input_path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '输入路径',
  `create_by` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=437 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='文件表';

-- ----------------------------
-- Records of sys_files
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job` (
  `job_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位名称',
  `job_sort` int(5) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`job_id`) USING BTREE,
  UNIQUE KEY `uniq_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='岗位';

-- ----------------------------
-- Records of sys_job
-- ----------------------------
BEGIN;
INSERT INTO `sys_job` (`job_id`, `name`, `job_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (10, '产品经理', 4, NULL, NULL, '2019-03-29 14:55:51', NULL);
INSERT INTO `sys_job` (`job_id`, `name`, `job_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (12, '软件测试', 5, NULL, 'admin', '2019-03-31 13:39:43', '2020-05-10 19:56:26');
INSERT INTO `sys_job` (`job_id`, `name`, `job_sort`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (14, '技术管理岗', 1, 'admin', NULL, '2022-03-24 10:45:14', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `log_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `method` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `params` text CHARACTER SET utf8,
  `request_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `browser` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `exception_detail` text CHARACTER SET utf8,
  `create_time` datetime DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `result` text COLLATE utf8mb4_general_ci,
  PRIMARY KEY (`log_id`) USING BTREE,
  KEY `log_create_time_index` (`create_time`),
  KEY `inx_log_type` (`log_type`)
) ENGINE=InnoDB AUTO_INCREMENT=388 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_log` (`log_id`, `description`, `log_type`, `method`, `params`, `request_ip`, `time`, `username`, `address`, `browser`, `exception_detail`, `create_time`, `create_date`, `result`) VALUES (385, '用户登录', 'INFO', 'com.cz.cache.LoginUserCache.setLoginUser()', '', '127.0.0.1', 66, 'admin', '内网IP', 'Chrome 100.0.4896.127', NULL, '2022-04-28 11:04:24', '2022-04-28', 'null');
INSERT INTO `sys_log` (`log_id`, `description`, `log_type`, `method`, `params`, `request_ip`, `time`, `username`, `address`, `browser`, `exception_detail`, `create_time`, `create_date`, `result`) VALUES (386, '删除菜单', 'INFO', 'com.cz.controller.system.MenuController.delMenus()', '[28]', '127.0.0.1', 165, 'admin', '内网IP', 'Chrome 100.0.4896.127', NULL, '2022-04-28 11:05:16', '2022-04-28', '{\"code\":1,\"data\":[{\"component\":\"system/timing/index\",\"createTime\":\"2019-01-07 20:34:40\",\"hasChildren\":true,\"hidden\":false,\"iFrame\":false,\"icon\":\"timing\",\"isCache\":false,\"menuId\":28,\"menuSort\":999,\"menuType\":1,\"name\":\"Timing\",\"path\":\"timing\",\"permission\":\"timing:list\",\"pid\":1,\"title\":\"任务调度\"},{\"component\":\"\",\"createTime\":\"2019-10-29 13:07:28\",\"hasChildren\":false,\"hidden\":false,\"iFrame\":false,\"icon\":\"\",\"isCache\":false,\"menuId\":73,\"menuSort\":2,\"menuType\":2,\"path\":\"\",\"permission\":\"timing:add\",\"pid\":28,\"title\":\"任务新增\"},{\"component\":\"\",\"createTime\":\"2019-10-29 13:07:41\",\"hasChildren\":false,\"hidden\":false,\"iFrame\":false,\"icon\":\"\",\"isCache\":false,\"menuId\":74,\"menuSort\":3,\"menuType\":2,\"path\":\"\",\"permission\":\"timing:edit\",\"pid\":28,\"title\":\"任务编辑\"},{\"component\":\"\",\"createTime\":\"2019-10-29 13:07:54\",\"hasChildren\":false,\"hidden\":false,\"iFrame\":false,\"icon\":\"\",\"isCache\":false,\"menuId\":75,\"menuSort\":4,\"menuType\":2,\"path\":\"\",\"permission\":\"timing:del\",\"pid\":28,\"title\":\"任务删除\"}],\"errMsg\":\"success\"}');
INSERT INTO `sys_log` (`log_id`, `description`, `log_type`, `method`, `params`, `request_ip`, `time`, `username`, `address`, `browser`, `exception_detail`, `create_time`, `create_date`, `result`) VALUES (387, '删除文件', 'INFO', 'com.cz.controller.tools.FilesController.delete()', '[436,435,434,433,432]', '127.0.0.1', 116, 'admin', '内网IP', 'Chrome 100.0.4896.127', NULL, '2022-04-28 11:05:53', '2022-04-28', '{\"code\":1,\"errMsg\":\"success\"}');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` int(11) DEFAULT NULL COMMENT '上级菜单ID',
  `menu_type` int(2) DEFAULT NULL COMMENT '菜单类型,0:菜单1：路由2：按钮',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单标题',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '组件名称',
  `component` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '组件',
  `menu_sort` int(5) DEFAULT NULL COMMENT '排序',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '链接地址',
  `i_frame` tinyint(1) DEFAULT '0' COMMENT '是否外链',
  `is_cache` tinyint(1) DEFAULT '0' COMMENT '缓存',
  `hidden` tinyint(1) DEFAULT '0' COMMENT '隐藏',
  `permission` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限',
  `has_children` tinyint(1) DEFAULT '0' COMMENT '是否有子菜单',
  `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建操作人',
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新操作人',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='系统菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (1, 0, 0, '系统管理', '', '', 1, 'system', 'system', 0, 0, 0, '', 1, '', 'admin', '2018-12-18 15:11:29', '2022-04-21 17:53:44');
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (2, 1, 1, '用户管理', 'User', 'system/user/index', 2, 'peoples', 'user', 0, 0, 0, 'user:list', 1, NULL, 'admin', '2018-12-18 15:14:44', '2022-03-25 15:35:23');
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (3, 1, 1, '角色管理', 'Role', 'system/role/index', 3, 'role', 'role', 0, 0, 0, 'roles:list', 1, NULL, NULL, '2018-12-18 15:16:07', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (5, 1, 1, '菜单管理', 'Menu', 'system/menu/index', 5, 'menu', 'menu', 0, 1, 0, 'menu:list', 1, '', 'admin', '2018-12-18 15:17:28', '2022-03-16 19:28:58');
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (6, 0, 0, '系统监控', NULL, NULL, 10, 'monitor', 'monitor', 0, 0, 0, NULL, 1, NULL, NULL, '2018-12-18 15:17:48', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (7, 6, 1, '操作日志', 'Log', 'monitor/log/index', 11, 'log', 'logs', 0, 1, 0, 'log:list', 1, '', 'admin', '2018-12-18 15:18:26', '2022-04-21 11:53:40');
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (10, 0, 0, '组件管理', NULL, NULL, 50, 'zujian', 'components', 0, 0, 0, NULL, 1, NULL, NULL, '2018-12-19 13:38:16', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (15, 10, 1, '富文本', 'Editors', 'components/Editor', 52, 'fwb', 'tinymce', 0, 1, 0, '', 0, NULL, 'admin', '2018-12-27 11:58:25', '2022-04-24 18:32:14');
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (18, 36, 1, '存储管理', 'Storage', 'tools/storage/index', 1, 'qiniu', 'storage', 0, 0, 0, 'storage:list', 1, NULL, 'admin', '2018-12-31 11:12:15', '2022-04-25 18:19:00');
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (30, 36, 1, '代码生成', 'GeneratorIndex', 'generator/index', 2, 'dev', 'generator', 0, 1, 0, '', 0, NULL, 'admin', '2019-01-11 15:45:55', '2022-04-25 18:19:10');
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (32, 6, 1, '异常日志', 'ErrorLog', 'monitor/log/errorLog', 12, 'error', 'errorLog', 0, 0, 0, NULL, 0, NULL, NULL, '2019-01-13 13:49:03', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (33, 10, 1, 'Markdown', 'Markdown', 'components/MarkDown', 53, 'markdown', 'markdown', 0, 1, 0, '', 0, NULL, 'admin', '2019-03-08 13:46:44', '2022-04-24 18:31:58');
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (35, 1, 1, '部门管理', 'Dept', 'system/dept/index', 6, 'dept', 'dept', 0, 0, 0, 'dept:list', 1, NULL, NULL, '2019-03-25 09:46:00', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (36, 0, 0, '系统工具', NULL, '', 30, 'sys-tools', 'sys-tools', 0, 0, 0, NULL, 1, NULL, 'admin', '2019-03-29 10:57:35', '2022-04-25 17:01:35');
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (37, 1, 1, '岗位管理', 'Job', 'system/job/index', 7, 'Steve-Jobs', 'job', 0, 0, 0, 'job:list', 1, NULL, NULL, '2019-03-29 13:51:18', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (39, 1, 1, '字典管理', 'Dict', 'system/dict/index', 8, 'dictionary', 'dict', 0, 0, 0, 'dict:list', 1, NULL, NULL, '2019-04-10 11:49:04', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (41, 6, 1, '在线用户', 'OnlineUser', 'monitor/online/index', 10, 'Steve-Jobs', 'online', 0, 0, 0, 'online:list', 1, '', 'admin', '2019-10-26 22:08:43', '2022-04-20 17:52:10');
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (44, 2, 2, '用户新增', NULL, '', 2, '', '', 0, 0, 0, 'user:add', 0, NULL, NULL, '2019-10-29 10:59:46', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (45, 2, 2, '用户编辑', NULL, '', 3, '', '', 0, 0, 0, 'user:edit', 0, NULL, NULL, '2019-10-29 11:00:08', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (46, 2, 2, '用户删除', NULL, '', 4, '', '', 0, 0, 0, 'user:del', 0, NULL, NULL, '2019-10-29 11:00:23', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (48, 3, 2, '角色创建', NULL, '', 2, '', '', 0, 0, 0, 'roles:add', 0, NULL, NULL, '2019-10-29 12:45:34', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (49, 3, 2, '角色修改', NULL, '', 3, '', '', 0, 0, 0, 'roles:edit', 0, NULL, NULL, '2019-10-29 12:46:16', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (50, 3, 2, '角色删除', NULL, '', 4, '', '', 0, 0, 0, 'roles:del', 0, NULL, NULL, '2019-10-29 12:46:51', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (52, 5, 2, '菜单新增', NULL, '', 2, '', '', 0, 0, 0, 'menu:add', 0, NULL, NULL, '2019-10-29 12:55:07', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (53, 5, 2, '菜单编辑', NULL, '', 3, '', '', 0, 0, 0, 'menu:edit', 0, NULL, NULL, '2019-10-29 12:55:40', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (54, 5, 2, '菜单删除', NULL, '', 4, '', '', 0, 0, 0, 'menu:del', 0, NULL, NULL, '2019-10-29 12:56:00', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (56, 35, 2, '部门新增', NULL, '', 2, '', '', 0, 0, 0, 'dept:add', 0, NULL, NULL, '2019-10-29 12:57:09', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (57, 35, 2, '部门编辑', NULL, '', 3, '', '', 0, 0, 0, 'dept:edit', 0, NULL, NULL, '2019-10-29 12:57:27', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (58, 35, 2, '部门删除', NULL, '', 4, '', '', 0, 0, 0, 'dept:del', 0, NULL, NULL, '2019-10-29 12:57:41', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (60, 37, 2, '岗位新增', NULL, '', 2, '', '', 0, 0, 0, 'job:add', 0, NULL, NULL, '2019-10-29 12:58:27', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (61, 37, 2, '岗位编辑', NULL, '', 3, '', '', 0, 0, 0, 'job:edit', 0, NULL, NULL, '2019-10-29 12:58:45', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (62, 37, 2, '岗位删除', NULL, '', 4, '', '', 0, 0, 0, 'job:del', 0, NULL, NULL, '2019-10-29 12:59:04', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (64, 39, 2, '字典新增', NULL, '', 2, '', '', 0, 0, 0, 'dict:add', 0, NULL, NULL, '2019-10-29 13:00:17', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (65, 39, 2, '字典编辑', NULL, '', 3, '', '', 0, 0, 0, 'dict:edit', 0, NULL, NULL, '2019-10-29 13:00:42', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (66, 39, 2, '字典删除', NULL, '', 4, '', '', 0, 0, 0, 'dict:del', 0, NULL, NULL, '2019-10-29 13:00:59', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (77, 18, 2, '上传文件', NULL, '', 2, '', '', 0, 0, 0, 'storage:add', 0, NULL, NULL, '2019-10-29 13:09:09', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (78, 18, 2, '文件编辑', NULL, '', 3, '', '', 0, 0, 0, 'storage:edit', 0, NULL, NULL, '2019-10-29 13:09:22', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (79, 18, 2, '文件删除', NULL, '', 4, '', '', 0, 0, 0, 'storage:del', 0, NULL, NULL, '2019-10-29 13:09:34', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (80, 6, 1, '服务监控', 'ServerMonitor', 'monitor/server/index', 14, 'codeConsole', 'server', 0, 0, 0, 'monitor:list', 0, NULL, 'admin', '2019-11-07 13:06:39', '2020-05-04 18:20:50');
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (82, 36, 1, '生成配置', 'GeneratorConfig', 'generator/config', 3, 'dev', 'generator/config/:tableName', 0, 1, 1, '', 0, NULL, 'admin', '2019-11-17 20:08:56', '2022-04-25 18:19:17');
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (83, 10, 1, '图表库', 'Echarts', 'components/Echarts', 50, 'chart', 'echarts', 0, 1, 0, NULL, 0, NULL, NULL, '2019-11-21 09:04:32', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (116, 36, 1, '生成预览', 'Preview', 'generator/preview', 4, 'java', 'generator/preview/:tableName', 0, 1, 1, '', 0, NULL, 'admin', '2019-11-26 14:54:36', '2022-04-25 18:19:25');
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (122, 41, 2, '强退用户', NULL, NULL, 1, NULL, NULL, 0, 0, 0, 'online:del', 0, 'admin', '', '2022-04-20 17:52:10', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (123, 7, 2, '清空日志', NULL, NULL, 999, NULL, NULL, 0, 0, 0, 'log:del', 0, 'admin', NULL, '2022-04-21 11:53:40', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (136, 36, 1, '邮件工具', 'EmailConfig', 'tools/email/index', 5, 'email', 'email', 0, 0, 0, 'sysEmailConfig:list', 1, 'admin', 'admin', '2022-04-25 17:01:35', '2022-04-25 18:19:35');
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (137, 136, 2, '新增配置', '', '', 0, '', '', 0, 0, 0, 'sysEmailConfig:add', 0, 'admin', 'admin', '2022-04-25 17:23:01', '2022-04-25 17:23:27');
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (138, 136, 2, '删除配置', NULL, NULL, 1, NULL, NULL, 0, 0, 0, 'sysEmailConfig:del', 0, 'admin', NULL, '2022-04-25 17:23:18', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (139, 136, 2, '修改配置', NULL, NULL, 2, NULL, NULL, 0, 0, 0, 'sysEmailConfig:edit', 0, 'admin', NULL, '2022-04-25 17:53:19', NULL);
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (140, 36, 1, '发送邮件', 'SendEmail', 'tools/email/send', 6, 'develop', 'email/sendEmail/:emailId', 0, 1, 1, '', 1, 'admin', 'admin', '2022-04-25 18:11:25', '2022-04-26 10:44:56');
INSERT INTO `sys_menu` (`menu_id`, `pid`, `menu_type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `is_cache`, `hidden`, `permission`, `has_children`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (141, 140, 2, '发送邮件', NULL, NULL, 0, NULL, NULL, 0, 0, 0, 'sysEmailConfig:send', 0, 'admin', NULL, '2022-04-26 10:44:56', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `level` int(2) DEFAULT NULL COMMENT '角色级别',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `data_scope` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据权限',
  `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建操作人',
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新操作人',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`role_id`, `name`, `level`, `description`, `data_scope`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (1, '超级管理员', 1, '-', 'ALL', 'admin', 'admin', '2018-11-23 11:04:37', '2020-08-06 16:10:24');
INSERT INTO `sys_role` (`role_id`, `name`, `level`, `description`, `data_scope`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (2, '普通用户', 2, '-', 'CURRENT', 'admin', 'admin', '2018-11-23 13:09:06', '2020-09-05 10:45:12');
INSERT INTO `sys_role` (`role_id`, `name`, `level`, `description`, `data_scope`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (3, 'zsda', 3, 'asdasdas', 'CURRENT', NULL, 'admin', NULL, NULL);
INSERT INTO `sys_role` (`role_id`, `name`, `level`, `description`, `data_scope`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (5, '比你们', 2, NULL, 'CURRENT', NULL, NULL, NULL, NULL);
INSERT INTO `sys_role` (`role_id`, `name`, `level`, `description`, `data_scope`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (6, 'asdad', 3, 'asdasd', 'CURRENT', NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `dept_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`dept_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色部门中间表';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_dept` (`dept_id`, `role_id`) VALUES (7, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_roles_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_menus`;
CREATE TABLE `sys_roles_menus` (
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`menu_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='角色菜单关联';

-- ----------------------------
-- Records of sys_roles_menus
-- ----------------------------
BEGIN;
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (1, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (1, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (2, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (2, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (3, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (3, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (5, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (5, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (6, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (6, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (7, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (7, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (10, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (15, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (18, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (30, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (32, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (32, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (33, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (34, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (35, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (35, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (36, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (37, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (37, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (39, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (39, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (41, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (41, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (44, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (44, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (45, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (45, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (46, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (46, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (48, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (48, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (49, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (49, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (50, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (50, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (52, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (52, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (53, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (53, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (54, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (54, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (56, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (56, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (57, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (57, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (58, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (58, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (60, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (60, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (61, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (61, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (62, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (62, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (64, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (64, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (65, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (65, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (66, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (66, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (77, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (78, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (79, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (80, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (80, 3);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (82, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (83, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (92, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (93, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (94, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (97, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (98, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (102, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (103, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (104, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (105, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (106, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (107, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (108, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (109, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (110, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (111, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (112, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (113, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (114, 1);
INSERT INTO `sys_roles_menus` (`menu_id`, `role_id`) VALUES (116, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门id',
  `job_id` int(11) DEFAULT NULL COMMENT '岗位id',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户名',
  `nick_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '昵称',
  `gender` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '性别',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机号码',
  `email` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `avatar_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像地址',
  `avatar_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像磁盘路径',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
  `is_admin` tinyint(1) DEFAULT '0' COMMENT '是否超管',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `pwd_reset_time` datetime DEFAULT NULL COMMENT '修改密码的时间',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建操作人',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '更新操作人',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `idx_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`user_id`, `dept_id`, `job_id`, `username`, `nick_name`, `gender`, `phone`, `email`, `avatar_name`, `avatar_path`, `password`, `is_admin`, `enabled`, `pwd_reset_time`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (1, 5, 14, 'admin', '管理员', '男', '18888888888', 'dfdsfsdf@ass.com', 'avatar-20211224161948245.png', '/Users/chaixuhong/personal/outerProject/czadmin/avatar/avatar-20211224161948245.png', '$2a$10$vXjo9NGLMwK5.JqIpKrc/OKS41QO1.rvWK/6S5bhmAECklguc9Lw.', 1, 1, '2020-05-03 16:38:31', NULL, 'admin', '2018-08-23 09:11:56', '2022-03-24 10:45:20');
INSERT INTO `sys_user` (`user_id`, `dept_id`, `job_id`, `username`, `nick_name`, `gender`, `phone`, `email`, `avatar_name`, `avatar_path`, `password`, `is_admin`, `enabled`, `pwd_reset_time`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (2, 2, 10, 'test', '测试', '男', '19999999999', '231@qq.com', NULL, NULL, '$2a$10$4XcyudOYTSz6fue6KFNMHeUQnCX5jbBQypLEnGk1PmekXt5c95JcK', 0, 1, NULL, 'admin', 'admin', '2020-05-05 11:15:49', '2022-03-24 18:24:33');
INSERT INTO `sys_user` (`user_id`, `dept_id`, `job_id`, `username`, `nick_name`, `gender`, `phone`, `email`, `avatar_name`, `avatar_path`, `password`, `is_admin`, `enabled`, `pwd_reset_time`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (6, 20, 10, 'asd', 'adasd', '男', '15718888888', 'asd@qq.com', NULL, NULL, '$2a$10$KFftCzYVhB63XuLQVFkW3e4BJszcffi2C2csqhlG6wkODkold/W8m', 0, 1, NULL, 'admin', 'admin', '2022-03-18 17:08:26', '2022-03-24 10:23:00');
COMMIT;

-- ----------------------------
-- Table structure for sys_users_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_roles`;
CREATE TABLE `sys_users_roles` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='用户角色关联';

-- ----------------------------
-- Records of sys_users_roles
-- ----------------------------
BEGIN;
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (2, 2);
INSERT INTO `sys_users_roles` (`user_id`, `role_id`) VALUES (6, 2);
COMMIT;

-- ----------------------------
-- View structure for tables_info
-- ----------------------------
DROP VIEW IF EXISTS `tables_info`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `tables_info` AS select `tables`.`TABLE_NAME` AS `table_name`,`tables`.`CREATE_TIME` AS `create_time`,`tables`.`ENGINE` AS `engine`,`tables`.`TABLE_COLLATION` AS `coding`,`tables`.`TABLE_COMMENT` AS `remark` from `information_schema`.`TABLES` where ((`tables`.`TABLE_SCHEMA` = (select database())) and (`tables`.`TABLE_TYPE` = 'BASE TABLE'));

SET FOREIGN_KEY_CHECKS = 1;
