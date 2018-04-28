package com.lambo.ndp.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lambo.common.util.excel.Constants;
import com.lambo.ndp.constant.FrontendConstants;
import com.lambo.ndp.dao.api.FrontendMapper;
import com.lambo.ndp.service.api.FrontendService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        boolean hasSubject = (Boolean) param.get("hasSubject");
        List resultList =  frontendMapper.getCategoryList(param);
        if(hasSubject){
            if(resultList != null && resultList.size()>0){
                String categoryIds = "";
                for(int i=0;i<resultList.size();i++){
                    Map obj = (Map)resultList.get(i);
                    categoryIds += obj.get("category_id")+",";
                }
                if(categoryIds.endsWith(",")){
                    categoryIds = categoryIds.substring(0,categoryIds.length()-1);
                }
                param.put("categoryIds",categoryIds);
                List subjectList = frontendMapper.getSubjectList(param);
                Map tmpSubject = new HashMap();
                for(int i=0;i<subjectList.size();i++){
                    Map subject = (Map)subjectList.get(i);
                    String categoryId = subject.get("category_id")+"";
                    if(null != tmpSubject.get(categoryId)){
                        List tmpList = (List)tmpSubject.get(categoryId);
                        tmpList.add(subject);
                        tmpSubject.put(categoryId,tmpList);
                    }else{
                        List tmpList = new ArrayList();
                        tmpList.add(subject);
                        tmpSubject.put(categoryId,tmpList);
                    }
                }
                for(int i=0;i<resultList.size();i++){
                    Map obj = (Map)resultList.get(i);
                    String categoryId = obj.get("category_id")+"";
                    obj.put("children",tmpSubject.get(categoryId));
                }
            }
        }
        return resultList;
    }

    @Override
    public List getSubjectList(Map param) {
        return frontendMapper.getSubjectList(param);
    }

    @Override
    public List getSubjectInfo(Map param){
        /*if(!param.containsKey("is_show")){
            param.put("is_show","1");
        }*/
        return frontendMapper.getSubjectInfo(param);
    }

    @Override
    public Map getTableData(Map param){
        Map result = new HashMap();
        result.put(Constants.rows,new ArrayList());
        result.put(Constants.total,0);
        if(param.containsKey("subject_id")){
            List columnList = getSubjectInfo(param);
            if(columnList!=null && columnList.size()>0){
                String sql = " select";
                String table = "";
                String sort = "";
                String order = "";
                if(param.get("sort") != null && !param.get("sort").equals("")
                        && param.get("order") != null && !param.get("order").equals("")){
                    sort = (String) param.get("sort");
                    order = (String) param.get("order");
                }
                for(int i=0;i<columnList.size();i++){
                    Map column = (Map)columnList.get(i);
                    if(i==0){
                        table = " " + column.get("table_code") + " a0";
                    }
                    sql += " a0."+column.get("cell_code")+",";
                    /*if(null != column.get("dict_id") && !"".equals(column.get("dict_id"))){
                        sql += " d" + i + ".dict_value "+column.get("cell_code")+",";
                        table += " left join ( select dict_id,dict_key,dict_value from ndp_dict where dict_id='" + column.get("dict_id") + "') d"
                        + i + " on a0."+column.get("cell_code")+"=d" + i + ".dict_key";
                    }else{
                        sql += " a0."+column.get("cell_code")+",";
                    }*/
                    if(sort.equalsIgnoreCase((String) column.get("cell_code"))){
                        sort = " a0." + sort;
                    }
                    /*if(column.get("search_condition") != null && column.get("ref_table")!=null){
                        sql += " b" + i +"."+column.get("name_field")+",";
                        if(sort.equalsIgnoreCase((String) column.get("name_field"))){
                            sort = " b" + i +"." + sort;
                        }
                        table += " left join " + column.get("ref_table") +
                        " b"+ i +" on a0."+column.get("cell_code") +"=b"+ i + "." + column.get("key_field");
                    }*/
                }
                if(sql.endsWith(",")){
                    sql = sql.substring(0,sql.length()-1);
                }
                sql += " from " + table;
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
                if (!"".equals(sort)){
                    sql += " order by " + sort + " " + order;
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
                result.put(Constants.rows,page.getList());
                result.put(Constants.total,page.getTotal());
            }
        }
        return result;
    }

    @Override
    public Map getDimensionInfo(Map param){
        Map dimension = null;
        String dimensionId = (String)param.get("dimensionId");
        if(StringUtils.isNotBlank(dimensionId)){
            dimension = frontendMapper.getDimensionInfo(Integer.parseInt(dimensionId));
        }
        return dimension;
    }

    @Override
    public Map getDimensionData(Map param){
        Map result = new HashMap();
        result.put(Constants.rows,new ArrayList());
        result.put(Constants.total,0);
        if (param.get("dimensionId")!=null) {
            String dimensionId = (String)param.get("dimensionId");
            Map dimension = frontendMapper.getDimensionInfo(Integer.parseInt(dimensionId));
            String sql = "";
            String sort = "";
            String order = "";
            if(param.get("sort") != null && !param.get("sort").equals("")
                    && param.get("order") != null && !param.get("order").equals("")){
                sort = (String) param.get("sort");
                order = (String) param.get("order");
            }
            if(dimension!=null){
                sql += " select " + dimension.get("key_field") + " key_field,";
                sql += " " + dimension.get("name_field") + " name_field,";
                sql += " " + dimension.get("show_field");
                sql += " from "+dimension.get("ref_table");
                if(param.containsKey("search")){
                    sql += " where "+dimension.get("name_field")+ " like '%"+param.get("search")+"%'";
                }
                if (!"".equals(sort)){
                    sql += " order by " + sort + " " + order;
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
                result.put(Constants.rows,page.getList());
                result.put(Constants.total,page.getTotal());
            }
        }
        return result;
    }

    @Override
    public Map getConditionData(Map param){
        String dimensionType = (String) param.get("dimensionType");
        Map result = new HashMap();
        result.put(Constants.rows,new ArrayList());
        result.put(Constants.total,0);
        if(StringUtils.isNotBlank(dimensionType)){
            int offset = 0;
            if(param.get("offset") != null && !param.get("offset").equals("")){
                offset = Integer.parseInt(param.get("offset")+"");
            }
            int limit = 10;
            if(param.get("limit") != null && !param.get("limit").equals("")){
                limit = Integer.parseInt(param.get("limit")+"");
            }
            PageHelper.offsetPage(offset,limit);
            List rows = null;
            if(dimensionType.equals(FrontendConstants.province)){
                rows = frontendMapper.getProviceData(param);
            }else if(dimensionType.equals(FrontendConstants.city)){
                rows = frontendMapper.getCityData(param);
            }else if(dimensionType.equals(FrontendConstants.item)){
                rows = frontendMapper.getItemData(param);
            }else if(dimensionType.equals(FrontendConstants.brand)){
                rows = frontendMapper.getBrandData(param);
            }
            PageInfo page = new PageInfo(rows);
            result.put(Constants.rows,page.getList());
            result.put(Constants.total,page.getTotal());
        }
        return result;
    }

    @Override
    public List getSubjectDocuments(Map param) {
        return frontendMapper.getSubjectDocuments(param);
    }
}