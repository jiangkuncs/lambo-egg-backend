package com.lambo.ndp.service.api;

import com.lambo.common.base.BaseService;

import java.util.Map;


/**
 * SubjectService接口
 * Created by zxc on 2018/3/15.
*/
public interface OverviewService extends BaseService<Map, Map> {
    public Map<String,Object> getMeasures(Map<String, Object> param);
    public Map<String,Object> getSaleProcess(Map<String, Object> param);
    public Map<String,Object> getSaleSameRate(Map<String, Object> param);
    public Map<String,Object> getBoxPrice(Map<String, Object> param);
    public Map<String,Object> getCustNum(Map<String, Object> param);
    public Map<String,Object> getBigCust(Map<String, Object> param);
    public Map<String,Object> getSelfregGroup(Map<String, Object> param);
    public Map<String,Object> getTerminalBulid(Map<String, Object> param);
}