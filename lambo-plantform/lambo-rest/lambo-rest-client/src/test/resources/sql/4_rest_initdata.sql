-- ----------------------------
-- Records of test_data_table
-- ----------------------------

INSERT INTO `test_data_table` VALUES ('1', 'tom', '1');
INSERT INTO `test_data_table` VALUES ('2', 'jack', '2');
INSERT INTO `test_data_table` VALUES ('3', 'wang', '1');


-- ----------------------------
-- Records of rest_setting
-- ----------------------------

INSERT INTO `rest_setting` VALUES ('02abc090802d4db38718ec9cf5479d99', 'xxxxx测试服务', '/aaa/bbb', 'selectList', 'masterDataSource', 'select * from test_data_table where id = #{id}', '[{"SEX":"1","ID":"1","NAME":"tom"}]', '这是一个测试服务', '2018-5-14 16:46:36', '2018-5-14 16:46:36', 'admin');
-- ---------------------------------
-- Records of rest_setting_params
-- ---------------------------------

INSERT INTO `rest_setting_params` VALUES ('02abc090802d4db38718ec9cf5479d99', 'id', '编码', '1', '1');

