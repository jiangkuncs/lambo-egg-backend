package com.lambo.ndp.service.api;


import com.lambo.ndp.model.Table;
import com.lambo.ndp.model.TableCell;
import com.lambo.ndp.model.TableCellDict;

import java.util.List;
import java.util.Map;

/**
* TableService接口
* Created by zxc on 2018/4/20.
*/
public interface TableService  {
    List<Map<String,Object>> queryDbTable(Map<String, Object> param);
    public int deleteTableCellByPrimaryKey(int cellId);
    public Object deleteTable(int tableId);
    public List<Map<String,Object>> queryTable(Map<String, Object> param);
    public List<Map<String,Object>> queryDbTableColumns(String tableName);
    public List<Map<String,Object>> queryTableCell(int tableId);
    public Object queryTableColumns(String tableName);
    public Object create(String tableCode,String tableName,String tableDesc,String TableCellss);
    public Object update(int tableId,String tableCode,String tableName,String tableDesc,String TableCellss);
    public Object get(int id);
}