-- 登录 MySQL 服务器
mysql -hlocalhost -uroot -pDjangobbs

-- 创建数据库 imooc_coupon_data
CREATE DATABASE IF NOT EXISTS coupon_db;

-- 登录 MySQL 服务器, 并进入到 imooc_coupon_data 数据库中
mysql -hlocalhost -uroot -proot -Dcoupon_db

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户名',
  `icon` varchar(500) DEFAULT NULL COMMENT '图标',
  `employee_id` int(11) DEFAULT NULL COMMENT '员工id',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `encry_password` varchar(200) DEFAULT NULL COMMENT 'MD5加密密码',
  `enable_flag` int(1) DEFAULT NULL comment '是否启用 1:启用 0 不启用',
  `creation_data` datetime DEFAULT NULL,
  `lasted_update_date` datetime DEFAULT null,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

DROP TABLE IF EXISTS `sys_roles`;
CREATE TABLE `sys_roles` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT'角色名',
  `role_code` varchar(500) DEFAULT NULL COMMENT '角色代码',
  `enable_flag` int(2) DEFAULT NULL comment '是否启用',
  `creation_data` datetime DEFAULT NULL,
  `lasted_update_date` datetime DEFAULT null,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

DROP TABLE IF EXISTS `sys_user_roles`;
CREATE TABLE `sys_user_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `enable_flag` int(2) DEFAULT NULL comment '是否启用',
  `creation_data` datetime DEFAULT NULL,
  `lasted_update_date` datetime DEFAULT null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';
DROP TABLE IF EXISTS `sys_user_logging_record`;
CREATE TABLE `sys_user_logging_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `ip` varchar(20) DEFAULT NULL comment '是否启用',
  `logging_date` datetime DEFAULT NULL comment '是否启用',
  `logging_source` varchar(50) DEFAULT NULL comment '是否启用',
  `creation_data` datetime DEFAULT NULL,
  `lasted_update_date` datetime DEFAULT null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';
DROP TABLE IF EXISTS `sys_function`;
CREATE TABLE `sys_function` (
  `function_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `function_name` bigint(50) not NULL COMMENT '角色id',
  `function_type` varchar(20) DEFAULT NULL COMMENT '用户id',
  `icon` varchar(50) DEFAULT NULL COMMENT '用户id',
  `enable_flag` int(1) DEFAULT NULL comment '是否启用',
  `creation_data` datetime DEFAULT NULL,
  `lasted_update_date` datetime DEFAULT null,
  PRIMARY KEY (`function_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统功能表';

DROP TABLE IF EXISTS `sys_role_function`;
CREATE TABLE `sys_role_function` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `function_id` bigint(20) NOT NULL comment '功能id',
  `role_id` bigint(50) not NULL COMMENT '角色id',
  `enable_flag` int(1) DEFAULT NULL comment '是否启用',
  `creation_data` datetime DEFAULT NULL,
  `lasted_update_date` datetime DEFAULT null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色功能明细表';

-- 创建 coupon_template 数据表
DROP TABLE IF EXISTS `coupon_template`
CREATE TABLE  `coupon_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `available` boolean NOT NULL DEFAULT false COMMENT '是否是可用状态; true: 可用, false: 不可用',
  `expired` boolean NOT NULL DEFAULT false COMMENT '是否过期; true: 是, false: 否',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '优惠券名称',
  `logo` varchar(256) NOT NULL DEFAULT '' COMMENT '优惠券 logo',
  `intro` varchar(256) NOT NULL DEFAULT '' COMMENT '优惠券描述',
  `category` varchar(64) NOT NULL DEFAULT '' COMMENT '优惠券分类',
  `product_line` int(11) NOT NULL DEFAULT '0' COMMENT '产品线',
  `coupon_count` int(11) NOT NULL DEFAULT '0' COMMENT '总数',
  `create_time` datetime NOT NULL DEFAULT '0000-01-01 00:00:00' COMMENT '创建时间',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建用户',
  `template_key` varchar(128) NOT NULL DEFAULT '' COMMENT '优惠券模板的编码',
  `target` int(11) NOT NULL DEFAULT '0' COMMENT '目标用户',
  `rule` varchar(1024) NOT NULL DEFAULT '' COMMENT '优惠券规则: TemplateRule 的 json 表示',
  PRIMARY KEY (`id`),
  KEY `idx_category` (`category`),
  KEY `idx_user_id` (`user_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='优惠券模板表';
