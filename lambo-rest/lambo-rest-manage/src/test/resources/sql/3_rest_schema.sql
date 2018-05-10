DROP TABLE IF EXISTS `rest_setting`;
CREATE TABLE `rest_setting` (
  `rest_id` varchar(50) NOT NULL,
  `rest_name` varchar(50) DEFAULT NULL,
  `url` varchar(100) NOT NULL,
  `datasource` varchar(50) NOT NULL,
  `rest_sql` text,
  `mock_data` text,
  `note` varchar(200) DEFAULT NULL,
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(50) NOT NULL,
  PRIMARY KEY (`rest_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;