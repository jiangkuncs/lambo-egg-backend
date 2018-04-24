package com.lambo.ndp.dao.api;

import java.util.List;
import java.util.Map;

public interface OverviewMapper {
    List<Map<String,Object>> queryOverview(Map<String, Object> param);
}