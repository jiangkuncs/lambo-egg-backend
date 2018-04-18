package com.lambo.ndp.service.imp;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.ndp.dao.api.CategoryOverviewMapper;
import com.lambo.ndp.model.CategoryOverview;
import com.lambo.ndp.model.CategoryOverviewExample;
import com.lambo.ndp.service.api.CategoryOverviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CategoryOverview服务
 * @author sunzhen
 * @date 2018/4/13
 */
@Service
@Transactional
@BaseService
public class CategoryOverviewServiceImpl extends BaseServiceImpl<CategoryOverviewMapper, CategoryOverview, CategoryOverviewExample> implements CategoryOverviewService {
}
