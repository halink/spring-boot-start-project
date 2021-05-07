CREATE TABLE `p_user`
(
    `user_id`       int(11) unsigned NOT NULL AUTO_INCREMENT,
    `username`      varchar(20)      NOT NULL DEFAULT '' COMMENT '用户名',
    `password`      varchar(20)      NOT NULL DEFAULT '' COMMENT '密码',
    `nick_name`     varchar(20)      NOT NULL DEFAULT '' COMMENT '昵称',
    `area_code`     varchar(20)               DEFAULT NULL COMMENT '区号',
    `phone`         varchar(20)               DEFAULT NULL COMMENT '手机号',
    `photo`         varchar(255)              DEFAULT NULL COMMENT '头像',
    `gender`        tinyint(4) unsigned       DEFAULT '0' COMMENT '性别 0.未知 1.男 2.女',
    `birthday`      date                      DEFAULT NULL COMMENT '出生年月日',
    `created_at`    timestamp        NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`    timestamp        NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `last_login_at` timestamp        NULL     DEFAULT NULL COMMENT '最后一次登录时间',
    PRIMARY KEY (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='系统-用户信息表';