CREATE TABLE `medical_auscultation` (
  `mid` int(10) NOT NULL AUTO_INCREMENT,
  `videoUrl` varchar(512) NOT NULL COMMENT '音频地址',
  `content` text NOT NULL COMMENT '音频内容',
  `uid` int(10) NOT NULL COMMENT '对应user_information中的uid',
  `operTime` varchar(32) NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='就医听诊表'