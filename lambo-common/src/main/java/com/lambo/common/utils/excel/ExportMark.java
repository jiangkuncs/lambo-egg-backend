package com.lambo.common.utils.excel;

import java.util.HashMap;
import java.util.Map;

public class ExportMark{
	/**
	 * key:此次导出的唯一标识码；value:是否导出完成"ok"标识完成
	 */
	static Map<String,String> excelMark = new HashMap<String,String>();
	/**
	 * 导出完成标识
	 */
	static String complete = "ok";
}