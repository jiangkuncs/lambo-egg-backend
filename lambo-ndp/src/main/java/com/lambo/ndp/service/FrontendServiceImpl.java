package com.lambo.ndp.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lambo.ndp.dao.DemoUserOldMapper;
import com.lambo.ndp.dao.FrontendMapper;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* DemoUserService实现
* Created by wangjie on 2017/11/14.
*/
@Service
public class FrontendServiceImpl implements FrontendService {

    private static Logger logger = LoggerFactory.getLogger(FrontendServiceImpl.class);

    @Autowired
    private FrontendMapper frontendMapper;

    @Override
    public List getCategoryList(Map param) {
        return frontendMapper.getCategoryList(param);
    }

    @Override
    public List getSubjectList(Map param) {
        return frontendMapper.getSubjectList(param);
    }

    @Override
    public List getSubjectInfo(Map param){
        if(!param.containsKey("is_show")){
            param.put("is_show","1");
        }
        return frontendMapper.getSubjectInfo(param);
    }

    @Override
    public Map getTableData(Map param){
        String sql = " select";
        if(param.containsKey("subject_id")){
            List columnList = getSubjectInfo(param);
            if(columnList!=null && columnList.size()>0){
                String table = "";
                for(int i=0;i<columnList.size();i++){
                    Map column = (Map)columnList.get(i);
                    sql += " a0."+column.get("cell_code")+",";
                    if(i==0){
                        table = " " + column.get("table_code") + " a0";
                    }
                    if(column.get("search_condition") != null && column.get("ref_table")!=null){
                        sql += " b" + i +"."+column.get("name_field")+",";
                        table += " left join " + column.get("ref_table") +
                        " b"+ i +" on a0."+column.get("cell_code") +"=b"+ i + "." + column.get("key_field");
                    }
                }
                if(sql.endsWith(",")){
                    sql = sql.substring(0,sql.length()-1);
                }
                sql += " from " + table;
            }else{
                Map result = new HashMap();
                result.put("rows",new ArrayList());
                result.put("total",0);
                return result;
            }
        }
        sql += " where 1=1";
        if(param.containsKey("params")){
            String params = (String)param.get("params");
            if(params.endsWith(",")){
                params = params.substring(0,params.length()-1);
            }
            String[] wheres = null;
            if(params.indexOf(",")>=0){
                wheres = params.split(",");
            }else{
                wheres = new String[]{params};
            }
            for(int i=0;i<wheres.length;i++){
                sql += " and a0."+wheres[i];
            }
        }
        Map finalParam = new HashMap();
        finalParam.put("sql",sql);
        int offset = 0;
        if(param.get("offset") != null && !param.get("offset").equals("")){
            offset = Integer.parseInt(param.get("offset")+"");
        }
        int limit = 10;
        if(param.get("limit") != null && !param.get("limit").equals("")){
            limit = Integer.parseInt(param.get("limit")+"");
        }
        PageHelper.offsetPage(offset,limit);
        List list = frontendMapper.getSqlData(finalParam);
        PageInfo page = new PageInfo(list);
        Map result = new HashMap();
        result.put("rows",page.getList());
        result.put("total",page.getTotal());
        return result;
    }

    @Override
    public Map getDimensionData(Map param){
        Map result = new HashMap();
        result.put("rows",new ArrayList());
        result.put("total",0);
        if (param.get("dimensionId")!=null) {
            String dimensionId = (String)param.get("dimensionId");
            Map dimension = frontendMapper.getDimensionInfo(Integer.parseInt(dimensionId));
            String sql = "";
            if(dimension!=null){
                sql += " select " + dimension.get("key_field") + " key_field,";
                sql += " " + dimension.get("name_field") + " name_field,";
                sql += " " + dimension.get("show_field");
                sql += " from "+dimension.get("ref_table");
                if(param.containsKey("search")){
                    sql += " where "+dimension.get("name_field")+ " like '%"+param.get("search")+"%'";
                }

                Map finalParam = new HashMap();
                finalParam.put("sql",sql);
                int offset = 0;
                if(param.get("offset") != null && !param.get("offset").equals("")){
                    offset = Integer.parseInt(param.get("offset")+"");
                }
                int limit = 10;
                if(param.get("limit") != null && !param.get("limit").equals("")){
                    limit = Integer.parseInt(param.get("limit")+"");
                }
                PageHelper.offsetPage(offset,limit);
                List rows =  frontendMapper.getSqlData(finalParam);
                PageInfo page = new PageInfo(rows);
                result.put("rows",page.getList());
                result.put("total",page.getTotal());
            }
        }
        return result;
    }
}