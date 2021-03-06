package com.lambo.ndp.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;

import com.lambo.common.utils.lang.StringUtils;
import com.lambo.common.validator.LengthValidator;
import com.lambo.ndp.constant.NdpResult;
import com.lambo.ndp.constant.NdpResultConstant;
import com.lambo.ndp.model.*;
import com.lambo.ndp.service.api.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据字典controller
 * Created by zxc on 2018/3/04.
 */
@Controller
@Api(value = "数据字典查询", description = "数据字典查询")
@RequestMapping("/manage/dictData")
public class DictController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(DictController.class);

    @Autowired
    private DictService dictService;

    @ApiOperation(value = "数据字典列表")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    @LogAround("请求列表数据")
    public Object listExport(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @ApiParam(name="sort", value = "排序字段")
            @RequestParam(required = false, value = "sort") String sort,
            @ApiParam(name="order", value = "排序方式")
            @RequestParam(required = false, value = "order") String order,
            @RequestParam(required = false, defaultValue = "", value = "dictId") String dictId,
            @RequestParam(required = false, defaultValue = "", value = "dictName") String dictName) {
        return ((NdpResult)list(offset,limit,sort,order,dictId,dictName)).data;
    }
    @ApiOperation(value = "数据字典列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("请求列表数据")
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @ApiParam(name="sort", value = "排序字段")
            @RequestParam(required = false, value = "sort") String sort,
            @ApiParam(name="order", value = "排序方式")
            @RequestParam(required = false, value = "order") String order,
            @RequestParam(required = false, defaultValue = "", value = "dictId") String dictId,
            @RequestParam(required = false, defaultValue = "", value = "dictName") String dictName) {
        DictExample dictExample = new DictExample();
        if(StringUtils.isBlank(sort)){
            sort = "dictId";
        }
        if(StringUtils.isBlank(order)){
            order = "desc";
        }
        dictExample.setOrderByClause(StringUtils.humpToLine(sort) + " " + order);
        if (StringUtils.isNotBlank(dictName)) {
            dictExample.or().andDictIdLike("%" + dictId + "%")
                    .andDictNameLike("%" + dictName + "%");
        }
        if (StringUtils.isNotBlank(dictId)) {
            dictExample.or().andDictIdLike("%" + dictId + "%");

        }
        //物理分页
        PageHelper.offsetPage(offset, limit);
        List<Dict> data = dictService.selectByExample(dictExample);
        PageInfo page = new PageInfo(data);

        Map<String, Object> result = new HashMap<>();
        result.put("rows", page.getList());
        result.put("total", page.getTotal());
        return new NdpResult(NdpResultConstant.SUCCESS,result);
    }

    @ApiOperation(value = "新增数据字典")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    /**
     * dictKeyList样例数据：[{"dictKey":"2","dictValue":"未审核"},{"dictKey":"3","dictValue":"提交"},{"dictKey":"1","dictValue":"已审核"},{"dictKey":"0","dictValue":"新建"}]
     */
    public Object create(
            @RequestParam(required = false, value = "dictName") String dictName,
            @RequestParam(required = true, value = "dictId") String dictId,
            @RequestParam(required = false, value = "dictDesc") String dictDesc,
            @RequestParam(required = false, value = "dictKeyList" ) String dictKeyList) {
        ComplexResult result = FluentValidator.checkAll()
                .on(dictId, new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new NdpResult(NdpResultConstant.INVALID_LENGTH, result.getErrors());
        }
        JSONArray json = JSONArray.parseArray(dictKeyList);
        int inset=0;
        if(json.size()>0){
            for(int i=0;i<json.size();i++){
                JSONObject job = json.getJSONObject(i);
                Dict dict=new Dict();
                dict.setDictId(dictId);
                dict.setDictDesc(dictDesc);
                dict.setDictName(dictName);
                dict.setDictKey((String)job.get("dictKey"));
                dict.setDictValue((String)job.get("dictValue"));
                inset=dictService.insert(dict);
            }
        }
        return new NdpResult(NdpResultConstant.SUCCESS, inset);
    }

    @ApiOperation(value = "删除数据字典")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestParam(required = true, defaultValue = "", value = "dictId") String dictId) {

        DictExample dictExample = new DictExample();
        dictExample.createCriteria().andDictIdEqualTo(dictId);

        return new NdpResult(NdpResultConstant.SUCCESS, dictService.deleteByExample(dictExample));
    }

    @ApiOperation(value = "删除数据字典项信息")
    @RequestMapping(value = "/deletedictkey",method = RequestMethod.GET)
    @ResponseBody
    public Object deleteDictKey(@RequestParam(required = true, defaultValue = "", value = "dictId") String dictId,
                                @RequestParam(required = true, defaultValue = "", value = "dictKey") String dictKey) {
        DictExample dictExample = new DictExample();
        dictExample.createCriteria().andDictIdEqualTo(dictId).andDictKeyEqualTo(dictKey);
        return new NdpResult(NdpResultConstant.SUCCESS, dictService.deleteByExample(dictExample));
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
      return new NdpResult(NdpResultConstant.SUCCESS, dictService.update(dictId,dictName,dictDesc,dictKeyList));
    }

    @ApiOperation(value = "根据ID查询分类")
    @RequestMapping(value = "/get/{dictId}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("dictId") String dictId) {
//        DictExample dictExample = new DictExample();
//        dictExample.createCriteria().andDictIdEqualTo(dictId);
        Map parm=new HashMap();
        parm.put("dictId",dictId);
        return new NdpResult(NdpResultConstant.SUCCESS, dictService.getDict(parm));
    }
}