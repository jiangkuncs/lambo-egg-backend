package com.lambo.ndp.service.imp;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.lambo.common.annotation.BaseService;
import com.lambo.common.validator.LengthValidator;
import com.lambo.ndp.constant.NdpResult;
import com.lambo.ndp.constant.NdpResultConstant;
import com.lambo.ndp.dao.api.TableMapper;
import com.lambo.ndp.model.Table;
import com.lambo.ndp.model.TableCell;
import com.lambo.ndp.service.api.TableService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* TableService实现
* Created by zxc on 2018/3/10.
*/
@Service
@Transactional
public class TableServiceImpl implements TableService {

    private static Logger logger = LoggerFactory.getLogger(TableServiceImpl.class);

    @Autowired
    TableMapper tableMapper;

    public List<Map<String,Object>> queryTable(Map<String,Object> param) {
        return tableMapper.queryTable(param);
    }
    public List<Map<String,Object>> queryTableCell(int tableId) {
        return tableMapper.queryTableCell(tableId);
    }
//    public int insertTable(Table table){
//         return tableMapper.insertTable(table);
//    }
//    public int insertTableCell(TableCell tableCell){
//        return tableMapper.insertTableCell(tableCell);
//    }
//    public int updateTable(Map<String, Object> param){
//      return tableMapper.updateTable(param);
//    }
//
//    public int deleteByPrimaryKey(int ids){
//    return tableMapper.deleteByPrimaryKey(ids);
//    }
    public int deleteTableCellByPrimaryKey(int cellId){
        return tableMapper.deleteTableCellByPrimaryKey(cellId);
    }
//    public int deleteTableCellByTableId(int tableId){
//        return tableMapper.deleteTableCellByTableId(tableId);
//    }
//    public int deleteTableByTableId(int tableId){
//        return tableMapper.deleteTableByTableId(tableId);
//    }
//    public Table selectByPrimaryKey(int id){
//        return tableMapper.selectByPrimaryKey(id);
//    }
    public List<Map<String,Object>> queryDbTable(Map<String, Object> param){
        return tableMapper.queryDbTable(param);
    }
    public List<Map<String,Object>> queryDbTableColumns(Map<String, Object> param){
        return tableMapper.queryDbTableColumns(param);
    }
    public Object create(String tableCode,String tableName,String tableDesc,String TableCellss){
        ComplexResult result = FluentValidator.checkAll()
                .on(tableCode, new LengthValidator(1, 50, "表名"))
                .on(tableName, new LengthValidator(0, 50, "中文名"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new NdpResult(NdpResultConstant.INVALID_LENGTH, result.getErrors());
        }
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Table table=new Table();
        table.setCreateTime(df.format(day).toString());
        table.setCreateUser(username);
        table.setTableCode(tableCode);
        table.setTableDesc(tableDesc);
        table.setTableName(tableName);
        int con =tableMapper.insertTable(table);
        if (con!=1) {
            return new NdpResult(NdpResultConstant.INVALID_LENGTH, result.getErrors());
        }
        int tableId=table.getTableId();
        //System.out.println("tableId="+tableId);
        //System.out.println("TableCellss:"+TableCellss);
        JSONArray json = JSONArray.parseArray(TableCellss);
        //System.out.println("json:"+json);
        if(json.size()>0){
            for(int i=0;i<json.size();i++){
                JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                System.out.println("job:"+job) ;  // 得到 每个对象中的属性值
                TableCell tableCell=new TableCell();
                tableCell.setTableId(tableId);
                tableCell.setCellCode((String) job.get("cellCode"));
                tableCell.setCellName((String) job.get("cellName"));
                tableCell.setDictId((String) job.get("dictId"));
                tableCell.setDataDesc((String) job.get("dataDesc"));
                tableCell.setDataUnit((String) job.get("dataUnit"));
                int conCell =tableMapper.insertTableCell(tableCell);

            }
        }
        return new NdpResult(NdpResultConstant.SUCCESS, con);
    }
    public Object update(int tableId,String tableCode,String tableName,String tableDesc,String TableCellss){
        int count = 0;
        ComplexResult result = FluentValidator.checkAll()
                .on(tableCode, new LengthValidator(1, 50, "表名"))
                .on(tableName, new LengthValidator(0, 50, "中文名"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new NdpResult(NdpResultConstant.INVALID_LENGTH, result.getErrors());
        }
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        if(tableId > 0){
            Map<String,Object> param = new HashMap<String, Object>();
            Date day=new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            param.put("tableId",tableId);
            param.put("tableCode",tableCode);
            param.put("tableName",tableName);
            param.put("createUser",username);
            param.put("tableDesc",tableDesc);
            param.put("createTime",df.format(day).toString());
            count = tableMapper.updateTable(param);
        }
        //System.out.println("TableCellss:"+TableCellss);
        count = tableMapper.deleteTableCellByTableId(tableId);
        JSONArray json = JSONArray.parseArray(TableCellss);
        //System.out.println("json:"+json);
        if(json.size()>0){
            for(int i=0;i<json.size();i++){
                JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                System.out.println("job:"+job) ;  // 得到 每个对象中的属性值
                TableCell tableCell=new TableCell();
                tableCell.setTableId(tableId);
                tableCell.setCellCode((String) job.get("cellCode"));
                tableCell.setCellName((String) job.get("cellName"));
                tableCell.setDictId((String) job.get("dictId"));
                tableCell.setDataDesc((String) job.get("dataDesc"));
                tableCell.setDataUnit((String) job.get("dataUnit"));
                int conCell =tableMapper.insertTableCell(tableCell);

            }
        }
        return new NdpResult(NdpResultConstant.SUCCESS, count);
    }
    public Object deleteTable(int tableId){
        //System.out.println("tableId="+tableId+",,,"+getType(tableId));
        tableMapper.deleteTableCellByTableId(tableId);
        return tableMapper.deleteTableByTableId(tableId);
    }
    public Object get(int id){
        Map<String,Object> param = tableMapper.selectTableByPrimaryKey(id);
        List data = tableMapper.queryTableCell(id);
        data.add(0,param);
        return new NdpResult(NdpResultConstant.SUCCESS,data);
    }
    public Object queryTableColumns(String id){
        List<Map<String,Object>> data = tableMapper.queryTableColumns(id);
        for(int i=0;i<data.size();i++){
            data.get(i).put("cellName","");
        }
        return new NdpResult(NdpResultConstant.SUCCESS,data);
    }
}