package com.lambo.ndp.service.imp;


import com.lambo.ndp.dao.api.TableMapper;
import com.lambo.ndp.model.Table;
import com.lambo.ndp.model.TableCell;
import com.lambo.ndp.service.api.TableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
* UpmsLogService实现
* Created by lambo on 2017/3/20.
*/
@Transactional
@Service
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
    public int insertTable(Table table){
         return tableMapper.insertTable(table);
    }
    public int insertTableCell(TableCell tableCell){
        return tableMapper.insertTableCell(tableCell);
    }
    public int updateTable(Map<String, Object> param){
      return tableMapper.updateTable(param);
    }

    public int deleteByPrimaryKey(int ids){
    return tableMapper.deleteByPrimaryKey(ids);
    }
    public int deleteTableCellByPrimaryKey(int cellId){
        return tableMapper.deleteTableCellByPrimaryKey(cellId);
    }
    public int deleteTableCellByTableId(int tableId){
        return tableMapper.deleteTableCellByTableId(tableId);
    }
    public int deleteTableByTableId(int tableId){
        return tableMapper.deleteTableByTableId(tableId);
    }
    public Table selectByPrimaryKey(int id){
        return tableMapper.selectByPrimaryKey(id);
    }
    public List<Map<String,Object>> queryDbTable(Map<String, Object> param){
        return tableMapper.queryDbTable(param);
    }
    public List<Map<String,Object>> queryDbTableColumns(Map<String, Object> param){
        return tableMapper.queryDbTableColumns(param);
    }
}