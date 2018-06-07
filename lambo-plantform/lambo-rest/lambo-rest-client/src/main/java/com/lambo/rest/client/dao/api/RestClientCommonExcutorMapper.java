package com.lambo.rest.client.dao.api;

import java.util.List;
import java.util.Map;

/**
 * @author sunzhen
 */
public interface RestClientCommonExcutorMapper {
    List<Map> select(String sql);
    int insert(String sql);
    int update(String sql);
    int delete(String sql);
}