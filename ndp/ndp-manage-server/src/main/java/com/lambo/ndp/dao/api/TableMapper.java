package com.lambo.ndp.dao.api;

import com.lambo.ndp.model.Table;
import com.lambo.ndp.model.TableCell;

import java.util.List;
import java.util.Map;

public interface TableMapper {
    //public int insertTable(Map<String, Object> param);
    public int insertTable(Table table);
    public int insertTableCell(TableCell tableCell);
    public int updateTable(Map<String, Object> param);

    public int deleteByPrimaryKey(int ids);
    public int deleteTableCellByPrimaryKey(int cellId);
    public int deleteTableCellByTableId(int tableId);
    public int deleteTableByTableId(int tableId);
    public List<Map<String,Object>> queryTable(Map<String, Object> param);
    public List<Map<String,Object>> queryDbTable(Map<String, Object> param);
    public List<Map<String,Object>> queryDbTableColumns(Map<String, Object> param);
    public List<Map<String,Object>> queryTableCell(int tableId);
    public List<Map<String,Object>> queryTableColumns(Map<String,Object> id);
    public  Map<String,Object> selectTableByPrimaryKey(int id);
}