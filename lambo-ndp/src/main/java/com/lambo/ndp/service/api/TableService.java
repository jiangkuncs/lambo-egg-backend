package com.lambo.ndp.service.api;

import com.lambo.common.base.BaseService;
import com.lambo.ndp.model.DemoLog;
import com.lambo.ndp.model.DemoLogExample;
import com.lambo.ndp.model.Table;
import com.lambo.ndp.model.TableCell;

import java.util.List;
import java.util.Map;

/**
* UpmsLogService接口
* Created by lambo on 2017/3/20.
*/
public interface TableService  {
    //public int insertTable(Map<String, Object> param);
    public int insertTable(Table table);
    public int insertTableCell(TableCell tableCell);
    public int updateTable(Map<String, Object> param);
    List<Map<String,Object>> queryDbTable(Map<String, Object> param);
    public int deleteByPrimaryKey(int ids);
    public int deleteTableCellByPrimaryKey(int cellId);
    public int deleteTableCellByTableId(int tableId);
    public int deleteTableByTableId(int tableId);
    public List<Map<String,Object>> queryTable(Map<String, Object> param);
    public List<Map<String,Object>> queryDbTableColumns(Map<String, Object> param);
    public List<Map<String,Object>> queryTableCell(int tableId);
    public Table selectByPrimaryKey(int tableId);

}