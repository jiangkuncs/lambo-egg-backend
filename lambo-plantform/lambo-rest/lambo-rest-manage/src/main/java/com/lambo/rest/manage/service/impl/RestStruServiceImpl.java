package com.lambo.rest.manage.service.impl;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.rest.manage.dao.api.RestStruMapper;
import com.lambo.rest.manage.model.RestStru;
import com.lambo.rest.manage.model.RestStruExample;
import com.lambo.rest.manage.service.api.RestStruService;
import org.springframework.stereotype.Service;

/**
 * RestStruService实现
 *
 */
@Service
@BaseService
public class RestStruServiceImpl extends BaseServiceImpl<RestStruMapper, RestStru, RestStruExample> implements RestStruService {
}
