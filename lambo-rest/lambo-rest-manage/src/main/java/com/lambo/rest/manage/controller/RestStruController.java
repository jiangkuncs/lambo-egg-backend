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
@Api(value = "rest服务管理", description = "rest服务管理")
@RequestMapping("/manage/rest/stru")
public class RestStruController extends BaseController {
    @Autowired
    RestStruService restStruService;

    @ApiOperation(value = "新增rest服务")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    @LogAround("新增rest服务")
    public Object insert(@RequestParam(required = true, value = "struName") String struName,
                         @RequestParam(required = true, value = "isLeaf") String isLeaf,
                         @RequestParam(required = false, value = "parentId") String parentId,
                         @RequestParam(required = false, value = "isUse") String isUse) {

        RestStru restStru = new RestStru();

        String uuid = IdGenerate.uuid();

        restStru.setStruId(uuid);
        restStru.setStruName(struName);
        restStru.setIsLeaf(isLeaf);
        restStru.setParentId(parentId);
        restStru.setIsUse(isUse);

        if(("1").equals(isLeaf)){
            restStru.setRestId(uuid);
        }else{
            restStru.setRestId("");
        }

        int count = restStruService.insert(restStru);

        return count;
    }

    @ApiOperation(value = "更新rest服务")
    @RequestMapping(value = "/update/{struId}",method = RequestMethod.POST)
    @ResponseBody
    @LogAround("更新rest服务")
    public Object update(@PathVariable("struId") String struId,
                         @RequestParam(required = false, value = "struName") String struName,
                         @RequestParam(required = false, value = "isLeaf") String isLeaf,
                         @RequestParam(required = false, value = "restid") String restid,
                         @RequestParam(required = false, value = "parentId") String parentId,
                         @RequestParam(required = false, value = "isUse") String isUse) {

        RestStru restStru = new RestStru();
        restStru.setStruId(struId);
        restStru.setStruName(struName);
        restStru.setIsLeaf(isLeaf);
        restStru.setRestId(restid);
        restStru.setParentId(parentId);
        restStru.setIsUse(isUse);

        int count = restStruService.updateByPrimaryKey(restStru);

        return count;
    }

    @ApiOperation(value = "查询rest服务")
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("查询rest服务")
    public Object get(
            @RequestParam(required = false, value = "parentId") String parentId) {

        RestStruExample restStruExample =  new RestStruExample();
        if(null!=parentId && !("").endsWith(parentId)) {
            restStruExample.createCriteria().andParentIdEqualTo(parentId);
        }

        List<RestStru> list = restStruService.selectByExample(restStruExample);

        return new BaseResult(BaseResultConstant.SUCCESS,list);
    }

    @ApiOperation(value = "查询rest服务")
    @RequestMapping(value = "/queryStru",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("查询rest服务")
    public Object getStru(
            @RequestParam(required = true, value = "struId") String struId) {

        RestStruExample restStruExample =  new RestStruExample();
        restStruExample.createCriteria().andStruIdEqualTo(struId);

        return restStruService.selectByExample(restStruExample);
    }
}
