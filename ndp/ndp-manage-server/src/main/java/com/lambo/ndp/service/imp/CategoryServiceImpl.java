package com.lambo.ndp.service.imp;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.ndp.controller.DictController;
import com.lambo.ndp.dao.api.CategoryMapper;
import com.lambo.ndp.model.Category;
import com.lambo.ndp.model.CategoryExample;
import com.lambo.ndp.service.api.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CategoryService实现
 * Created by zxc on 2018/2/28.
*/
@Service
@BaseService
public class CategoryServiceImpl extends BaseServiceImpl<CategoryMapper, Category, CategoryExample> implements CategoryService {

    private static Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

}