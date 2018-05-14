package com.lambo.rest.client.factory.sqlformat;

import java.util.List;

/**
 * 预编译SQL转换编译器
 * @date 2012-9-2
 */
public interface SQLCompiler {
	/**
	 * 编译
	 * @param contexts 上下文字符环境
	 * @param values 值
	 * @return List 替换值后的上下文字符
	 * @throws CompileException contexts的长度与values的长度不一致
	 */
	List<String> compile(List<String> contexts, List<Object> values) throws CompileException;

	/**
	 * 设置参数转换者
	 * @param ghost
	 */
	void setParamGhost(ParamGhost ghost);
}
