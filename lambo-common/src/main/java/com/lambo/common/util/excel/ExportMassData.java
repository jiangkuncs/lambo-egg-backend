//package com.lambo.common.util.excel;
//
//import java.text.MessageFormat;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.lc.v6.jdbc.mybatis.V6SqlSessionUtil;
//import com.v6.base.tool.excel.CellBean;
//import com.v6.base.tool.excel.GenerateXSSFExcel;
//import com.v6.base.tool.excel.RowBean;
//import com.v6.base.tool.excel.SheetBean;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.mybatis.spring.SqlSessionTemplate;
//
///**
// * @description 海量数据Workbook生成
// * @author SmartMan
// *
// */
//public class ExportMassData{
//
//	private static Log log = LogFactory.getLog(ExportMassDataCmd.class);
//	SqlSessionTemplate sst = V6SqlSessionUtil.getSqlSession("sqlSessionFactory");
//
//	GenerateXSSFExcel g = new GenerateXSSFExcel();
//
//	/**
//	 * @description 返回Workbook对象
//	 * @param sql
//	 * @param headArr
//	 * @param fileName
//	 * @return
//	 * @throws Exception
//	 */
//	public Workbook CreateWorkBook(String sql,JSONArray headArr,String fileName) throws Exception {
//		/**
//		 * 初始化sheet页
//		 */
//		SheetBean sb = new SheetBean();
//
//		/**
//		 * 初始化表头
//		 */
//		List<CellBean> headCellList = new ArrayList<CellBean>();
//		for(int i=0,j=headArr.length(); i<j; i++){
//			JSONObject obj = headArr.getJSONObject(i);
//			CellBean cb = new CellBean();
//			cb.setValue(obj.get("text"));
//			cb.setAlign("center");
//			cb.setBorder(true);
//			headCellList.add(cb);
//		}
//
//		RowBean rb = new RowBean();
//		rb.setCellList(headCellList);
//
//		List<RowBean> headRowList = new ArrayList<RowBean>();
//		headRowList.add(rb);
//
//		sb.setIndex(1);
//    	sb.setName("sheet1");
//
//    	//写入头信息--结束
//    	g.processWorkSheet(sb, headRowList);
//
//
//		//分批执行sql将结果追加到sheet中
//		processExcuteSql(sb,headArr,0,10000,sql);
//
//		Workbook book = g.getWorkbook();
//
//		long endTime = System.currentTimeMillis();
//
//		return book;
//	}
//
//	/**
//	 * @throws Exception
//	* @Title: getMassDataProcess
//	* @Description: 分批执行sql将结果追加到sheet中
//	* @param @param start
//	* @param @param end
//	* @param @return
//	* @return List
//	* @throws
//	 */
//	public List processExcuteSql(SheetBean sb,JSONArray headArr,int start,int size,String sql) throws Exception{
//
//		long beginTime1 = System.currentTimeMillis();
//
//		log.debug(MessageFormat.format("起始行号：{0}--->执行SQL开始---->  最大内存: {1}m  已分配内存: {2}m  已分配内存中的剩余空间: {3}m  最大可用内存: {4}m",
//				start,
//				Runtime.getRuntime().maxMemory()/1024/1024, Runtime.getRuntime().totalMemory()/1024/1024, Runtime.getRuntime().freeMemory()/1024/1024,
//				(Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory())/1024/1024));
//
//
//		Map parmMap = new HashMap();
//		parmMap.put("start", start);
//		parmMap.put("end", start+size);
//		parmMap.put("sql", sql);
//
//		List<Map<String,String>> dataList=sst.selectList("exportexcel.getMassDataProcess",parmMap);
//
//		long endTime1 = System.currentTimeMillis();
//
//		log.debug(MessageFormat.format("起始行号：{0}--->执行SQL结束----> 用时:{1}s  最大内存: {2}m  已分配内存: {3}m  已分配内存中的剩余空间: {4}m  最大可用内存: {5}m",
//				start,
//				(endTime1-beginTime1)/1000,
//				Runtime.getRuntime().maxMemory()/1024/1024, Runtime.getRuntime().totalMemory()/1024/1024, Runtime.getRuntime().freeMemory()/1024/1024,
//				(Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory())/1024/1024));
//
//		long beginTime2 = System.currentTimeMillis();
//
//		List<RowBean> rList = new ArrayList<RowBean>();
//
//		for(int i =0 ;i<dataList.size();i++){
//			Map<String,?> tempMap = dataList.get(i);
//
//			RowBean rb = new RowBean();
//			List<CellBean> cList = new  ArrayList<CellBean>();
//
//			for(int m=0,n=headArr.length(); m<n; m++){
//				JSONObject obj = headArr.getJSONObject(m);
//				Object value = tempMap.get(obj.get("key"));
//				CellBean cb = new CellBean();
//				cb.setValue(value);
//    			cList.add(cb);
//
//			};
//			rb.setCellList(cList);
//    		rList.add(rb);
//		}
//
//		//分批次追加生成excel
//		g.processWorkSheet(sb, rList);
//
//		dataList=null;
//
//		long endTime2 = System.currentTimeMillis();
//
//		log.debug(MessageFormat.format("起始行号：{0}--->追加到EXCEL结束----> 用时:{1}s  最大内存: {2}m  已分配内存: {3}m  已分配内存中的剩余空间: {4}m  最大可用内存: {5}m",
//				start,
//				(endTime2-beginTime2)/1000,
//				Runtime.getRuntime().maxMemory()/1024/1024, Runtime.getRuntime().totalMemory()/1024/1024, Runtime.getRuntime().freeMemory()/1024/1024,
//				(Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory())/1024/1024));
//
//		if(rList != null && rList.size()!=0){
//			rList=null;
//			processExcuteSql(sb,headArr,start+size,size,sql);
//		}
//
//		return dataList;
//	}
//}