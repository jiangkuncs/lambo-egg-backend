package com.lambo.ndp.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lambo.common.annotation.EnableExportTable;
import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.common.validator.LengthValidator;
import com.lambo.ndp.constant.NdpResult;
import com.lambo.ndp.constant.NdpResultConstant;
import com.lambo.ndp.model.*;
import com.lambo.ndp.service.api.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日志controller
 * Created by lambo on 2017/3/14.
 */
@Controller
@Api(value = "数据字典查询", description = "数据字典查询")
@RequestMapping("/manage/dictdata")
public class DictController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(DictController.class);

    @Autowired
    private DictService DictService;

    @ApiOperation(value = "数据字典列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    @EnableExportTable
    @LogAround("请求列表数据")
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "dictId") String dictId,
            @RequestParam(required = false, defaultValue = "", value = "dictName") String dictName) {
        DictExample dictExample=new DictExample();
//        if(StringUtils.isBlank(sort)){
//            sort = "startTime";
//        }
//        if(StringUtils.isBlank(order)){
//            order = "desc";
//        }
//        demoLogExample.setOrderByClause(StringUtil.humpToLine(sort) + " " + order);
//
        if (StringUtils.isNotBlank(dictName)) {
            dictExample.or().andDictIdLike("%" + dictId + "%")
                    .andDictNameLike("%" + dictName + "%");

        }

        //物理分页
        PageHelper.offsetPage(offset, limit);
        List<Dict> data = DictService.selectByExample(dictExample);
        PageInfo page = new PageInfo(data);

        Map<String, Object> result = new HashMap<>();
        result.put("rows", page.getList());
        result.put("total", page.getTotal());
        return new NdpResult(NdpResultConstant.SUCCESS,result);
    }

    @ApiOperation(value = "新增数据字典")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(
            @RequestParam(required = false, value = "dictName") String dictName,
            @RequestParam(required = true, value = "dictId") String dictId,
            @RequestParam(required = false, value = "dictDesc") String dictDesc,
            @RequestParam(required = false, value = "dictKeyList" ) String dictKeyList) {
//[{"dictKey":"2","dictValue":"未审核"},{"dictKey":"3","dictValue":"提交"},{"dictKey":"1","dictValue":"已审核"},{"dictKey":"0","dictValue":"新建"}]
        ComplexResult result = FluentValidator.checkAll()
                .on(dictId, new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new NdpResult(NdpResultConstant.INVALID_LENGTH, result.getErrors());
        }
        JSONArray json = JSONArray.parseArray(dictKeyList);
        System.out.println("json:"+json);
        if(json.size()>0){
            for(int i=0;i<json.size();i++){
                JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                System.out.println("job:"+job) ;  // 得到 每个对象中的属性值
                Dict dict=new Dict();
                dict.setDictId(dictId);
                dict.setDictDesc(dictDesc);
                dict.setDictName(dictName);
                dict.setDictKey((String)job.get("dictKey"));
                dict.setDictValue((String)job.get("dictValue"));
                DictService.insert(dict);
            }
        }
        return new NdpResult(NdpResultConstant.SUCCESS, json);
    }

    @ApiOperation(value = "删除数据字典")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestParam(required = true, defaultValue = "", value = "dictId") String dictId) {
        System.out.println("dictId:"+dictId);
        Map parm=new HashMap();
        parm.put("dictId",dictId);
        int count = DictService.deleteByDictId(parm);
        return new NdpResult(NdpResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除数据字典项信息")
    @RequestMapping(value = "/deletedictkey",method = RequestMethod.GET)
    @ResponseBody
    public Object deleteDictKey(@RequestParam(required = true, defaultValue = "", value = "dictId") String dictId,
                                @RequestParam(required = true, defaultValue = "", value = "dictKey") String dictKey) {
        Map parm=new HashMap();
        parm.put("dictId",dictId);
        parm.put("dictKey",dictKey);
        int count = DictService.deleteByDictId(parm);
        return new NdpResult(NdpResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改数据字典")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object updateUser(
            @RequestParam(required = true, value = "dictId")String dictId,
            @RequestParam(required = false, value = "dictName") String dictName,
            @RequestParam(required = false, value = "dictDesc") String dictDesc,
            @RequestParam(required = false, value = "dictKeyList" ) String dictKeyList
    ){
        int count = 0;
        ComplexResult result = FluentValidator.checkAll()
                .on(dictId, new LengthValidator(1, 20, "表名"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new NdpResult(NdpResultConstant.INVALID_LENGTH, result.getErrors());
        }
        System.out.println("dictKeyList:"+dictKeyList);

        JSONArray json = JSONArray.parseArray(dictKeyList);
        System.out.println("json:"+json);
        if(json.size()>0){
            Map parm=new HashMap();
            parm.put("dictId",dictId);
            count = DictService.deleteByDictId(parm);
            for(int i=0;i<json.size();i++){
                JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                System.out.println("job:"+job) ;  // 得到 每个对象中的属性值
                Dict dict=new Dict();
                dict.setDictId(dictId);
                dict.setDictDesc(dictDesc);
                dict.setDictName(dictName);
                dict.setDictKey((String)job.get("dictKey"));
                dict.setDictValue((String)job.get("dictValue"));
                DictService.insert(dict);
            }
        }
        return count;
    }

    @ApiOperation(value = "根据ID查询分类")
    @RequestMapping(value = "/get/{dictId}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("dictId") String dictId) {
        List<Dict> Dict = DictService.selectByDictId(dictId);
        return new NdpResult(NdpResultConstant.SUCCESS, Dict);
    }
}