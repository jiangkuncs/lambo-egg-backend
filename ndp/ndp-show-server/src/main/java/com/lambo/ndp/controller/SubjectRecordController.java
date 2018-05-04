package com.lambo.ndp.controller;

import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.ndp.constant.NdpResult;
import com.lambo.ndp.constant.NdpResultConstant;
import com.lambo.ndp.model.SubjectRecord;
import com.lambo.ndp.service.api.DataViewService;
import com.lambo.ndp.service.api.SubjectRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 分类controller
 * Created by zxc on 2018/3/14.
 */
@Controller
@Api(value = "数据分类", description = "数据分类")
@RequestMapping("/manage/subjectRecord")
public class SubjectRecordController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(SubjectRecordController.class);

    @Autowired
    private SubjectRecordService subjectRecordService;
    @Autowired
    private DataViewService dataViewService;

    @ApiOperation(value = "记录专题访问记录")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    @LogAround(value="记录专题访问记录")
    public Object create(
            @ApiParam(name="subjectId", value = "专题ID")
            @RequestParam(required = true, defaultValue = "0", value = "subjectId") int subjectId) {
        SubjectRecord subjectRecord = new SubjectRecord();
        int count = 0;
        if(subjectId > 0 ){
            Date date=new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //subjectRecord.setCreateTime(df.format(date).toString());
           // subjectRecord.setSubjectId(subjectId);
            Subject subject = SecurityUtils.getSubject();
            String userName = (String) subject.getPrincipal();
            //subjectRecord.setUserName(userName);

            //count = subjectRecordService.insertSelective(subjectRecord);

            Map recordMap = new HashMap();
            recordMap.put("userName",userName);
            recordMap.put("dateTime",df.format(date).toString());
            recordMap.put("subjectId",subjectId);

            count = dataViewService.insertRecord(recordMap);

        }

        //更新总浏览量

        int visitCount =dataViewService.updateVisitCountBySubjectId(subjectId);

        if(count==1){
            return new NdpResult(NdpResultConstant.SUCCESS, count);
        }else{
            return new NdpResult(NdpResultConstant.FAILED, count);
        }
    }
}