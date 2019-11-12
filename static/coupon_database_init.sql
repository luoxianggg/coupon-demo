DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户名',
  `icon` varchar(500) DEFAULT NULL COMMENT '图标',
  `employee_id` int(11) DEFAULT NULL COMMENT '员工id',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `encry_password` int(500) DEFAULT NULL COMMENT 'MD5加密密码',
  `enable_flag` int(2) DEFAULT NULL comment '是否启用',
  `creation_data` datetime DEFAULT NULL,
  `lasted_update_date` datetime DEFAULT null,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
INSERT INTO `sys_user`(user_id,user_name,password,enable_flag) VALUES ('1001', 'admin', 'admin', '1');

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