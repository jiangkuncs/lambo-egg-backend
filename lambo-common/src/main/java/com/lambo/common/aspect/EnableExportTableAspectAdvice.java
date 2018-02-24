package com.lambo.common.aspect;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.lambo.common.annotation.EnableExportTable;
import com.lambo.common.util.excel.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.*;

/**
 * @author wangjierj
 */
@Component
@Aspect
public class EnableExportTableAspectAdvice {

    private static Logger logger = LoggerFactory.getLogger(EnableExportTableAspectAdvice.class);

    @Around(value = "com.lambo.common.architecture.Architecture.anyMethod() && @annotation(enableExportTable)")
    public Object enableExportTable(ProceedingJoinPoint jp, EnableExportTable enableExportTable) throws Throwable {

        Logger log = LoggerFactory.getLogger(jp.getTarget().getClass());
        Object[] args = jp.getArgs();

        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (args.length == 0 || null == req) {
            if(log.isErrorEnabled()){
                log.error("导出excel方法的传入参数错误");
            }
            throw new RuntimeException("导出excel方法的传入参数错误");
        }

        HttpServletResponse rep = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        String exportParams = req.getParameter(Constants.exportParams);
        if( null != exportParams && !"".equals(exportParams)) {
            if (rep != null) {
                JSONObject params = JSONObject.parseObject(exportParams);
                String fileFormat = params.getString(Constants.fileFormat);
                String fileName = params.getString(Constants.fileName);
                String exportType = params.getString(Constants.exportType);
                if(exportType == null || "".equals(exportType)){
                    exportType = Constants.current;
                }
                if(null == fileName || "".equals(fileName)){
                    fileName = Constants.defaultName;
                }
                GenerateXSSFExcel g = new GenerateXSSFExcel();
                SheetBean sb = new SheetBean();
                sb.setIndex(0);
                sb.setName(fileName);
                JSONArray columns = params.getJSONArray(Constants.tableColumns);
                if(exportType.equals(Constants.all)){
                    //处理分页
                    int count = 0;
                    int limit = 1000;
                    while (true){
                        Object o = null;
                        try{
                            args[0] = count;
                            args[1] = limit;
                            o = jp.proceed(args);
                        }catch(Throwable t){
                            throw t;
                        }
                        if(count > 0){
                            createExportBook(g,sb,columns,o,false);
                        }else{
                            createExportBook(g,sb,columns,o,true);
                        }
                        count++;
                        if(o != null && o instanceof Map){
                            Map resultMap = (Map)o;
                            Integer total = Integer.parseInt(resultMap.get(Constants.total) + "");
                            if(total < count * limit){
                                break;
                            }
                        }
                    }
                }else{
                    Object o = null;
                    try{
                        o = jp.proceed(args);
                    }catch(Throwable t){
                        throw t;
                    }
                    createExportBook(g,sb,columns,o,true);
                }

                if(null == fileFormat || "".equals(fileFormat)){
                    fileFormat = Constants.defaultType;
                }
                if(req.getHeader("user-agent").contains("MSIE") || req.getHeader("user-agent").contains("rv:11")) {
                    fileName = java.net.URLEncoder.encode(fileName,"utf-8") + "." + fileFormat;
                }else{
                    fileName = new String(fileName.getBytes("utf-8"),"iso-8859-1") + "." + fileFormat;
                }
                ServletOutputStream servletoutputstream = rep.getOutputStream();
                rep.setHeader("Content-disposition", "attachment; filename=" + fileName);
                rep.setDateHeader("Expires", 0);
                rep.setContentType("application/vnd.ms-excel;charset=utf-8");
                g.write(servletoutputstream);
                servletoutputstream.flush();
                servletoutputstream.close();
                return null;
            }else{
                if(log.isErrorEnabled()){
                    log.error("使用@EnableExportTable注解的导出功能时获取HttpServletResponse rep参数异常");
                }
                throw new RuntimeException("使用@EnableExportTable注解的导出功能时获取HttpServletResponse rep参数异常");
            }
        }else{
            Object o = null;
            try{
                o = jp.proceed(args);
            }catch(Throwable t){
                throw t;
            }
            return o;
        }
    }

    /**
     * 常见excel表格内容
     * @param g
     * @param sb
     * @param columns
     * @param data
     * @param writeHead
     * @throws Throwable
     */
    private void createExportBook(GenerateXSSFExcel g,SheetBean sb,JSONArray columns,Object data,boolean writeHead)throws Throwable{
        List<RowBean> rList = new ArrayList<RowBean>();
        if(writeHead){
            RowBean rHead = new RowBean();
            List<CellBean> cHeadList = new ArrayList<CellBean>();
            for(int i = 0; i < columns.size(); i++){
                JSONObject column = columns.getJSONObject(i);
                CellBean cHead = new CellBean();
                cHead.setValue(column.get("title"));
                cHeadList.add(cHead);
            }
            rHead.setCellList(cHeadList);
            rList.add(rHead);
            g.processWorkSheet(sb, rList);
            rList.clear();
        }
        if(data instanceof Map){
            Map resultMap = (Map)data;
            List rows = (List)resultMap.get(Constants.rows);
            JSONArray rowsList = new JSONArray(rows);
            for(int i = 0; i < rowsList.size(); i++){
                JSONObject row = rowsList.getJSONObject(i);
                RowBean rb = new RowBean();
                List<CellBean> cList = new ArrayList<CellBean>();
                for(int j = 0; j < columns.size(); j++){
                    CellBean cb = new CellBean();
                    JSONObject column = columns.getJSONObject(j);
                    String key = column.getString("key");
                    Object value = row.get(key);
                    JSONObject enums = column.getJSONObject("enums");
                    if(null != enums && !enums.isEmpty()){
                        value = enums.get(value+"");
                    }
                    cb.setValue(value);
                    cList.add(cb);
                }
                rb.setCellList(cList);
                rList.add(rb);
            }
            g.processWorkSheet(sb, rList);
        }
    }
}
