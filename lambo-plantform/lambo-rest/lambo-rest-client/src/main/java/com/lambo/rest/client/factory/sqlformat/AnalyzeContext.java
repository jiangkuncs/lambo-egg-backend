package com.lambo.rest.client.factory.sqlformat;

import java.util.List;

/**
 * 分析环境上下文
 */
public class AnalyzeContext {
	private final static AnalyzeContext context = new AnalyzeContext();
	private SQLAnalyze analyze;
	private AnalyzeContext(){
		init();
	}
	
	private void init() {
		analyze = new SQLAnalyze();
		SQLCompiler compiler = new DefaultSQLCompiler();
		ParamGhost ghost = new DefaultParamGhost();
		compiler.setParamGhost(ghost);
		analyze.setCompiler(compiler);
	}
	
	public String analyze(String sql, List<Object> paramLists){
		
		return analyze.compile(sql, paramLists);
	}
	
	public static AnalyzeContext getContext(){
		return context;
	}
}
