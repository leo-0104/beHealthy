CREATE TABLE `family_member` (
  `fid` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '姓名',
  `profile` varchar(512) DEFAULT NULL COMMENT '头像路径',
  `gender` tinyint(1) DEFAULT '1' COMMENT '1:男生,0:女生',
  `birthday` varchar(32) DEFAULT NULL COMMENT '出生日期',
  `relation` varchar(16) DEFAULT NULL COMMENT '关系',
  `uid` int(10) NOT NULL COMMENT '对应user_information中的uid',
  `operTime` varchar(32) NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='家庭成员信息表'