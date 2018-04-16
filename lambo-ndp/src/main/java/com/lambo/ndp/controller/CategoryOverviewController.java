package com.lambo.ndp.controller;

import com.lambo.common.base.BaseController;
import com.lambo.ndp.constant.NdpResult;
import com.lambo.ndp.constant.NdpResultConstant;
import com.lambo.ndp.model.CategoryOverview;
import com.lambo.ndp.service.api.CategoryOverviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @ClassName CategoryOverviewContoller
 * @Descirption 分类概览维护Controller
 * @Author sunzhen
 * @Date 2018/4/13 16:49
 **/

@Controller
@Api(value = "分类概览管理", description = "分类概览管理")
@RequestMapping("/manage/category/overview")
public class CategoryOverviewController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryOverviewService categoryOverviewService;

    @ApiOperation(value = "新增分类概览")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(
            @RequestParam(required = true, value = "categoryId") int categoryId,
            @RequestParam(required = true, value = "summary") String summary,
            @RequestParam(required = true, value = "caption") String caption,
            @RequestParam(required = true, value = "picture") String picture,
            @RequestParam(required = true, value = "article") String article) {
        CategoryOverview co = new CategoryOverview();
        co.setCategoryId(categoryId);
        co.setSummary(summary);
        co.setCaption(caption);
        co.setPicture(picture);
        co.setArticle(article);
        co.setCreateTime(new Date());
        co.setUpdateTime(new Date());
        co.setLastEditor((String) SecurityUtils.getSubject().getPrincipal());
        int count = categoryOverviewService.insert(co);
        if(count==1){
            return new NdpResult(NdpResultConstant.SUCCESS, count);
        }else{
            return new NdpResult(NdpResultConstant.FAILED, count);
        }
    }

    @ApiOperation(value = "修改分类概览")
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(
            @RequestParam(required = true, value = "categoryId") int categoryId,
            @RequestParam(required = true, value = "summary") String summary,
            @RequestParam(required = true, value = "caption") String caption,
            @RequestParam(required = true, value = "picture") String picture,
            @RequestParam(required = true, value = "article") String article) {

        CategoryOverview co =  categoryOverviewService.selectByPrimaryKey(categoryId);
        boolean isUpdate = true;
        if (co == null) {
            co = new CategoryOverview();
            co.setCreateTime(new Date());
            isUpdate = false;
        }
        co.setCategoryId(categoryId);
        co.setSummary(summary);
        co.setCaption(caption);
        co.setPicture(picture);
        co.setArticle(article);
        co.setUpdateTime(new Date());
        co.setLastEditor((String) SecurityUtils.getSubject().getPrincipal());

        int count = 0;
        if(isUpdate){
            count = categoryOverviewService.updateByPrimaryKey(co);
        }else{
            count = categoryOverviewService.insertSelective(co);
        }

        if(count == 1){
            return new NdpResult(NdpResultConstant.SUCCESS, co);
        }else{
            return new NdpResult(NdpResultConstant.FAILED, "数据保存失败");
        }
    }

    @ApiOperation(value = "查询分类概览")
    @ResponseBody
    @RequestMapping(value = "/get/{categoryId}", method = RequestMethod.GET)
    public Object get( @PathVariable("categoryId") int categoryId) {

        return categoryOverviewService.selectByPrimaryKey(categoryId);
    }
}
