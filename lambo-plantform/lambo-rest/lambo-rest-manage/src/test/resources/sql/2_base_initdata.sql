-- ----------------------------
-- Records of upms_log
-- ----------------------------
INSERT INTO `upms_log` VALUES ('779', '登录', 'admin', '1510034418813', '167', 'http://localhost:1111', '/sso/login', 'http://localhost:1111/sso/login', 'POST', '{password=[123456],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '0:0:0:0:0:0:0:1', '{\"code\":1,\"data\":\"upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('780', '获取当前登录用户有权限的菜单', 'admin', '1510034423026', '103', 'http://localhost:1111', '/api/getMenuTree', 'http://localhost:1111/api/getMenuTree', 'GET', null, 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '0:0:0:0:0:0:0:1', '{\"code\":1,\"data\":[{\"basePath\":\"upms\",\"children\":[{\"permissionValue\":\"\",\"path\":\"\",\"children\":[{\"permissionValue\":\"upms:system:read\",\"path\":\"/manage/system/index\",\"icon\":\"\",\"pid\":1,\"id\":2,\"title\":\"系统管理\",\"order\":2},{\"permissionValue\":\"upms:organization:read\",\"path\":\"/manage/organization/index\",\"icon\":\"\",\"pid\":1,\"id\":3,\"title\":\"组织管理\",\"order\":3}],\"icon\":\"zmdi zmdi-accounts-list\",\"pid\":0,\"id\":1,\"title\":\"系统组织管理\",\"order\":1},{\"permissionValue\":\"upms:system:read\",\"path\":\"/manage/system/index\",\"icon\":\"\",\"pid\":1,\"id\":2,\"title\":\"系统管理\",\"order\":2},{\"permissionValue\":\"upms:organization:read\",\"path\":\"/manage/organization/index\",\"icon\":\"\",\"pid\":1,\"id\":3,\"title\":\"组织管理\",\"order\":3},{\"permissionValue\":\"\",\"path\":\"\",\"children\":[{\"permissionValue\":\"upms:user:read\",\"path\":\"/manage/user/index\",\"icon\":\"\",\"pid\":4,\"id\":6,\"title\":\"用户管理\",\"order\":5},{\"permissionValue\":\"upms:role:read\",\"path\":\"/manage/role/index\",\"icon\":\"\",\"pid\":4,\"id\":5,\"title\":\"角色管理\",\"order\":6}],\"icon\":\"zmdi zmdi-accounts\",\"pid\":0,\"id\":4,\"title\":\"角色用户管理\",\"order\":4},{\"permissionValue\":\"\",\"path\":\"\",\"children\":[{\"permissionValue\":\"upms:permission:read\",\"path\":\"/manage/permission/index\",\"pid\":7,\"id\":39,\"title\":\"权限管理\",\"order\":39}],\"icon\":\"zmdi zmdi-lock-outline\",\"pid\":0,\"id\":7,\"title\":\"权限资源管理\",\"order\":7},{\"permissionValue\":\"\",\"path\":\"\",\"children\":[{\"permissionValue\":\"upms:session:read\",\"path\":\"/manage/session/index\",\"icon\":\"\",\"pid\":12,\"id\":14,\"title\":\"会话管理\",\"order\":14},{\"permissionValue\":\"upms:log:read\",\"path\":\"/manage/log/index\",\"icon\":\"\",\"pid\":12,\"id\":15,\"title\":\"日志记录\",\"order\":15}],\"icon\":\"zmdi zmdi-more\",\"pid\":0,\"id\":12,\"title\":\"其他数据管理\",\"order\":12}],\"icon\":\"zmdi zmdi-shield-security\",\"description\":\"用户权限管理系统（RBAC细粒度用户权限、统一后台、单点登录、会话管理）\",\"banner\":\"\",\"id\":1,\"title\":\"权限管理系统\",\"order\":1}],\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('781', '登录', 'admin', '1510047675993', '1072', 'http://127.0.0.1:1111', '/sso/login', 'http://127.0.0.1:1111/sso/login', 'POST', '{password=[123456],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('782', '获取当前登录用户有权限的菜单', 'admin', '1510047686976', '98', 'http://127.0.0.1:1111', '/api/menu/getList', 'http://127.0.0.1:1111/api/menu/getList', 'GET', null, 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":[{\"basePath\":\"/upms\",\"children\":[{\"permissionValue\":\"\",\"path\":\"\",\"children\":[{\"permissionValue\":\"upms:system:read\",\"path\":\"/manage/system/index\",\"name\":2,\"icon\":\"\",\"pid\":1,\"title\":\"系统管理\",\"order\":2},{\"permissionValue\":\"upms:organization:read\",\"path\":\"/manage/organization/index\",\"name\":3,\"icon\":\"\",\"pid\":1,\"title\":\"组织管理\",\"order\":3}],\"name\":1,\"icon\":\"zmdi zmdi-accounts-list\",\"pid\":0,\"title\":\"系统组织管理\",\"order\":1},{\"permissionValue\":\"\",\"path\":\"\",\"children\":[{\"permissionValue\":\"upms:user:read\",\"path\":\"/manage/user/index\",\"name\":6,\"icon\":\"\",\"pid\":4,\"title\":\"用户管理\",\"order\":5},{\"permissionValue\":\"upms:role:read\",\"path\":\"/manage/role/index\",\"name\":5,\"icon\":\"\",\"pid\":4,\"title\":\"角色管理\",\"order\":6}],\"name\":4,\"icon\":\"zmdi zmdi-accounts\",\"pid\":0,\"title\":\"角色用户管理\",\"order\":4},{\"permissionValue\":\"\",\"path\":\"\",\"children\":[{\"permissionValue\":\"upms:permission:read\",\"path\":\"/manage/permission/index\",\"name\":39,\"pid\":7,\"title\":\"权限管理\",\"order\":39}],\"name\":7,\"icon\":\"zmdi zmdi-lock-outline\",\"pid\":0,\"title\":\"权限资源管理\",\"order\":7},{\"permissionValue\":\"\",\"path\":\"\",\"children\":[{\"permissionValue\":\"upms:session:read\",\"path\":\"/manage/session/index\",\"name\":14,\"icon\":\"\",\"pid\":12,\"title\":\"会话管理\",\"order\":14},{\"permissionValue\":\"upms:log:read\",\"path\":\"/manage/log/index\",\"name\":15,\"icon\":\"\",\"pid\":12,\"title\":\"日志记录\",\"order\":15}],\"name\":12,\"icon\":\"zmdi zmdi-more\",\"pid\":0,\"title\":\"其他数据管理\",\"order\":12}],\"name\":1,\"icon\":\"zmdi zmdi-shield-security\",\"description\":\"用户权限管理系统（RBAC细粒度用户权限、统一后台、单点登录、会话管理）\",\"banner\":\"\",\"title\":\"权限管理系统\",\"order\":1}],\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('783', '登录', 'admin', '1510120824579', '1589', 'http://localhost:1111', '/sso/login', 'http://localhost:1111/sso/login', 'POST', '{password=[123456],rememberMe=[false],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('784', '登录', 'admin', '1510121406329', '30', 'http://localhost:1111', '/sso/login', 'http://localhost:1111/sso/login', 'POST', '{password=[123456],rememberMe=[false],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('785', '登录', 'admin', '1510121420864', '20', 'http://localhost:1111', '/sso/login', 'http://localhost:1111/sso/login', 'POST', '{password=[123456],rememberMe=[false],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('786', '登录', 'admin', '1510121453053', '13', 'http://localhost:1111', '/sso/login', 'http://localhost:1111/sso/login', 'POST', '{password=[123456],rememberMe=[false],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('787', '登录', 'admin', '1510122324009', '21', 'http://localhost:1111', '/sso/login', 'http://localhost:1111/sso/login', 'POST', '{password=[123456],rememberMe=[false],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('788', '登录', 'admin', '1510122959734', '85', 'http://localhost:1111', '/sso/login', 'http://localhost:1111/sso/login', 'POST', '{password=[123456],rememberMe=[false],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('789', '登录', 'admin', '1510122971006', '12', 'http://localhost:1111', '/sso/login', 'http://localhost:1111/sso/login', 'POST', '{password=[123456],rememberMe=[false],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('790', '登录', 'admin', '1510123088757', '33', 'http://localhost:1111', '/sso/login', 'http://localhost:1111/sso/login', 'POST', '{password=[123456],rememberMe=[false],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('791', '登录', 'admin', '1510123092484', '15', 'http://localhost:1111', '/sso/login', 'http://localhost:1111/sso/login', 'POST', '{password=[123456],rememberMe=[false],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('792', '登录', 'admin', '1510123141416', '14', 'http://localhost:1111', '/sso/login', 'http://localhost:1111/sso/login', 'POST', '{password=[123456],rememberMe=[false],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('793', '登录', 'admin', '1510123219366', '13', 'http://localhost:8081', '/sso/login', 'http://localhost:8081/sso/login', 'POST', '{password=[123456],rememberMe=[false],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('794', '登录', 'admin', '1510123226717', '32', 'http://localhost:8081', '/sso/login', 'http://localhost:8081/sso/login', 'POST', '{password=[123456],rememberMe=[false],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('795', '登录', 'admin', '1510126516156', '111', 'http://localhost:8081', '/sso/login', 'http://localhost:8081/sso/login', 'POST', '{password=[123456],rememberMe=[false],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('796', '登录', 'admin', '1510128582935', '73', 'http://localhost:8081', '/sso/login', 'http://localhost:8081/sso/login', 'POST', '{password=[123456],rememberMe=[false],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('797', '登录', 'admin', '1510128596753', '12', 'http://localhost:8081', '/sso/login', 'http://localhost:8081/sso/login', 'POST', '{password=[123456],rememberMe=[false],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('798', '登录', 'admin', '1510133579280', '159', 'http://localhost:8081', '/sso/login', 'http://localhost:8081/sso/login', 'POST', '{password=[123456],rememberMe=[false],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('799', '登录', 'admin', '1510136180778', '79', 'http://localhost:8081', '/sso/login', 'http://localhost:8081/sso/login', 'POST', '{password=[123456],rememberMe=[false],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('800', '登录', 'admin', '1510137685028', '42', 'http://localhost:1111', '/sso/login', 'http://localhost:1111/sso/login', 'POST', '{password=[123456],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '0:0:0:0:0:0:0:1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('801', '获取当前登录用户有权限的菜单', 'admin', '1510137743341', '474', 'http://localhost:1111', '/api/menu/getList', 'http://localhost:1111/api/menu/getList', 'GET', null, 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '0:0:0:0:0:0:0:1', '{\"code\":1,\"data\":[{\"basePath\":\"/upms\",\"children\":[{\"permissionValue\":\"\",\"path\":\"\",\"children\":[{\"permissionValue\":\"upms:system:read\",\"path\":\"/manage/system/index\",\"name\":2,\"icon\":\"\",\"pid\":1,\"title\":\"系统管理\",\"order\":2},{\"permissionValue\":\"upms:organization:read\",\"path\":\"/manage/organization/index\",\"name\":3,\"icon\":\"\",\"pid\":1,\"title\":\"组织管理\",\"order\":3}],\"name\":1,\"icon\":\"zmdi zmdi-accounts-list\",\"pid\":0,\"title\":\"系统组织管理\",\"order\":1},{\"permissionValue\":\"\",\"path\":\"\",\"children\":[{\"permissionValue\":\"upms:user:read\",\"path\":\"/manage/user/index\",\"name\":6,\"icon\":\"\",\"pid\":4,\"title\":\"用户管理\",\"order\":5},{\"permissionValue\":\"upms:role:read\",\"path\":\"/manage/role/index\",\"name\":5,\"icon\":\"\",\"pid\":4,\"title\":\"角色管理\",\"order\":6}],\"name\":4,\"icon\":\"zmdi zmdi-accounts\",\"pid\":0,\"title\":\"角色用户管理\",\"order\":4},{\"permissionValue\":\"\",\"path\":\"\",\"children\":[{\"permissionValue\":\"upms:permission:read\",\"path\":\"/manage/permission/index\",\"name\":39,\"pid\":7,\"title\":\"权限管理\",\"order\":39}],\"name\":7,\"icon\":\"zmdi zmdi-lock-outline\",\"pid\":0,\"title\":\"权限资源管理\",\"order\":7},{\"permissionValue\":\"\",\"path\":\"\",\"children\":[{\"permissionValue\":\"upms:session:read\",\"path\":\"/manage/session/index\",\"name\":14,\"icon\":\"\",\"pid\":12,\"title\":\"会话管理\",\"order\":14},{\"permissionValue\":\"upms:log:read\",\"path\":\"/manage/log/index\",\"name\":15,\"icon\":\"\",\"pid\":12,\"title\":\"日志记录\",\"order\":15}],\"name\":12,\"icon\":\"zmdi zmdi-more\",\"pid\":0,\"title\":\"其他数据管理\",\"order\":12}],\"name\":1,\"icon\":\"zmdi zmdi-shield-security\",\"description\":\"用户权限管理系统（RBAC细粒度用户权限、统一后台、单点登录、会话管理）\",\"banner\":\"\",\"title\":\"权限管理系统\",\"order\":1}],\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('802', '登录', 'admin', '1510195768097', '152', 'http://localhost:8081', '/sso/login', 'http://localhost:8081/sso/login', 'POST', '{password=[123456],rememberMe=[false],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('803', '登录', 'admin', '1510195768097', '152', 'http://localhost:8081', '/sso/login', 'http://localhost:8081/sso/login', 'POST', '{password=[123456],rememberMe=[false],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('804', '登录', 'admin', '1510195842729', '24', 'http://localhost:8081', '/sso/login', 'http://localhost:8081/sso/login', 'POST', '{password=[123456],rememberMe=[false],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('805', '登录', 'admin', '1510195871841', '13', 'http://localhost:8081', '/sso/login', 'http://localhost:8081/sso/login', 'POST', '{password=[123456],rememberMe=[false],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('806', '登录', 'admin', '1510195891090', '15', 'http://localhost:8081', '/sso/login', 'http://localhost:8081/sso/login', 'POST', '{password=[123456],rememberMe=[false],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('807', '登录', 'admin', '1510196147398', '65', 'http://localhost:8081', '/sso/login', 'http://localhost:8081/sso/login', 'POST', '{password=[123456],rememberMe=[false],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);
INSERT INTO `upms_log` VALUES ('808', '登录', 'admin', '1510196616835', '17', 'http://localhost:8081', '/sso/login', 'http://localhost:8081/sso/login', 'POST', '{password=[123456],rememberMe=[false],username=[admin]}', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36', '127.0.0.1', '{\"code\":1,\"data\":\"/upms\",\"message\":\"success\"}', null);


-- ----------------------------
-- Records of upms_organization
-- ----------------------------
INSERT INTO `upms_organization` VALUES ('1', null, '总部', '北京总部', '1');
INSERT INTO `upms_organization` VALUES ('4', null, '河北分部', '河北石家庄', '1488122466236');
INSERT INTO `upms_organization` VALUES ('5', null, '河南分部', '河南郑州', '1488122480265');
INSERT INTO `upms_organization` VALUES ('6', null, '湖北分部', '湖北武汉', '1488122493265');
INSERT INTO `upms_organization` VALUES ('7', null, '湖南分部', '湖南长沙', '1488122502752');


-- ----------------------------
-- Records of upms_permission
-- ----------------------------
INSERT INTO `upms_permission` VALUES ('1', '1', '0', '系统组织管理', '1', '', '', 'zmdi zmdi-accounts-list', '1', '1', '1');
INSERT INTO `upms_permission` VALUES ('2', '1', '1', '系统管理', '2', 'upms:system:read', '/frame/manage/system/index', '', '1', '2', '2');
INSERT INTO `upms_permission` VALUES ('3', '1', '1', '组织管理', '2', 'upms:organization:read', '/frame/manage/organization/index', '', '1', '3', '3');
INSERT INTO `upms_permission` VALUES ('4', '1', '0', '角色用户管理', '1', '', '', 'zmdi zmdi-accounts', '1', '4', '4');
INSERT INTO `upms_permission` VALUES ('5', '1', '4', '角色管理', '2', 'upms:role:read', '/frame/manage/role/index', '', '1', '6', '6');
INSERT INTO `upms_permission` VALUES ('6', '1', '4', '用户管理', '2', 'upms:user:read', '/frame/manage/user/index', '', '1', '5', '5');
INSERT INTO `upms_permission` VALUES ('7', '1', '0', '权限资源管理', '1', '', '', 'zmdi zmdi-lock-outline', '1', '7', '7');
INSERT INTO `upms_permission` VALUES ('12', '1', '0', '其他数据管理', '1', '', '', 'zmdi zmdi-more', '1', '12', '12');
INSERT INTO `upms_permission` VALUES ('14', '1', '12', '会话管理', '2', 'upms:session:read', '/frame/manage/session/index', '', '1', '14', '14');
INSERT INTO `upms_permission` VALUES ('15', '1', '12', '日志记录', '2', 'upms:log:read', '/frame/manage/log/index', '', '1', '15', '15');
INSERT INTO `upms_permission` VALUES ('24', '1', '2', '新增系统', '3', 'upms:system:create', '', 'zmdi zmdi-plus', '1', '24', '24');
INSERT INTO `upms_permission` VALUES ('25', '1', '2', '编辑系统', '3', 'upms:system:update', '', 'zmdi zmdi-edit', '1', '25', '25');
INSERT INTO `upms_permission` VALUES ('26', '1', '2', '删除系统', '3', 'upms:system:delete', '', 'zmdi zmdi-close', '1', '26', '26');
INSERT INTO `upms_permission` VALUES ('27', '1', '3', '新增组织', '3', 'upms:organization:create', '', 'zmdi zmdi-plus', '1', '27', '27');
INSERT INTO `upms_permission` VALUES ('28', '1', '3', '编辑组织', '3', 'upms:organization:update', '', 'zmdi zmdi-edit', '1', '28', '28');
INSERT INTO `upms_permission` VALUES ('29', '1', '3', '删除组织', '3', 'upms:organization:delete', '', 'zmdi zmdi-close', '1', '29', '29');
INSERT INTO `upms_permission` VALUES ('30', '1', '6', '新增用户', '3', 'upms:user:create', '', 'zmdi zmdi-plus', '1', '30', '30');
INSERT INTO `upms_permission` VALUES ('31', '1', '6', '编辑用户', '3', 'upms:user:update', '', 'zmdi zmdi-edit', '1', '31', '31');
INSERT INTO `upms_permission` VALUES ('32', '1', '6', '删除用户', '3', 'upms:user:delete', '', 'zmdi zmdi-close', '1', '32', '32');
INSERT INTO `upms_permission` VALUES ('33', '1', '5', '新增角色', '3', 'upms:role:create', '', 'zmdi zmdi-plus', '1', '33', '33');
INSERT INTO `upms_permission` VALUES ('34', '1', '5', '编辑角色', '3', 'upms:role:update', '', 'zmdi zmdi-edit', '1', '34', '34');
INSERT INTO `upms_permission` VALUES ('35', '1', '5', '删除角色', '3', 'upms:role:delete', '', 'zmdi zmdi-close', '1', '35', '35');
INSERT INTO `upms_permission` VALUES ('36', '1', '39', '新增权限', '3', 'upms:permission:create', '', 'zmdi zmdi-plus', '1', '36', '36');
INSERT INTO `upms_permission` VALUES ('37', '1', '39', '编辑权限', '3', 'upms:permission:update', '', 'zmdi zmdi-edit', '1', '37', '37');
INSERT INTO `upms_permission` VALUES ('38', '1', '39', '删除权限', '3', 'upms:permission:delete', '', 'zmdi zmdi-close', '1', '38', '38');
INSERT INTO `upms_permission` VALUES ('39', '1', '7', '权限管理', '2', 'upms:permission:read', '/frame/manage/permission/index', null, '1', '39', '39');
INSERT INTO `upms_permission` VALUES ('46', '1', '5', '角色权限', '3', 'upms:role:permission', '', 'zmdi zmdi-key', '1', '1488091928257', '1488091928257');
INSERT INTO `upms_permission` VALUES ('48', '1', '6', '用户组织', '3', 'upms:user:organization', '', 'zmdi zmdi-accounts-list', '1', '1488120011165', '1488120011165');
INSERT INTO `upms_permission` VALUES ('50', '1', '6', '用户角色', '3', 'upms:user:role', '', 'zmdi zmdi-accounts', '1', '1488120554175', '1488120554175');
INSERT INTO `upms_permission` VALUES ('51', '1', '6', '用户权限', '3', 'upms:user:permission', '', 'zmdi zmdi-key', '1', '1488092013302', '1488092013302');
INSERT INTO `upms_permission` VALUES ('53', '1', '14', '强制退出', '3', 'upms:session:forceout', '', 'zmdi zmdi-run', '1', '1488379514715', '1488379514715');
INSERT INTO `upms_permission` VALUES ('57', '1', '15', '删除权限', '3', 'upms:log:delete', '', 'zmdi zmdi-close', '1', '1489503867909', '1489503867909');


-- ----------------------------
-- Records of upms_role_permission
-- ----------------------------
INSERT INTO `upms_role_permission` VALUES ('1', '1', '1');
INSERT INTO `upms_role_permission` VALUES ('2', '1', '2');
INSERT INTO `upms_role_permission` VALUES ('3', '1', '3');
INSERT INTO `upms_role_permission` VALUES ('4', '1', '4');
INSERT INTO `upms_role_permission` VALUES ('5', '1', '5');
INSERT INTO `upms_role_permission` VALUES ('6', '1', '6');
INSERT INTO `upms_role_permission` VALUES ('7', '1', '7');
INSERT INTO `upms_role_permission` VALUES ('8', '1', '39');
INSERT INTO `upms_role_permission` VALUES ('12', '1', '12');
INSERT INTO `upms_role_permission` VALUES ('14', '1', '14');
INSERT INTO `upms_role_permission` VALUES ('15', '1', '15');
INSERT INTO `upms_role_permission` VALUES ('24', '1', '24');
INSERT INTO `upms_role_permission` VALUES ('27', '1', '27');
INSERT INTO `upms_role_permission` VALUES ('28', '1', '28');
INSERT INTO `upms_role_permission` VALUES ('29', '1', '29');
INSERT INTO `upms_role_permission` VALUES ('30', '1', '30');
INSERT INTO `upms_role_permission` VALUES ('31', '1', '31');
INSERT INTO `upms_role_permission` VALUES ('32', '1', '32');
INSERT INTO `upms_role_permission` VALUES ('33', '1', '33');
INSERT INTO `upms_role_permission` VALUES ('34', '1', '34');
INSERT INTO `upms_role_permission` VALUES ('35', '1', '35');
INSERT INTO `upms_role_permission` VALUES ('36', '1', '36');
INSERT INTO `upms_role_permission` VALUES ('37', '1', '37');
INSERT INTO `upms_role_permission` VALUES ('38', '1', '38');
INSERT INTO `upms_role_permission` VALUES ('39', '1', '46');
INSERT INTO `upms_role_permission` VALUES ('40', '1', '51');
INSERT INTO `upms_role_permission` VALUES ('44', '1', '48');
INSERT INTO `upms_role_permission` VALUES ('45', '1', '50');
INSERT INTO `upms_role_permission` VALUES ('47', '1', '53');
INSERT INTO `upms_role_permission` VALUES ('55', '1', '57');
INSERT INTO `upms_role_permission` VALUES ('124', '1', '25');
INSERT INTO `upms_role_permission` VALUES ('125', '1', '26');

-- ----------------------------
-- Records of upms_user
-- ----------------------------
INSERT INTO `upms_user` VALUES ('1', 'admin', '3038D9CB63B3152A79B8153FB06C02F7', '66f1b370c660445a8657bf8bf1794486', '张恕征', '/resources/lambo-admin/images/avatar.jpg', '', '469741414@qq.com', '1', '0', '1');
INSERT INTO `upms_user` VALUES ('2', 'test', '285C9762F5F9046F5893F752DFAF3476', 'd2d0d03310444ad388a8b290b0fe8564', '张恕征', '/resources/lambo-admin/images/avatar.jpg', '', '469741414@qq.com', '1', '0', '1493394720495');


-- ----------------------------
-- Records of upms_user_organization
-- ----------------------------
INSERT INTO `upms_user_organization` VALUES ('19', '1', '1');
INSERT INTO `upms_user_organization` VALUES ('20', '1', '4');
INSERT INTO `upms_user_organization` VALUES ('21', '1', '5');
INSERT INTO `upms_user_organization` VALUES ('22', '1', '6');
INSERT INTO `upms_user_organization` VALUES ('23', '1', '7');

-- ----------------------------
-- Records of upms_user_permission
-- ----------------------------
INSERT INTO `upms_user_permission` VALUES ('5', '2', '24', '-1');
INSERT INTO `upms_user_permission` VALUES ('6', '2', '26', '-1');
INSERT INTO `upms_user_permission` VALUES ('7', '2', '27', '-1');
INSERT INTO `upms_user_permission` VALUES ('8', '2', '29', '-1');
INSERT INTO `upms_user_permission` VALUES ('9', '2', '32', '-1');
INSERT INTO `upms_user_permission` VALUES ('10', '2', '51', '-1');
INSERT INTO `upms_user_permission` VALUES ('11', '2', '48', '-1');
INSERT INTO `upms_user_permission` VALUES ('12', '2', '50', '-1');
INSERT INTO `upms_user_permission` VALUES ('13', '2', '35', '-1');
INSERT INTO `upms_user_permission` VALUES ('14', '2', '46', '-1');
INSERT INTO `upms_user_permission` VALUES ('15', '2', '37', '-1');
INSERT INTO `upms_user_permission` VALUES ('16', '2', '38', '-1');
INSERT INTO `upms_user_permission` VALUES ('17', '2', '57', '-1');


-- ----------------------------
-- Records of upms_user_role
-- ----------------------------
INSERT INTO `upms_user_role` VALUES ('4', '1', '1');
INSERT INTO `upms_user_role` VALUES ('5', '1', '2');
INSERT INTO `upms_user_role` VALUES ('6', '2', '1');
INSERT INTO `upms_user_role` VALUES ('7', '2', '2');

-- ----------------------------
-- Records of upms_system
-- ----------------------------
INSERT INTO `upms_system` VALUES ('1', 'zmdi zmdi-shield-security', '', '#29A176', '/upms', '1', 'lambo-upms-server', '权限管理系统', '用户权限管理系统（RBAC细粒度用户权限、统一后台、单点登录、会话管理）', '1', '1');

-- ----------------------------
-- Records of upms_role
-- ----------------------------
INSERT INTO `upms_role` VALUES ('1', 'super', '超级管理员', '拥有所有权限', '1', '1');
INSERT INTO `upms_role` VALUES ('2', 'admin', '管理员', '拥有除权限管理系统外的所有权限', '1487471013117', '1487471013117');
