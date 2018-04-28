package com.lambo.common.utils.other;


import com.lambo.common.utils.lang.StringUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.velocity.VelocityContext;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 代码生成类
 * Created by lambo on 2017/1/10.
 */
public class MybatisGeneratorUtil {

	/**
	 * generatorConfig模板路径
	 */
	private static String generatorConfig_vm = "/template/generatorConfig.vm";
	/**
	 * Service模板路径
	 */
	private static String service_vm = "/template/Service.vm";
	/**
	 * ServiceMock模板路径
	 */
	private static String serviceMock_vm = "/template/ServiceMock.vm";
	/**
	 * ServiceImpl模板路径
	 */
	private static String serviceImpl_vm = "/template/ServiceImpl.vm";

	/**
	 * 根据模板生成generatorConfig.xml文件
	 * @param jdbc_driver   驱动路径
	 * @param jdbc_url      链接
	 * @param jdbc_username 帐号
	 * @param jdbc_password 密码
	 * @param module        项目模块
	 * @param database      数据库
	 * @param table_name  表名
	 * @param package_name  包名
	 */
	public static void generator(
			String jdbc_driver,
			String jdbc_url,
			String jdbc_username,
			String jdbc_password,
			String module,
			String database,
			String table_name,
			String package_name,
			String domain_object_name) throws Exception{

		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("win")) {
			generatorConfig_vm = MybatisGeneratorUtil.class.getResource(generatorConfig_vm).getPath().replaceFirst("/", "");
			service_vm = MybatisGeneratorUtil.class.getResource(service_vm).getPath().replaceFirst("/", "");
			serviceMock_vm = MybatisGeneratorUtil.class.getResource(serviceMock_vm).getPath().replaceFirst("/", "");
			serviceImpl_vm = MybatisGeneratorUtil.class.getResource(serviceImpl_vm).getPath().replaceFirst("/", "");
		} else {
			generatorConfig_vm = MybatisGeneratorUtil.class.getResource(generatorConfig_vm).getPath();
			service_vm = MybatisGeneratorUtil.class.getResource(service_vm).getPath();
			serviceMock_vm = MybatisGeneratorUtil.class.getResource(serviceMock_vm).getPath();
			serviceImpl_vm = MybatisGeneratorUtil.class.getResource(serviceImpl_vm).getPath();
		}

		String targetProject = "";
		System.out.println("targetProject="+targetProject);
		String basePath = MybatisGeneratorUtil.class.getResource("/").getPath().replace("/target/classes/", "").replace(targetProject, "").replaceFirst("/", "");
		System.out.println("basePath="+basePath);
		String generatorConfig_xml = MybatisGeneratorUtil.class.getResource("/").getPath().replace("/target/classes/", "") + "/src/main/resources/generatorConfig.xml";
		targetProject = basePath + targetProject;
		String sql = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + database + "' AND table_name = '" + table_name + "';";

		System.out.println("========== 开始生成generatorConfig.xml文件 ==========");
		List<Map<String, Object>> tables = new ArrayList<>();
		try {
			VelocityContext context = new VelocityContext();
			Map<String, Object> table;

			// 查询指定表元数据
			JdbcUtil jdbcUtil = new JdbcUtil(jdbc_driver, jdbc_url, jdbc_username, jdbc_password);
			List<Map> result = jdbcUtil.selectByParams(sql, null);
			for (Map map : result) {
				System.out.println(map.get("TABLE_NAME"));
				table = new HashMap<>();
				table.put("table_name", map.get("TABLE_NAME"));
				table.put("model_name", domain_object_name);
				tables.add(table);
			}
			jdbcUtil.release();

			String targetProject_sqlMap = basePath;
			context.put("tables", tables);
			context.put("generator_javaModelGenerator_targetPackage", package_name + (StringUtils.isNotBlank(module) ? "." + module : "") + ".dao.model");
			context.put("generator_sqlMapGenerator_targetPackage", package_name + (StringUtils.isNotBlank(module) ? "." + module : "") + ".dao.mapper");
			context.put("generator_javaClientGenerator_targetPackage", package_name + (StringUtils.isNotBlank(module) ? "." + module : "") + ".dao.mapper");
			context.put("targetProject", basePath);
			context.put("targetProject_sqlMap", basePath);

			context.put("generator_jdbc_driver", jdbc_driver);
			context.put("generator_jdbc_url", jdbc_url);
			context.put("generator_jdbc_username", jdbc_username);
			context.put("generator_jdbc_password", jdbc_password);
			VelocityUtil.generate(generatorConfig_vm, generatorConfig_xml, context);
			// 删除旧代码
			deleteDir(new File(basePath + "/src/main/java/" + package_name.replaceAll("\\.", "/") + (StringUtils.isNotBlank(module) ? "/" + module : "") + "/dao/model"));
			deleteDir(new File(basePath + "/src/main/java/" + package_name.replaceAll("\\.", "/") + (StringUtils.isNotBlank(module) ? "/" + module : "") + "/dao/mapper"));
			deleteDir(new File(basePath + "/src/main/java/" + package_name.replaceAll("\\.", "/") + (StringUtils.isNotBlank(module) ? "/" + module : "") + "/dao/mapper"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("========== 结束生成generatorConfig.xml文件 ==========");

		System.out.println("========== 开始运行MybatisGenerator ==========");
		List<String> warnings = new ArrayList<>();
		File configFile = new File(generatorConfig_xml);
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(true);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
		for (String warning : warnings) {
			System.out.println(warning);
		}
		System.out.println("========== 结束运行MybatisGenerator ==========");

		System.out.println("========== 开始生成Service ==========");
		String ctime = new SimpleDateFormat("yyyy/M/d").format(new Date());
		String servicePath = basePath + "/src/main/java/" + package_name.replaceAll("\\.", "/") + (StringUtils.isNotBlank(module) ? "/" + module : "") + "/service/api";
		String serviceImplPath = basePath  + "/src/main/java/" + package_name.replaceAll("\\.", "/") + (StringUtils.isNotBlank(module) ? "/" + module : "") + "/service/impl";
		for (int i = 0; i < tables.size(); i++) {
			String model = StringUtils.lineToHump(ObjectUtils.toString(tables.get(i).get("table_name")));
			String service = servicePath + "/" + model + "Service.java";
			String serviceMock = servicePath + "/" + model + "ServiceMock.java";
			String serviceImpl = serviceImplPath + "/" + model + "ServiceImpl.java";
			// 生成service
			File serviceFile = new File(service);
			if (!serviceFile.exists()) {
				VelocityContext context = new VelocityContext();
				context.put("package_name", package_name);
				context.put("model", model);
				context.put("ctime", ctime);
				VelocityUtil.generate(service_vm, service, context);
				System.out.println(service);
			}
			// 生成serviceMock
			File serviceMockFile = new File(serviceMock);
			if (!serviceMockFile.exists()) {
				VelocityContext context = new VelocityContext();
				context.put("package_name", package_name);
				context.put("model", model);
				context.put("ctime", ctime);
				VelocityUtil.generate(serviceMock_vm, serviceMock, context);
				System.out.println(serviceMock);
			}
			// 生成serviceImpl
			File serviceImplFile = new File(serviceImpl);
			if (!serviceImplFile.exists()) {
				VelocityContext context = new VelocityContext();
				context.put("package_name", package_name);
				context.put("model", model);
				context.put("mapper", StringUtils.toLowerCaseFirstOne(model));
				context.put("ctime", ctime);
				VelocityUtil.generate(serviceImpl_vm, serviceImpl, context);
				System.out.println(serviceImpl);
			}
		}
		System.out.println("========== 结束生成Service ==========");
	}

	// 递归删除非空文件夹
	public static void deleteDir(File dir) {
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				deleteDir(files[i]);
			}
		}
		dir.delete();
	}

}
