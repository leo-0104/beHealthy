CREATE TABLE `user_information` (
  `uid` int(10) NOT NULL AUTO_INCREMENT,
  `accountName` varchar(32) NOT NULL COMMENT '账号名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `gender` tinyint(1) DEFAULT '1' COMMENT '1:男生,0:女生',
  `birthday` varchar(32) DEFAULT NULL COMMENT '出生日期',
  `profile` varchar(512) DEFAULT NULL COMMENT '图像路径',
  `phone` varchar(16) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `registerTime` varchar(32) DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `accountName` (`accountName`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表'