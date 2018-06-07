package com.lambo.rest.client.factory.sqlformat;

/**
 * 参数幽灵——还原者
 */
public interface ParamGhost {
	/**
	 * 还原实际参数
	 * @param value 具体值
	 * @return 替换后的上下文字符
	 */
	String recover(String value);
}
