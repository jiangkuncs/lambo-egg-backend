package com.lambo.ndp.service.api;


import com.lambo.ndp.model.Table;
import com.lambo.ndp.model.TableCell;
import com.lambo.ndp.model.TableCellDict;

import java.util.List;
import java.util.Map;

/**
* TableService接口
* Created by zxc on 2018/3/20.
*/
public interface TableService  {
    //public int insertTable(Map<String, Object> param);
//    public int insertTable(Table table);
//    public int insertTableCell(TableCell tableCell);
//    public int updateTable(Map<String, Object> param);
    List<Map<String,Object>> queryDbTable(Map<String, Object> param);
//    public int deleteByPrimaryKey(int ids);
    public int deleteTableCellByPrimaryKey(int cellId);
//    public int deleteTableCellByTableId(int tableId);
//    public int deleteTableByTableId(int tableId);
    public Object deleteTable(int tableId);
    public List<Map<String,Object>> queryTable(Map<String, Object> param);
    public List<Map<String,Object>> queryDbTableColumns(Map<String, Object> param);
    public List<Map<String,Object>> queryTableCell(int tableId);
    public Object queryTableColumns(String id);
//    public Table selectByPrimaryKey(int tableId);
    public Object create(String tableCode,String tableName,String tableDesc,String TableCellss);
    public Object update(int tableId,String tableCode,String tableName,String tableDesc,String TableCellss);
    public Object get(int id);
}