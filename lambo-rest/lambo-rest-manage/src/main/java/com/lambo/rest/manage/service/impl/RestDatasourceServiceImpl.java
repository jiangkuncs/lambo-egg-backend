package com.lambo.rest.manage.service.impl;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.rest.manage.dao.api.RestDatasourceMapper;
import com.lambo.rest.manage.model.RestDatasource;
import com.lambo.rest.manage.model.RestDatasourceExample;
import com.lambo.rest.manage.service.api.RestDatasourceService;
import org.springframework.stereotype.Service;

/**
 * RestDatasourceServiceImpl实现
 *
 */
@Service
@BaseService
public class RestDatasourceServiceImpl extends BaseServiceImpl<RestDatasourceMapper, RestDatasource, RestDatasourceExample> implements RestDatasourceService {
}
