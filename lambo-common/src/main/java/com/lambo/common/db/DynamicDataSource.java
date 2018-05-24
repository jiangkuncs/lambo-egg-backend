package com.lambo.common.db;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源（数据源切换）
 * Created by lambo on 2017/1/15.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	private final static Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

	@Override
	protected Object determineCurrentLookupKey() {
		String dataSource = getDataSource();
		if(StringUtils.isNotBlank(dataSource)){
			logger.debug("当前数据源已切换至:"+dataSource);
		}
		return dataSource;
	}

	/**
	 * 设置数据源
	 * @param dataSource
	 */
	public static void setDataSource(String dataSource) {
		contextHolder.set(dataSource);
	}

	/**
	 * 获取数据源
	 * @return
	 */
	public static String getDataSource() {
		String dataSource = contextHolder.get();
		return dataSource;
	}

	/**
	 * 清除数据源
	 */
	public static void clearDataSource() {
		if(logger.isDebugEnabled()){
			logger.debug("已清空数据源设置");
		}
		contextHolder.remove();
	}

}
