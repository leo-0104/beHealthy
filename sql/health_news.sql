CREATE TABLE `health_news` (
  `nid` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) NOT NULL COMMENT '标题',
  `author` varchar(16) NOT NULL COMMENT '作者名字',
  `coverUrl` varchar(512) NOT NULL COMMENT '封面截图',
  `status` tinyint(1) DEFAULT '1' COMMENT '1:启用,0:停用',
  `isTop` tinyint(1) DEFAULT '0' COMMENT '置顶 1:是，0:否',
  `content` text NOT NULL COMMENT '资讯内容',
  `operTime` varchar(32) NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`nid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='健康资讯信息表'