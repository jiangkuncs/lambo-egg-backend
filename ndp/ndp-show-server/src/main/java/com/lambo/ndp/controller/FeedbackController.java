package com.lambo.ndp.controller;

import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.ndp.constant.NdpResult;
import com.lambo.ndp.constant.NdpResultConstant;
import com.lambo.ndp.model.FeedBack;
import com.lambo.ndp.service.api.DataViewService;
import com.lambo.ndp.service.api.FeedBackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 反馈信息controller
 * Created by SunJibing on 2018/4/18.
 */
@Controller
@Api(value = "反馈信息", description = "反馈信息")
@RequestMapping("/manage/feedBack")
public class FeedbackController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(FeedbackController.class);
    @Autowired
    private FeedBackService feedBackService;
    @Autowired
    private DataViewService dataViewService;

    @ApiOperation(value = "反馈信息保存")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    @LogAround("请求列表数据")
    public Object save(
            @RequestParam(required = false, value = "categoryId") String categoryId,
            @RequestParam(required = false, value = "categoryType") String categoryType,
            @RequestParam(required = false, value = "title") String title,
            @RequestParam(required = false, value = "rateCount") String rateCount,
            @RequestParam(required = false, value = "commentContent") String commentContent) {

        FeedBack fb = new FeedBack();
        fb.setTitle(title);
        fb.setComment(commentContent);
        fb.setRatecount(Integer.valueOf(rateCount));
        fb.setRecordobjectid(categoryId);
        fb.setType(categoryType);
        fb.setRecorduser(SecurityUtils.getSubject().getPrincipal().toString());
        fb.setRecorddate(new Date());
        //保存评价记录
        int count = feedBackService.insert(fb);
        //更新数据目录的的分
        Map<String,Object> paraMap = new HashMap();
        paraMap.put("catagoryId",categoryId);
        paraMap.put("rateCount", Double.valueOf(rateCount));
        int updateCount = dataViewService.updateRateCountBySubjectId(paraMap);
        if(count>0)
        {return  new NdpResult(NdpResultConstant.SUCCESS, fb);}
        else
        {return  new NdpResult(NdpResultConstant.FAILED,fb);}
    }
}