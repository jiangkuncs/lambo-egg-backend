package com.lambo.rest.manage.controller;

import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.common.utils.idgen.IdGenerate;
import com.lambo.rest.manage.model.RestStru;
import com.lambo.rest.manage.model.RestStruExample;
import com.lambo.rest.manage.service.api.RestStruService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(value = "rest目录管理", description = "rest目录管理")
@RequestMapping("/manage/rest/stru")
public class RestStruController extends BaseController {
    @Autowired
    RestStruService restStruService;

    @ApiOperation(value = "新增rest目录")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    @LogAround("新增rest目录")
    public Object insert(@RequestParam(required = true, value = "struName") String struName,
                         @RequestParam(required = true, value = "struUrl") String struUrl,
                         @RequestParam(required = true, value = "isLeaf") String isLeaf,
                         @RequestParam(required = false, value = "parentId") String parentId,
                         @RequestParam(required = false, value = "isUse") String isUse,
                         @RequestParam(required = false, value = "orderSeq") Integer orderSeq) {

        RestStru restStru = new RestStru();

        String uuid = IdGenerate.uuid();

        //REST_STRU
        restStru.setStruId(uuid);
        restStru.setStruName(struName);
        restStru.setStruUrl(struUrl);
        restStru.setIsLeaf(isLeaf);
        restStru.setParentId(parentId);
        restStru.setIsUse(isUse);
        restStru.setOrderSeq(orderSeq);

        if(("0").equals(isLeaf)){
            restStru.setRestId("");
        }else{
            restStru.setRestId(uuid);
        }

        int count = restStruService.insert(restStru);

        return new BaseResult(BaseResultConstant.SUCCESS,restStru);
    }

    @ApiOperation(value = "更新rest目录")
    @RequestMapping(value = "/update/{struId}",method = RequestMethod.POST)
    @ResponseBody
    @LogAround("更新rest目录")
    public Object update(@PathVariable("struId") String struId,
                         @RequestParam(required = true, value = "struName") String struName,
                         @RequestParam(required = true, value = "struUrl") String struUrl,
                         @RequestParam(required = true, value = "isLeaf") String isLeaf,
                         @RequestParam(required = false, value = "restId") String restId,
                         @RequestParam(required = false, value = "parentId") String parentId,
                         @RequestParam(required = false, value = "isUse") String isUse,
                         @RequestParam(required = false, value = "orderSeq") Integer orderSeq) {

        RestStru restStru = new RestStru();

        //REST_STRU
        restStru.setStruId(struId);
        restStru.setStruName(struName);
        restStru.setStruUrl(struUrl);
        restStru.setIsLeaf(isLeaf);
        restStru.setRestId(restId);
        restStru.setParentId(parentId);
        restStru.setIsUse(isUse);
        restStru.setOrderSeq(orderSeq);

        int count = restStruService.updateByPrimaryKey(restStru);

        return new BaseResult(BaseResultConstant.SUCCESS,restStru);
    }

    @ApiOperation(value = "删除rest目录")
    @RequestMapping(value = "/delete/{struId}",method = RequestMethod.POST)
    @ResponseBody
    @LogAround("删除rest目录")
    public Object delete(@PathVariable("struId") String struId){

        RestStru restStru = new RestStru();
        restStru.setIsUse("0");

        RestStruExample restStruExample =  new RestStruExample();
        restStruExample.createCriteria().andStruIdEqualTo(struId);

        int count = restStruService.updateByExampleSelective(restStru,restStruExample);

        return new BaseResult(BaseResultConstant.SUCCESS,restStru);
    }

    @ApiOperation(value = "查询rest目录")
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("查询rest目录")
    public Object query(
            @RequestParam(required = false, value = "struId") String struId) {

        RestStruExample restStruExample =  new RestStruExample();
        if(null!=struId && !("").endsWith(struId)) {
            restStruExample.createCriteria().andStruIdEqualTo(struId);
        }

        RestStru restStru = restStruService.selectFirstByExample(restStruExample);

        return new BaseResult(BaseResultConstant.SUCCESS,restStru);
    }

    @ApiOperation(value = "查询rest子目录")
    @RequestMapping(value = "/queryChildren",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("查询rest子目录")
    public Object queryChildren(
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
