package com.lambo.ndp.service.imp;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.ndp.dao.api.OverviewMapper;
import com.lambo.ndp.service.api.OverviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OverviewService实现
 * Created by zxc on 2018/2/28.
*/
@Service
@Transactional
@BaseService
public class OverviewServiceImpl extends BaseServiceImpl<OverviewMapper,Map, Map> implements OverviewService {

    private static Logger logger = LoggerFactory.getLogger(OverviewServiceImpl.class);

    @Autowired
    OverviewMapper overviewMapper;

    public Map<String,Object> getCategoryOverview(Map<String, Object> param){
        return overviewMapper.getCategoryOverview(param);
    }

    public Map<String,Object> getMeasures(Map<String, Object> param){
        Map<String, Object> result = new HashMap<>();

        result.put("SALE_QTY", 10000);
        result.put("SALE_PROGRESS", 26);
        result.put("BOX_PRICE", 10000);
        result.put("BOX_PRICE_GROWTH", 26);
        result.put("CUST_NUM", 10000);
        result.put("CUST_NUM_GROWTH", 26);
        result.put("ITEM_NUM", 1256);
        result.put("BRAND_NUM", 95);
//        overviewMapper.queryOverview(param);
        return result;
    }

    public Map<String,Object> getSaleProcess(Map<String, Object> param){
        Map<String, Object> result = new HashMap<>();

        List<Map<String, Object>> tableColumn = new ArrayList<>();
        Map<String, Object> column = new HashMap<>();
        column.put("title", "排名");
        column.put("key", "rank");
        tableColumn.add(column);
        column = new HashMap<>();
        column.put("title", "省份");
        column.put("key", "name");
        tableColumn.add(column);
        column = new HashMap<>();
        column.put("title", "任务");
        column.put("key", "measure1");
        tableColumn.add(column);
        column = new HashMap<>();
        column.put("title", "完成");
        column.put("key", "measure2");
        tableColumn.add(column);
        column = new HashMap<>();
        column.put("title", "进度");
        column.put("key", "value");
        tableColumn.add(column);

        List<Map<String, Object>> tableData = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();
        data.put("rank", "1");
        data.put("name", "北京");
        data.put("measure1", "2563");
        data.put("measure2", "1025");
        data.put("value", "66");
        tableData.add(data);
        data = new HashMap<>();
        data.put("rank", "2");
        data.put("name", "大连");
        data.put("measure1", "2563");
        data.put("measure2", "1025");
        data.put("value", "65");
        tableData.add(data);
        data = new HashMap<>();
        data.put("rank", "3");
        data.put("name", "山东");
        data.put("measure1", "2563");
        data.put("measure2", "1025");
        data.put("value", "64");
        tableData.add(data);
        data = new HashMap<>();
        data.put("rank", "4");
        data.put("name", "山西");
        data.put("measure1", "2563");
        data.put("measure2", "1025");
        data.put("value", "63");
        tableData.add(data);
        data = new HashMap<>();
        data.put("rank", "5");
        data.put("name", "湖北");
        data.put("measure1", "2563");
        data.put("measure2", "1025");
        data.put("value", "62");
        tableData.add(data);
        data = new HashMap<>();
        data.put("rank", "");
        data.put("name", "时间进度");
        data.put("measure1", "2563");
        data.put("measure2", "1025");
        data.put("value", "60");
        tableData.add(data);

        result.put("tableColumn", tableColumn);
        result.put("tableData", tableData);
//        overviewMapper.queryOverview(param);
        return result;
    }

    public Map<String,Object> getSaleSameRate(Map<String, Object> param){
        Map<String, Object> result = new HashMap<>();

        List<Map<String, Object>> tableColumn = new ArrayList<>();
        Map<String, Object> column = new HashMap<>();
        column.put("title", "排名");
        column.put("key", "rank");
        tableColumn.add(column);
        column = new HashMap<>();
        column.put("title", "省份");
        column.put("key", "name");
        tableColumn.add(column);
        column = new HashMap<>();
        column.put("title", "任务1");
        column.put("key", "measure1");
        tableColumn.add(column);
        column = new HashMap<>();
        column.put("title", "完成");
        column.put("key", "measure2");
        tableColumn.add(column);
        column = new HashMap<>();
        column.put("title", "进度");
        column.put("key", "value");
        tableColumn.add(column);

        List<Map<String, Object>> tableData = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();
        data.put("rank", "1");
        data.put("name", "北京");
        data.put("measure1", "2563");
        data.put("measure2", "1025");
        data.put("value", "66");
        tableData.add(data);
        data = new HashMap<>();
        data.put("rank", "2");
        data.put("name", "大连");
        data.put("measure1", "2563");
        data.put("measure2", "1025");
        data.put("value", "65");
        tableData.add(data);
        data = new HashMap<>();
        data.put("rank", "3");
        data.put("name", "山东");
        data.put("measure1", "2563");
        data.put("measure2", "1025");
        data.put("value", "74");
        tableData.add(data);
        data = new HashMap<>();
        data.put("rank", "4");
        data.put("name", "山西");
        data.put("measure1", "2563");
        data.put("measure2", "1025");
        data.put("value", "63");
        tableData.add(data);
        data = new HashMap<>();
        data.put("rank", "5");
        data.put("name", "湖北");
        data.put("measure1", "2563");
        data.put("measure2", "1025");
        data.put("value", "62");
        tableData.add(data);
        data = new HashMap<>();
        data.put("rank", "");
        data.put("name", "时间进度");
        data.put("measure1", "2563");
        data.put("measure2", "1025");
        data.put("value", "60");
        tableData.add(data);

        result.put("tableColumn", tableColumn);
        result.put("tableData", tableData);
//        overviewMapper.queryOverview(param);
        return result;
    }

    public Map<String,Object> getBoxPrice(Map<String, Object> param){
        Map<String, Object> result = new HashMap<>();

        result.put("SALE_QTY", 10000);
        result.put("SALE_PROGRESS", 26);
        result.put("BOX_PRICE", 10000);
        result.put("BOX_PRICE_GROWTH", 26);
        result.put("CUST_NUM", 10000);
        result.put("CUST_NUM_GROWTH", 26);
        result.put("ITEM_NUM", 1256);
        result.put("BRAND_NUM", 95);
//        overviewMapper.queryOverview(param);
        return result;
    }

    public Map<String,Object> getCustNum(Map<String, Object> param){
        Map<String, Object> result = new HashMap<>();

        result.put("SALE_QTY", 10000);
        result.put("SALE_PROGRESS", 26);
        result.put("BOX_PRICE", 10000);
        result.put("BOX_PRICE_GROWTH", 26);
        result.put("CUST_NUM", 10000);
        result.put("CUST_NUM_GROWTH", 26);
        result.put("ITEM_NUM", 1256);
        result.put("BRAND_NUM", 95);
//        overviewMapper.queryOverview(param);
        return result;
    }

    public Map<String,Object> getBigCust(Map<String, Object> param){
        Map<String, Object> result = new HashMap<>();

        result.put("SALE_QTY", 10000);
        result.put("SALE_PROGRESS", 26);
        result.put("BOX_PRICE", 10000);
        result.put("BOX_PRICE_GROWTH", 26);
        result.put("CUST_NUM", 10000);
        result.put("CUST_NUM_GROWTH", 26);
        result.put("ITEM_NUM", 1256);
        result.put("BRAND_NUM", 95);
//        overviewMapper.queryOverview(param);
        return result;
    }

    public Map<String,Object> getSelfregGroup(Map<String, Object> param){
        Map<String, Object> result = new HashMap<>();

        result.put("SALE_QTY", 10000);
        result.put("SALE_PROGRESS", 26);
        result.put("BOX_PRICE", 10000);
        result.put("BOX_PRICE_GROWTH", 26);
        result.put("CUST_NUM", 10000);
        result.put("CUST_NUM_GROWTH", 26);
        result.put("ITEM_NUM", 1256);
        result.put("BRAND_NUM", 95);
//        overviewMapper.queryOverview(param);
        return result;
    }

    public Map<String,Object> getTerminalBulid(Map<String, Object> param){
        Map<String, Object> result = new HashMap<>();

        result.put("SALE_QTY", 10000);
        result.put("SALE_PROGRESS", 26);
        result.put("BOX_PRICE", 10000);
        result.put("BOX_PRICE_GROWTH", 26);
        result.put("CUST_NUM", 10000);
        result.put("CUST_NUM_GROWTH", 26);
        result.put("ITEM_NUM", 1256);
        result.put("BRAND_NUM", 95);
//        overviewMapper.queryOverview(param);
        return result;
    }

}