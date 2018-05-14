DROP TABLE IF EXISTS `rest_setting`;
CREATE TABLE `rest_setting` (
  `rest_id` varchar(50) NOT NULL,
  `rest_name` varchar(50) DEFAULT NULL,
  `url` varchar(100) NOT NULL,
  `operation_type` varchar(20) NOT NULL,
  `datasource` varchar(50) NOT NULL,
  `rest_sql` text,
  `mock_data` text,
  `note` varchar(200) DEFAULT NULL,
  `create_time` datetime NOT NULL ,
  `update_time` datetime NOT NULL ,
  `create_user` varchar(50) NOT NULL,
  PRIMARY KEY (`rest_id`)
) ;


DROP TABLE IF EXISTS `rest_setting_params`;
CREATE TABLE `rest_setting_params` (
  `rest_id` varchar(50) NOT NULL,
  `param_key` varchar(50) NOT NULL,
  `param_name` varchar(50) NOT NULL,
  `necessary` char(1) NOT NULL,
  `default_value` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`rest_id`,`param_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `test_data_table`;
CREATE TABLE `test_data_table` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `sex` varchar(50) NOT NULL
);


