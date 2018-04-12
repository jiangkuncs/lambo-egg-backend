/*
Navicat MySQL Data Transfer

Source Server         : 10.10.10.136
Source Server Version : 50711
Source Host           : 10.10.10.136:3306
Source Database       : lambo

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2018-04-10 10:04:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ndp_category`
-- ----------------------------
DROP TABLE IF EXISTS `ndp_category`;
CREATE TABLE `ndp_category` (
  `CATEGORY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CATEGORY_NAME` varchar(50) NOT NULL,
  `CATEGORY_DESC` varchar(100) DEFAULT NULL,
  `CREATE_USER` varchar(30) DEFAULT NULL,
  `CREATE_TIME` char(19) DEFAULT NULL,
  PRIMARY KEY (`CATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ndp_category
-- ----------------------------
INSERT INTO `ndp_category` VALUES ('1', '销售', '销售', 'admin', '2018-03-21 09:02:10');
INSERT INTO `ndp_category` VALUES ('2', '客户', '客户', 'admin', '2018-03-21 09:02:11');
INSERT INTO `ndp_category` VALUES ('3', '品牌', '品牌', 'admin', '2018-03-21 09:02:13');
INSERT INTO `ndp_category` VALUES ('4', '物流', '物流', 'admin', '2018-03-23 17:37:49');
INSERT INTO `ndp_category` VALUES ('5', '网建', '网建', 'admin', '2018-03-21 09:03:56');
INSERT INTO `ndp_category` VALUES ('10', '社会经济', '社会经济', 'admin', '2018-03-21 09:03:48');

-- ----------------------------
-- Table structure for `ndp_dict`
-- ----------------------------
DROP TABLE IF EXISTS `ndp_dict`;
CREATE TABLE `ndp_dict` (
  `DICT_ID` varchar(50) NOT NULL,
  `DICT_NAME` varchar(50) NOT NULL,
  `DICT_DESC` varchar(100) DEFAULT NULL,
  `DICT_KEY` varchar(50) NOT NULL,
  `DICT_VALUE` varchar(50) NOT NULL,
  PRIMARY KEY (`DICT_ID`,`DICT_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ndp_dict
-- ----------------------------
INSERT INTO `ndp_dict` VALUES ('1', '性别', null, '0', '女');
INSERT INTO `ndp_dict` VALUES ('1', '性别', null, '1', '男');
INSERT INTO `ndp_dict` VALUES ('2', '订单状态', '', '1', '新建');
INSERT INTO `ndp_dict` VALUES ('PLM_TYPE', '商品生命周期类型', '商品生命周期类型', '00', '试销');
INSERT INTO `ndp_dict` VALUES ('PLM_TYPE', '商品生命周期类型', '商品生命周期类型', '01', '新品');
INSERT INTO `ndp_dict` VALUES ('PLM_TYPE', '商品生命周期类型', '商品生命周期类型', '02', '成长');
INSERT INTO `ndp_dict` VALUES ('PLM_TYPE', '商品生命周期类型', '商品生命周期类型', '03', '成熟');
INSERT INTO `ndp_dict` VALUES ('PLM_TYPE', '商品生命周期类型', '商品生命周期类型', '04', '衰退');
INSERT INTO `ndp_dict` VALUES ('PLM_TYPE', '商品生命周期类型', '商品生命周期类型', '05', '退网');

-- ----------------------------
-- Table structure for `ndp_dimension`
-- ----------------------------
DROP TABLE IF EXISTS `ndp_dimension`;
CREATE TABLE `ndp_dimension` (
  `DIMENSION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `DIMENSION_NAME` varchar(50) DEFAULT NULL,
  `REF_TABLE` varchar(100) DEFAULT NULL,
  `KEY_FIELD` varchar(50) DEFAULT NULL,
  `NAME_FIELD` varchar(50) DEFAULT NULL,
  `NAME_FIELD_ZH_CN` varchar(50) DEFAULT NULL,
  `SHOW_FIELD` varchar(200) DEFAULT NULL,
  `SHOW_FIELD_ZH_CN` varchar(200) DEFAULT NULL,
  `DIMENSION_TYPE` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`DIMENSION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ndp_dimension
-- ----------------------------
INSERT INTO `ndp_dimension` VALUES ('1', '省公司', 'PL_COM', 'COM_ID', 'COM_NAME', '公司名称', 'COM_TEST,COM_TEST1', '测试字段,测试字段1', 'province');
INSERT INTO `ndp_dimension` VALUES ('2', '商品', 'PL_ITEM', 'ITEM_ID', 'ITEM_NAME', '商品名称', null, null, 'item');
INSERT INTO `ndp_dimension` VALUES ('3', '品牌', 'PL_TEST', 'TEST_ID', 'TEST_NAME', '品牌名称', null, null, 'brand');
INSERT INTO `ndp_dimension` VALUES ('4', '日期', null, null, null, null, null, null, 'year');
INSERT INTO `ndp_dimension` VALUES ('5', '日期', null, null, null, null, null, null, 'month');
INSERT INTO `ndp_dimension` VALUES ('6', '日期', null, null, null, null, null, null, 'date');
INSERT INTO `ndp_dimension` VALUES ('7', '市公司', 'PL_COM', 'COM_ID', 'COM_NAME', '公司名称', null, null, 'city');

-- ----------------------------
-- Table structure for `ndp_subject`
-- ----------------------------
DROP TABLE IF EXISTS `ndp_subject`;
CREATE TABLE `ndp_subject` (
  `SUBJECT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CATEGORY_ID` int(11) NOT NULL,
  `SUBJECT_NAME` varchar(50) NOT NULL,
  `SUBJECT_DESC` varchar(100) DEFAULT NULL,
  `TABLE_ID` int(11) NOT NULL,
  `TABLE_CODE` varchar(50) NOT NULL,
  `CREATE_TIME` char(19) DEFAULT NULL,
  PRIMARY KEY (`SUBJECT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ndp_subject
-- ----------------------------
INSERT INTO `ndp_subject` VALUES ('1', '1', '商品月度销售', '商品月度销售，商品月度销售，商品月度销售。商品月度销售，商品月度销售，商品月度销售。商品月度销售，商品月度销售，商品月度销售。商品月度销售，商品月度销售，商品月度销售。', '1', 'DEMO_USER', '2018-03-21 16:42:59');
INSERT INTO `ndp_subject` VALUES ('2', '2', '商品月度销售2', null, '1', 'DEMO_USER', null);
INSERT INTO `ndp_subject` VALUES ('3', '1', '商品年度销售', null, '1', 'DEMO_USER', '2018-03-21 16:43:12');
INSERT INTO `ndp_subject` VALUES ('4', '1', '商品年度销售22222222222222222222222222222222222', null, '1', 'DEMO_USER', '2018-03-21 16:43:04');
INSERT INTO `ndp_subject` VALUES ('5', '17', '测速', '测速', '33', 'ndp_subject_column', null);
INSERT INTO `ndp_subject` VALUES ('7', '5', '123', '123', '32', 'pl_item', null);
INSERT INTO `ndp_subject` VALUES ('8', '5', 'wangjian', '网监', '1', 'DEMO_USER', '2018-03-21 16:43:08');
INSERT INTO `ndp_subject` VALUES ('14', '3', '123', '321', '1', 'DEMO_USER', null);
INSERT INTO `ndp_subject` VALUES ('15', '1', '公司商品', '公司商品', '36', 'plm_item_com', '2018-03-21 16:42:24');
INSERT INTO `ndp_subject` VALUES ('16', '5', '1111', '1111', '36', 'plm_item_com', null);
INSERT INTO `ndp_subject` VALUES ('17', '2', '测试', '试试', '36', 'plm_item_com', '2018-03-22 16:51:04');
INSERT INTO `ndp_subject` VALUES ('18', '4', '物流商品', '物流商品', '36', 'plm_item_com', '2018-03-22 19:53:34');

-- ----------------------------
-- Table structure for `ndp_subject_column`
-- ----------------------------
DROP TABLE IF EXISTS `ndp_subject_column`;
CREATE TABLE `ndp_subject_column` (
  `COLUMN_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SUBJECT_ID` int(11) NOT NULL,
  `COLUMN_NAME` varchar(50) NOT NULL,
  `CELL_ID` int(11) NOT NULL,
  `SEARCH_CONDITION` varchar(30) DEFAULT NULL,
  `SEARCH_SETTING` varchar(100) DEFAULT NULL,
  `COLUMN_ORDER` int(11) DEFAULT NULL,
  `IS_SHOW` char(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`COLUMN_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ndp_subject_column
-- ----------------------------
INSERT INTO `ndp_subject_column` VALUES ('10', '14', '主键', '23', '1', null, null, '1');
INSERT INTO `ndp_subject_column` VALUES ('11', '14', '名称', '24', '4', null, null, '1');
INSERT INTO `ndp_subject_column` VALUES ('12', '14', '年龄', '25', null, null, null, '1');
INSERT INTO `ndp_subject_column` VALUES ('13', '14', '性别', '26', null, null, null, '1');
INSERT INTO `ndp_subject_column` VALUES ('14', '14', '公司', '27', null, null, null, '1');
INSERT INTO `ndp_subject_column` VALUES ('15', '14', '商品', '28', null, null, null, '1');
INSERT INTO `ndp_subject_column` VALUES ('16', '14', '创建时间', '29', null, null, null, '1');
INSERT INTO `ndp_subject_column` VALUES ('24', '15', '公司编码', '35', null, null, null, '1');
INSERT INTO `ndp_subject_column` VALUES ('25', '15', '商品编码', '36', 'item', null, null, '1');
INSERT INTO `ndp_subject_column` VALUES ('26', '15', '商品生命周期类型', '37', null, null, null, '1');
INSERT INTO `ndp_subject_column` VALUES ('27', '15', '工商调拨价', '38', null, null, null, '1');
INSERT INTO `ndp_subject_column` VALUES ('28', '15', '商商调拨价格', '39', null, null, null, '0');
INSERT INTO `ndp_subject_column` VALUES ('29', '15', '批发价格', '40', null, null, null, '1');
INSERT INTO `ndp_subject_column` VALUES ('30', '15', '建议零售价', '41', null, null, null, '1');
INSERT INTO `ndp_subject_column` VALUES ('31', '1', '主键', '23', null, null, '1', '0');
INSERT INTO `ndp_subject_column` VALUES ('32', '1', '名称', '24', null, null, '2', '1');
INSERT INTO `ndp_subject_column` VALUES ('33', '1', '性别', '26', null, null, '3', '1');
INSERT INTO `ndp_subject_column` VALUES ('34', '1', '年龄', '25', null, null, '4', '1');
INSERT INTO `ndp_subject_column` VALUES ('35', '1', '公司', '27', 'province', null, '5', '1');
INSERT INTO `ndp_subject_column` VALUES ('36', '1', '商品', '28', 'item', '', '6', '1');
INSERT INTO `ndp_subject_column` VALUES ('37', '1', '创建时间', '29', 'year', '', '7', '1');
INSERT INTO `ndp_subject_column` VALUES ('38', '17', '公司编码', '35', null, null, '0', '1');
INSERT INTO `ndp_subject_column` VALUES ('39', '17', '商品编码', '36', null, null, '1', '1');
INSERT INTO `ndp_subject_column` VALUES ('40', '17', '商品生命周期类型', '37', null, null, '2', '1');
INSERT INTO `ndp_subject_column` VALUES ('41', '17', '工商调拨价', '38', null, null, '3', '1');
INSERT INTO `ndp_subject_column` VALUES ('42', '17', '商商调拨价格', '39', null, null, '4', '1');
INSERT INTO `ndp_subject_column` VALUES ('43', '17', '批发价格', '40', null, null, '5', '1');
INSERT INTO `ndp_subject_column` VALUES ('44', '17', '建议零售价', '41', null, null, '6', '1');
INSERT INTO `ndp_subject_column` VALUES ('59', '18', '公司编码', '35', null, null, '1', '1');
INSERT INTO `ndp_subject_column` VALUES ('60', '18', '工商调拨价', '38', null, null, '1', '1');
INSERT INTO `ndp_subject_column` VALUES ('61', '18', '商品编码', '36', null, null, '2', '1');
INSERT INTO `ndp_subject_column` VALUES ('62', '18', '商品生命周期类型', '37', null, null, '3', '1');
INSERT INTO `ndp_subject_column` VALUES ('63', '18', '商商调拨价格', '39', null, null, '5', '1');
INSERT INTO `ndp_subject_column` VALUES ('64', '18', '批发价格', '40', null, null, '6', '1');
INSERT INTO `ndp_subject_column` VALUES ('65', '18', '建议零售价', '41', null, null, '1', '1');

-- ----------------------------
-- Table structure for `ndp_subject_record`
-- ----------------------------
DROP TABLE IF EXISTS `ndp_subject_record`;
CREATE TABLE `ndp_subject_record` (
  `record_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `create_time` char(19) DEFAULT NULL,
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ndp_subject_record
-- ----------------------------
INSERT INTO `ndp_subject_record` VALUES ('1', 'admin', '1', '1', '2018-03-22 15:37:11');
INSERT INTO `ndp_subject_record` VALUES ('2', 'admin', '1', '1', '2018-03-22 15:45:27');
INSERT INTO `ndp_subject_record` VALUES ('3', 'admin', '3', '1', '2018-03-22 15:45:50');
INSERT INTO `ndp_subject_record` VALUES ('4', 'admin', '4', '1', '2018-03-22 15:45:51');
INSERT INTO `ndp_subject_record` VALUES ('5', 'admin', '15', '1', '2018-03-22 15:45:52');
INSERT INTO `ndp_subject_record` VALUES ('6', 'admin', '1', '1', '2018-03-22 15:45:53');
INSERT INTO `ndp_subject_record` VALUES ('7', 'admin', '3', '1', '2018-03-22 15:45:54');
INSERT INTO `ndp_subject_record` VALUES ('8', 'admin', '4', '1', '2018-03-22 15:45:55');
INSERT INTO `ndp_subject_record` VALUES ('9', 'admin', '15', '1', '2018-03-22 15:45:56');
INSERT INTO `ndp_subject_record` VALUES ('10', 'admin', '1', '1', '2018-03-22 15:45:57');
INSERT INTO `ndp_subject_record` VALUES ('11', 'admin', '1', '1', '2018-03-22 15:48:54');
INSERT INTO `ndp_subject_record` VALUES ('12', 'admin', '3', '1', '2018-03-22 16:08:55');
INSERT INTO `ndp_subject_record` VALUES ('13', 'admin', '1', '1', '2018-03-22 16:08:57');
INSERT INTO `ndp_subject_record` VALUES ('14', 'admin', '1', '1', '2018-03-22 16:10:56');
INSERT INTO `ndp_subject_record` VALUES ('15', 'admin', '1', '1', '2018-03-22 16:42:13');
INSERT INTO `ndp_subject_record` VALUES ('16', 'admin', '1', '1', '2018-03-22 17:02:42');
INSERT INTO `ndp_subject_record` VALUES ('17', 'admin', '1', '1', '2018-03-22 17:02:45');
INSERT INTO `ndp_subject_record` VALUES ('18', 'admin', '1', '1', '2018-03-23 08:39:18');
INSERT INTO `ndp_subject_record` VALUES ('19', 'admin', '1', '1', '2018-03-23 08:39:37');
INSERT INTO `ndp_subject_record` VALUES ('20', 'admin', '1', '1', '2018-03-23 08:39:53');
INSERT INTO `ndp_subject_record` VALUES ('21', 'admin', '3', '1', '2018-03-23 08:39:54');
INSERT INTO `ndp_subject_record` VALUES ('22', 'admin', '1', '1', '2018-03-23 08:39:55');
INSERT INTO `ndp_subject_record` VALUES ('23', 'admin', '1', '1', '2018-03-23 08:40:34');
INSERT INTO `ndp_subject_record` VALUES ('24', 'admin', '1', '1', '2018-03-23 08:40:36');
INSERT INTO `ndp_subject_record` VALUES ('25', 'admin', '1', '1', '2018-03-23 08:45:03');
INSERT INTO `ndp_subject_record` VALUES ('26', 'admin', '3', '1', '2018-03-23 08:45:05');
INSERT INTO `ndp_subject_record` VALUES ('27', 'admin', '1', '1', '2018-03-23 08:45:05');
INSERT INTO `ndp_subject_record` VALUES ('28', 'admin', '1', '1', '2018-03-23 08:45:09');
INSERT INTO `ndp_subject_record` VALUES ('29', 'admin', '1', '1', '2018-03-23 08:45:11');
INSERT INTO `ndp_subject_record` VALUES ('30', 'admin', '1', '1', '2018-03-23 08:45:13');
INSERT INTO `ndp_subject_record` VALUES ('31', 'admin', '3', '1', '2018-03-23 08:45:15');
INSERT INTO `ndp_subject_record` VALUES ('32', 'admin', '1', '1', '2018-03-23 08:45:16');
INSERT INTO `ndp_subject_record` VALUES ('33', 'admin', '14', '3', '2018-03-23 08:45:20');
INSERT INTO `ndp_subject_record` VALUES ('34', 'admin', '2', '2', '2018-03-23 08:45:21');
INSERT INTO `ndp_subject_record` VALUES ('35', 'admin', '17', '2', '2018-03-23 08:45:22');
INSERT INTO `ndp_subject_record` VALUES ('36', 'admin', '1', '1', '2018-03-23 08:45:24');
INSERT INTO `ndp_subject_record` VALUES ('37', 'admin', '3', '1', '2018-03-23 08:45:42');
INSERT INTO `ndp_subject_record` VALUES ('38', 'admin', '1', '1', '2018-03-23 08:45:43');
INSERT INTO `ndp_subject_record` VALUES ('39', 'admin', '3', '1', '2018-03-23 08:45:44');
INSERT INTO `ndp_subject_record` VALUES ('40', 'admin', '1', '1', '2018-03-23 08:45:45');
INSERT INTO `ndp_subject_record` VALUES ('41', 'admin', '3', '1', '2018-03-23 08:45:45');
INSERT INTO `ndp_subject_record` VALUES ('42', 'admin', '1', '1', '2018-03-23 08:45:46');
INSERT INTO `ndp_subject_record` VALUES ('43', 'admin', '4', '1', '2018-03-23 08:45:47');
INSERT INTO `ndp_subject_record` VALUES ('44', 'admin', '3', '1', '2018-03-23 08:45:49');
INSERT INTO `ndp_subject_record` VALUES ('45', 'admin', '15', '1', '2018-03-23 08:45:50');
INSERT INTO `ndp_subject_record` VALUES ('46', 'admin', '1', '1', '2018-03-23 08:45:52');
INSERT INTO `ndp_subject_record` VALUES ('47', 'admin', '1', '1', '2018-03-23 11:07:17');
INSERT INTO `ndp_subject_record` VALUES ('48', 'admin', '1', '1', '2018-03-23 13:36:24');
INSERT INTO `ndp_subject_record` VALUES ('49', 'admin', '1', '1', '2018-03-23 13:36:37');
INSERT INTO `ndp_subject_record` VALUES ('50', 'admin', '1', '1', '2018-03-23 15:13:49');
INSERT INTO `ndp_subject_record` VALUES ('51', 'admin', '1', '1', '2018-03-24 13:55:11');
INSERT INTO `ndp_subject_record` VALUES ('52', 'admin', '1', '1', '2018-03-24 13:55:15');
INSERT INTO `ndp_subject_record` VALUES ('53', 'admin', '1', '1', '2018-03-24 13:55:18');
INSERT INTO `ndp_subject_record` VALUES ('54', 'admin', '1', '1', '2018-03-24 13:55:21');
INSERT INTO `ndp_subject_record` VALUES ('55', 'admin', '1', '1', '2018-03-24 13:55:24');
INSERT INTO `ndp_subject_record` VALUES ('56', 'admin', '1', '1', '2018-03-24 13:56:10');
INSERT INTO `ndp_subject_record` VALUES ('57', 'admin', '1', '1', '2018-03-24 13:56:14');
INSERT INTO `ndp_subject_record` VALUES ('58', 'admin', '1', '1', '2018-03-24 14:33:33');
INSERT INTO `ndp_subject_record` VALUES ('59', 'admin', '1', '1', '2018-03-26 10:29:27');
INSERT INTO `ndp_subject_record` VALUES ('60', 'admin', '1', '1', '2018-03-26 10:31:36');
INSERT INTO `ndp_subject_record` VALUES ('61', 'admin', '1', '1', '2018-03-26 10:33:09');
INSERT INTO `ndp_subject_record` VALUES ('62', 'admin', '1', '1', '2018-03-26 10:33:18');
INSERT INTO `ndp_subject_record` VALUES ('63', 'admin', '1', '1', '2018-03-26 10:33:48');
INSERT INTO `ndp_subject_record` VALUES ('64', 'admin', '1', '1', '2018-03-26 10:36:40');
INSERT INTO `ndp_subject_record` VALUES ('65', 'admin', '1', '1', '2018-03-26 10:37:37');
INSERT INTO `ndp_subject_record` VALUES ('66', 'admin', '1', '1', '2018-03-26 10:38:15');
INSERT INTO `ndp_subject_record` VALUES ('67', 'admin', '1', '1', '2018-03-26 10:39:43');
INSERT INTO `ndp_subject_record` VALUES ('68', 'admin', '1', '1', '2018-03-26 10:39:56');
INSERT INTO `ndp_subject_record` VALUES ('69', 'admin', '1', '1', '2018-03-26 10:40:36');
INSERT INTO `ndp_subject_record` VALUES ('70', 'admin', '1', '1', '2018-03-26 10:41:06');
INSERT INTO `ndp_subject_record` VALUES ('71', 'admin', '1', '1', '2018-03-26 10:41:21');
INSERT INTO `ndp_subject_record` VALUES ('72', 'admin', '1', '1', '2018-03-26 10:42:45');
INSERT INTO `ndp_subject_record` VALUES ('73', 'admin', '1', '1', '2018-03-26 10:43:33');
INSERT INTO `ndp_subject_record` VALUES ('74', 'admin', '1', '1', '2018-03-26 10:43:40');
INSERT INTO `ndp_subject_record` VALUES ('75', 'admin', '1', '1', '2018-03-26 10:43:55');
INSERT INTO `ndp_subject_record` VALUES ('76', 'admin', '1', '1', '2018-03-26 10:53:14');
INSERT INTO `ndp_subject_record` VALUES ('77', 'admin', '1', '1', '2018-03-26 10:55:46');
INSERT INTO `ndp_subject_record` VALUES ('78', 'admin', '1', '1', '2018-03-26 11:00:20');
INSERT INTO `ndp_subject_record` VALUES ('79', 'admin', '1', '1', '2018-03-26 11:03:54');
INSERT INTO `ndp_subject_record` VALUES ('80', 'admin', '1', '1', '2018-03-26 11:04:30');
INSERT INTO `ndp_subject_record` VALUES ('81', 'admin', '1', '1', '2018-03-26 11:07:37');
INSERT INTO `ndp_subject_record` VALUES ('82', 'admin', '1', '1', '2018-03-26 11:09:35');
INSERT INTO `ndp_subject_record` VALUES ('83', 'admin', '1', '1', '2018-03-26 11:13:14');
INSERT INTO `ndp_subject_record` VALUES ('84', 'admin', '1', '1', '2018-03-26 11:20:56');
INSERT INTO `ndp_subject_record` VALUES ('85', 'admin', '1', '1', '2018-03-26 11:26:07');
INSERT INTO `ndp_subject_record` VALUES ('86', 'admin', '1', '1', '2018-03-26 11:32:24');
INSERT INTO `ndp_subject_record` VALUES ('87', 'admin', '3', '1', '2018-03-26 11:33:02');
INSERT INTO `ndp_subject_record` VALUES ('88', 'admin', '3', '1', '2018-03-26 11:33:06');
INSERT INTO `ndp_subject_record` VALUES ('89', 'admin', '3', '1', '2018-03-26 11:33:38');
INSERT INTO `ndp_subject_record` VALUES ('90', 'admin', '1', '1', '2018-03-26 11:36:14');
INSERT INTO `ndp_subject_record` VALUES ('91', 'admin', '1', '1', '2018-03-26 11:38:58');
INSERT INTO `ndp_subject_record` VALUES ('92', 'admin', '1', '1', '2018-03-26 11:49:33');
INSERT INTO `ndp_subject_record` VALUES ('93', 'admin', '1', '1', '2018-03-26 12:01:43');
INSERT INTO `ndp_subject_record` VALUES ('94', 'admin', '1', '1', '2018-03-26 12:19:28');
INSERT INTO `ndp_subject_record` VALUES ('95', 'admin', '1', '1', '2018-03-26 13:40:55');
INSERT INTO `ndp_subject_record` VALUES ('96', 'admin', '3', '1', '2018-03-26 13:41:04');
INSERT INTO `ndp_subject_record` VALUES ('97', 'admin', '1', '1', '2018-03-26 13:41:19');
INSERT INTO `ndp_subject_record` VALUES ('98', 'admin', '3', '1', '2018-03-26 13:41:41');
INSERT INTO `ndp_subject_record` VALUES ('99', 'admin', '1', '1', '2018-03-26 13:41:42');
INSERT INTO `ndp_subject_record` VALUES ('100', 'admin', '1', '1', '2018-03-26 13:57:49');
INSERT INTO `ndp_subject_record` VALUES ('101', 'admin', '1', '1', '2018-03-26 14:03:49');
INSERT INTO `ndp_subject_record` VALUES ('102', 'admin', '1', '1', '2018-03-26 15:43:33');
INSERT INTO `ndp_subject_record` VALUES ('103', 'admin', '1', '1', '2018-03-26 17:10:25');
INSERT INTO `ndp_subject_record` VALUES ('104', 'admin', '1', '1', '2018-03-26 17:30:19');
INSERT INTO `ndp_subject_record` VALUES ('105', 'admin', '1', '1', '2018-03-26 17:30:30');
INSERT INTO `ndp_subject_record` VALUES ('106', 'admin', '1', '1', '2018-03-26 19:08:52');
INSERT INTO `ndp_subject_record` VALUES ('107', 'admin', '1', '1', '2018-03-27 08:49:27');
INSERT INTO `ndp_subject_record` VALUES ('108', 'admin', '1', '1', '2018-03-27 10:37:51');
INSERT INTO `ndp_subject_record` VALUES ('109', 'admin', '1', '1', '2018-03-27 10:40:20');
INSERT INTO `ndp_subject_record` VALUES ('110', 'admin', '1', '1', '2018-03-27 10:41:16');
INSERT INTO `ndp_subject_record` VALUES ('111', 'admin', '1', '1', '2018-03-27 10:47:39');
INSERT INTO `ndp_subject_record` VALUES ('112', 'admin', '1', '1', '2018-03-27 10:47:55');
INSERT INTO `ndp_subject_record` VALUES ('113', 'admin', '1', '1', '2018-03-27 10:51:51');
INSERT INTO `ndp_subject_record` VALUES ('114', 'admin', '1', '1', '2018-03-27 10:54:02');
INSERT INTO `ndp_subject_record` VALUES ('115', 'admin', '3', '1', '2018-03-27 10:54:46');
INSERT INTO `ndp_subject_record` VALUES ('116', 'admin', '1', '1', '2018-03-27 10:54:48');
INSERT INTO `ndp_subject_record` VALUES ('117', 'admin', '3', '1', '2018-03-27 10:55:00');
INSERT INTO `ndp_subject_record` VALUES ('118', 'admin', '1', '1', '2018-03-27 10:55:01');
INSERT INTO `ndp_subject_record` VALUES ('119', 'admin', '3', '1', '2018-03-27 10:55:02');
INSERT INTO `ndp_subject_record` VALUES ('120', 'admin', '4', '1', '2018-03-27 10:55:03');
INSERT INTO `ndp_subject_record` VALUES ('121', 'admin', '3', '1', '2018-03-27 10:55:04');
INSERT INTO `ndp_subject_record` VALUES ('122', 'admin', '1', '1', '2018-03-27 10:55:05');
INSERT INTO `ndp_subject_record` VALUES ('123', 'admin', '3', '1', '2018-03-27 10:55:14');
INSERT INTO `ndp_subject_record` VALUES ('124', 'admin', '4', '1', '2018-03-27 10:56:05');
INSERT INTO `ndp_subject_record` VALUES ('125', 'admin', '3', '1', '2018-03-27 11:15:59');
INSERT INTO `ndp_subject_record` VALUES ('126', 'admin', '1', '1', '2018-03-27 14:12:57');
INSERT INTO `ndp_subject_record` VALUES ('127', 'admin', '3', '1', '2018-03-27 14:13:08');
INSERT INTO `ndp_subject_record` VALUES ('128', 'admin', '1', '1', '2018-03-27 14:13:09');
INSERT INTO `ndp_subject_record` VALUES ('129', 'admin', '3', '1', '2018-03-27 14:13:10');
INSERT INTO `ndp_subject_record` VALUES ('130', 'admin', '1', '1', '2018-03-27 14:13:11');
INSERT INTO `ndp_subject_record` VALUES ('131', 'admin', '1', '1', '2018-03-27 14:13:28');
INSERT INTO `ndp_subject_record` VALUES ('132', 'admin', '1', '1', '2018-03-27 14:14:34');
INSERT INTO `ndp_subject_record` VALUES ('133', 'admin', '1', '1', '2018-03-27 14:15:39');
INSERT INTO `ndp_subject_record` VALUES ('134', 'admin', '1', '1', '2018-03-27 14:34:16');
INSERT INTO `ndp_subject_record` VALUES ('135', 'admin', '3', '1', '2018-03-27 14:34:18');
INSERT INTO `ndp_subject_record` VALUES ('136', 'admin', '1', '1', '2018-03-27 14:34:19');
INSERT INTO `ndp_subject_record` VALUES ('137', 'admin', '3', '1', '2018-03-27 14:34:21');
INSERT INTO `ndp_subject_record` VALUES ('138', 'admin', '1', '1', '2018-03-27 14:34:32');
INSERT INTO `ndp_subject_record` VALUES ('139', 'admin', '1', '1', '2018-03-27 14:37:59');
INSERT INTO `ndp_subject_record` VALUES ('140', 'admin', '1', '1', '2018-03-27 14:38:12');
INSERT INTO `ndp_subject_record` VALUES ('141', 'admin', '1', '1', '2018-03-27 14:38:16');
INSERT INTO `ndp_subject_record` VALUES ('142', 'admin', '3', '1', '2018-03-27 14:39:37');
INSERT INTO `ndp_subject_record` VALUES ('143', 'admin', '1', '1', '2018-03-27 14:39:38');
INSERT INTO `ndp_subject_record` VALUES ('144', 'admin', '1', '1', '2018-03-27 14:41:13');
INSERT INTO `ndp_subject_record` VALUES ('145', 'admin', '3', '1', '2018-03-27 14:41:17');
INSERT INTO `ndp_subject_record` VALUES ('146', 'admin', '4', '1', '2018-03-27 14:41:18');
INSERT INTO `ndp_subject_record` VALUES ('147', 'admin', '15', '1', '2018-03-27 14:41:18');
INSERT INTO `ndp_subject_record` VALUES ('148', 'admin', '1', '1', '2018-03-27 14:48:04');
INSERT INTO `ndp_subject_record` VALUES ('149', 'admin', '4', '1', '2018-03-27 14:48:05');
INSERT INTO `ndp_subject_record` VALUES ('150', 'admin', '1', '1', '2018-03-27 16:39:44');
INSERT INTO `ndp_subject_record` VALUES ('151', 'admin', '1', '1', '2018-03-28 10:07:53');
INSERT INTO `ndp_subject_record` VALUES ('152', 'admin', '1', '1', '2018-03-28 13:59:21');
INSERT INTO `ndp_subject_record` VALUES ('153', 'admin', '1', '1', '2018-03-29 16:15:32');
INSERT INTO `ndp_subject_record` VALUES ('154', 'admin', '1', '1', '2018-03-29 16:37:58');
INSERT INTO `ndp_subject_record` VALUES ('155', 'admin', '2', '2', '2018-03-29 16:40:08');
INSERT INTO `ndp_subject_record` VALUES ('156', 'admin', '1', '1', '2018-03-29 16:40:11');
INSERT INTO `ndp_subject_record` VALUES ('157', 'admin', '1', '1', '2018-03-29 16:40:15');
INSERT INTO `ndp_subject_record` VALUES ('158', 'admin', '1', '1', '2018-03-29 16:41:29');
INSERT INTO `ndp_subject_record` VALUES ('159', 'admin', '1', '1', '2018-03-29 16:44:01');
INSERT INTO `ndp_subject_record` VALUES ('160', 'admin', '1', '1', '2018-03-29 16:44:14');
INSERT INTO `ndp_subject_record` VALUES ('161', 'admin', '1', '1', '2018-03-29 16:44:30');
INSERT INTO `ndp_subject_record` VALUES ('162', 'admin', '1', '1', '2018-03-29 16:53:58');
INSERT INTO `ndp_subject_record` VALUES ('163', 'admin', '1', '1', '2018-03-29 16:54:16');
INSERT INTO `ndp_subject_record` VALUES ('164', 'admin', '1', '1', '2018-03-30 11:03:07');
INSERT INTO `ndp_subject_record` VALUES ('165', 'admin', '1', '1', '2018-03-30 11:08:37');
INSERT INTO `ndp_subject_record` VALUES ('166', 'admin', '1', '1', '2018-03-30 13:39:27');
INSERT INTO `ndp_subject_record` VALUES ('167', 'admin', '1', '1', '2018-03-30 13:39:58');
INSERT INTO `ndp_subject_record` VALUES ('168', 'admin', '1', '1', '2018-03-30 13:41:43');
INSERT INTO `ndp_subject_record` VALUES ('169', 'admin', '1', '1', '2018-03-30 13:42:08');
INSERT INTO `ndp_subject_record` VALUES ('170', 'admin', '1', '1', '2018-03-30 13:43:51');

-- ----------------------------
-- Table structure for `ndp_table`
-- ----------------------------
DROP TABLE IF EXISTS `ndp_table`;
CREATE TABLE `ndp_table` (
  `TABLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TABLE_CODE` varchar(50) NOT NULL,
  `TABLE_NAME` varchar(50) NOT NULL,
  `TABLE_DESC` varchar(100) DEFAULT NULL,
  `CREATE_USER` varchar(30) DEFAULT NULL,
  `CREATE_TIME` char(19) DEFAULT NULL,
  PRIMARY KEY (`TABLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ndp_table
-- ----------------------------
INSERT INTO `ndp_table` VALUES ('1', 'DEMO_USER', '测试用户表', '', '1', '2018-03-14 15:54:18');
INSERT INTO `ndp_table` VALUES ('31', 'pl_com', '测试', '测试', '1', '2018-03-14 20:40:46');
INSERT INTO `ndp_table` VALUES ('32', 'pl_item', '商品表', '商品表', '1', '2018-03-15 11:37:25');
INSERT INTO `ndp_table` VALUES ('36', 'plm_item_com', '公司商品', '公司商品', 'admin', '2018-03-21 09:00:31');

-- ----------------------------
-- Table structure for `ndp_table_cell`
-- ----------------------------
DROP TABLE IF EXISTS `ndp_table_cell`;
CREATE TABLE `ndp_table_cell` (
  `CELL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TABLE_ID` int(11) NOT NULL,
  `CELL_CODE` varchar(50) NOT NULL,
  `CELL_NAME` varchar(50) DEFAULT NULL,
  `DICT_ID` varchar(30) DEFAULT NULL,
  `DATA_UNIT` varchar(15) DEFAULT NULL,
  `DATA_DESC` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`CELL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ndp_table_cell
-- ----------------------------
INSERT INTO `ndp_table_cell` VALUES ('23', '1', 'ID', '主键', '', null, null);
INSERT INTO `ndp_table_cell` VALUES ('24', '1', 'NAME', '名称', null, null, null);
INSERT INTO `ndp_table_cell` VALUES ('25', '1', 'AGE', '年龄', null, '岁', null);
INSERT INTO `ndp_table_cell` VALUES ('26', '1', 'SEX', '性别', '1', null, null);
INSERT INTO `ndp_table_cell` VALUES ('27', '1', 'COM_ID', '公司', null, null, null);
INSERT INTO `ndp_table_cell` VALUES ('28', '1', 'ITEM_ID', '商品', null, null, null);
INSERT INTO `ndp_table_cell` VALUES ('29', '1', 'CREATE_TIME', '创建时间', null, null, null);
INSERT INTO `ndp_table_cell` VALUES ('32', '31', 'COM_ID', '公司', '', '省公司', '');
INSERT INTO `ndp_table_cell` VALUES ('33', '32', 'ITEM_ID', '商品名称', '', '重量', '商品名称');
INSERT INTO `ndp_table_cell` VALUES ('35', '36', 'COM_ID', '公司编码', '', '编码', '公司编码');
INSERT INTO `ndp_table_cell` VALUES ('36', '36', 'ITEM_ID', '商品编码', '', '', '商品编码');
INSERT INTO `ndp_table_cell` VALUES ('37', '36', 'PLM_TYPE', '商品生命周期类型', 'PLM_TYPE', '', '商品生命周期类型');
INSERT INTO `ndp_table_cell` VALUES ('38', '36', 'PRICE_PUH', '工商调拨价', '', '', '工商调拨价');
INSERT INTO `ndp_table_cell` VALUES ('39', '36', 'PRICE_TRN', '商商调拨价格', '', '', '商商调拨价格');
INSERT INTO `ndp_table_cell` VALUES ('40', '36', 'PRICE_TRADE', '批发价格', '', '', '批发价格');
INSERT INTO `ndp_table_cell` VALUES ('41', '36', 'PRICE_RETAIL', '建议零售价', '', '', '建议零售价');
