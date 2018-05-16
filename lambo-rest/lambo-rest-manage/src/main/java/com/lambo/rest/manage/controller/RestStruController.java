package com.lambo.rest.manage.controller;

import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.rest.manage.model.RestStru;
import com.lambo.rest.manage.model.RestStruExample;
import com.lambo.rest.manage.service.api.RestStruService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(value = "rest服务查询", description = "rest服务查询")
@RequestMapping("/manage/rest/stru")
public class RestStruController extends BaseController {
    @Autowired
    RestStruService restStruService;

    @ApiOperation(value = "查询rest服务")
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("查询rest服务")
    public Object get(
            @RequestParam(required = false, value = "parentId") String parentId) {

        RestStruExample restStruExample =  new RestStruExample();

        RestStruExample.Criteria criteria = restStruExample.createCriteria();
        if(null!=parentId && !("").endsWith(parentId)) {
            criteria.andParentIdEqualTo(parentId);
        }
        criteria.andIsUseEqualTo("1");

        restStruExample.setOrderByClause("ORDER_SEQ ASC");

        List<RestStru> list = restStruService.selectByExample(restStruExample);

        return new BaseResult(BaseResultConstant.SUCCESS,list);
    }

}
