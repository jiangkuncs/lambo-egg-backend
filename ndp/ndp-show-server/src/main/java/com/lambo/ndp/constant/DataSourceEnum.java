package com.lambo.ndp.constant;

/**
 * 多数据源枚举
 * @author sunzhen 
 */
public enum DataSourceEnum {

	/**
	 * mysql
	 */
	MYSQL("mysqlDataSource", true),
	/**
	 * greenplum
	 */
	GREENPLUM("greenplumDataSource", false),;

	/**
	 * 数据源名称
	 */
	private String name;
	/**
	 * 是否默认数据源
	 */
	private boolean master;

	DataSourceEnum(String name, boolean master) {
		this.name = name;
		this.master = master;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMaster() {
		return master;
	}

	public void setMaster(boolean master) {
		this.master = master;
	}

	public String getDefault() {
		String defaultDataSource = "";
		for (DataSourceEnum dataSourceEnum : DataSourceEnum.values()) {
			if (!"".equals(defaultDataSource)) {
				break;
			}
			if (dataSourceEnum.master) {
				defaultDataSource = dataSourceEnum.getName();
			}
		}
		return defaultDataSource;
	}

}
