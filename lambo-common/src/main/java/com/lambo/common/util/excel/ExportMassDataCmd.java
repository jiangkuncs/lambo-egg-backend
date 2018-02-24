//package com.lambo.common.util.excel;
//
//import java.io.PrintWriter;
//
//import com.v6.base.cmd.BaseCommandImpl;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.json.JSONArray;
//import org.loushang.util.IErrorHandler;
//import org.loushang.util.IMessageHandler;
//import org.loushang.waf.mvc.ViewHelper;
///**
// * @description 海量数据导出CMD
// * @author SmartMan
// *
// */
//public class ExportMassDataCmd extends BaseCommandImpl {
//
//	private static Log log = LogFactory.getLog(ExportMassDataCmd.class);
//
//	/**
//	 * @description 标识excel导出进度，导出完成时，返回"ok"
//	 * @author SmartMan
//	 * @param req
//	 * @param rep
//	 * @param errorHandler
//	 * @param messageHandler
//	 * @param viewHelper
//	 * @return
//	 */
//	public String exportProgress(HttpServletRequest req,
//			HttpServletResponse rep, IErrorHandler errorHandler,
//			IMessageHandler messageHandler, ViewHelper viewHelper) {
//
//			String mark = req.getParameter("mark");
//			String count = ExportMark.excelMark.get(mark);
//			if( ExportMark.complete.equals(count)){
//				ExportMark.excelMark.remove(mark);
//			}
//			try {
//				PrintWriter out = rep.getWriter();
//				out.write(count);
//				out.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		return "null";
//	}
//
//
//	/**
//	* @throws Exception
//	* @Title: exportExcel
//	* @Description: 导出海量数据excel，
//	* 				req中传递参数：mark-标识此次导出，配合判断是否导出完成
//	* 							 sql-导出数据的sql
//	* 							 head-表头信息
//	* 							 fileName-文件名
//	*
//	* @param req
//	* @param rep
//	* @param errorHandler
//	* @param messageHandler
//	* @param viewHelper
//	* @return String
//	* @throws
//	 */
//	public String exportExcel(HttpServletRequest req, HttpServletResponse rep,
//			IErrorHandler errorHandler, IMessageHandler messageHandler,
//			ViewHelper viewHelper) throws Exception {
//
//		String mark = req.getParameter("mark");//标识一次导出
//		String sql = req.getParameter("sql");//导出数据的sql
//		String head = req.getParameter("head");//表头信息
//		String fileName = req.getParameter("fileName");//文件名
//
//		JSONArray headArr = new JSONArray(head);
//
//		ExportMark.excelMark.put(mark, "0");//标记是否导出完成
//
//		ExportMassData emd = new ExportMassData();
//		Workbook book = emd.CreateWorkBook(sql, headArr, fileName); //生成导出Workbook
//
//		/**
//		 * 文件名称的乱码处理及空处理
//		 */
//		if(fileName != null && !"".equals(fileName)){
//			if(req.getHeader("user-agent").indexOf("MSIE") != -1 || req.getHeader("user-agent").indexOf("rv:11") !=-1) {
//				fileName = java.net.URLEncoder.encode(fileName,"utf-8") + ".xlsx";
//			}else{
//				fileName = new String(fileName.getBytes("utf-8"),"iso-8859-1")+ ".xlsx";
//			}
//		}else{
//			fileName = "表格数据";
//		}
//
//		ServletOutputStream servletoutputstream = rep.getOutputStream();
//		rep.setHeader("Content-disposition", "attachment; filename=" + fileName);
//		rep.setDateHeader("Expires", 0);
//		rep.setContentType("application/vnd.ms-excel;charset=utf-8");
//
//		ExportMark.excelMark.put(mark, ExportMark.complete);//标识导出完成
//
//		book.write(servletoutputstream);
//		servletoutputstream.flush();
//
//		return null;
//	}
//
//}