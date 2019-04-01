CREATE TABLE `health_record` (
  `hid` int(10) NOT NULL AUTO_INCREMENT,
  `date` varchar(32) NOT NULL COMMENT '日期',
  `time` varchar(32) NOT NULL COMMENT '时间',
  `food` varchar(1024) DEFAULT NULL COMMENT '食物/饮料摄入描述',
  `sport` varchar(1024) DEFAULT NULL COMMENT '运动情况描述',
  `symptom` varchar(1024) NOT NULL COMMENT '症状描述',
  `uid` int(10) NOT NULL COMMENT '对应user_information中的uid',
  `operTime` varchar(32) NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`hid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='康复记录表'