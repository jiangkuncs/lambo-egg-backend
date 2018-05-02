package com.lambo.ndp.service.imp;


import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.ndp.dao.api.FeedBackMapper;
import com.lambo.ndp.model.FeedBack;
import com.lambo.ndp.model.FeedBackExample;
import com.lambo.ndp.service.api.FeedBackService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
* DictService实现
* Created by zxc on 2018/3/10.
*/
@Service
@Transactional
@BaseService
public class FeedbackServiceImpl extends BaseServiceImpl<FeedBackMapper,FeedBack, FeedBackExample> implements FeedBackService {

}