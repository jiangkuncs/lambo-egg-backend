package com.lambo.ndp.service.imp;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.ndp.dao.api.CategoryMapper;
import com.lambo.ndp.model.Category;
import com.lambo.ndp.model.CategoryExample;
import com.lambo.ndp.service.api.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CategoryService实现
 * Created by zxc on 2018/2/28.
*/
@Service
@Transactional
@BaseService
public class CategoryServiceImpl extends BaseServiceImpl<CategoryMapper, Category, CategoryExample> implements CategoryService {

}