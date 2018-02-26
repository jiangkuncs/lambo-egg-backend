package com.lambo.upms.server.controller.manage;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.lambo.common.annotation.EnableExportTable;
import com.lambo.common.base.BaseController;
import com.lambo.common.validator.LengthValidator;
import com.lambo.upms.common.constant.UpmsResult;
import com.lambo.upms.common.constant.UpmsResultConstant;
import com.lambo.upms.server.dao.model.*;
import com.lambo.upms.server.service.api.UpmsOrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组织controller
 * Created by lambo on 2017/2/6.
 */
@Controller
@Api(value = "组织管理", description = "组织管理")
@RequestMapping("/manage/organization")
public class UpmsOrganizationController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(UpmsOrganizationController.class);

    @Autowired
    private UpmsOrganizationService upmsOrganizationService;

    @ApiOperation(value = "组织列表")
    @RequiresPermissions("upms:organization:read")
    @RequestMapping(value = "/list")
    @ResponseBody
    @EnableExportTable
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UpmsOrganizationExample upmsOrganizationExample = new UpmsOrganizationExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            upmsOrganizationExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            upmsOrganizationExample.or().andNameLike("%" + search + "%");
        }
        List<UpmsOrganization> rows = upmsOrganizationService.selectByExampleForOffsetPage(upmsOrganizationExample, offset, limit);
        long total = upmsOrganizationService.countByExample(upmsOrganizationExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "新增组织")
    @RequiresPermissions("upms:organization:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(
            @RequestParam(required = true, value = "name") String name,
            @RequestParam(required = false, value = "description") String description) {

        ComplexResult result = FluentValidator.checkAll()
                .on(name, new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        long time = System.currentTimeMillis();
        UpmsOrganization upmsOrganization = new UpmsOrganization();
        upmsOrganization.setName(name);
        upmsOrganization.setDescription(description);
        upmsOrganization.setCtime(time);
        int count = upmsOrganizationService.insertSelective(upmsOrganization);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除组织")
    @RequiresPermissions("upms:organization:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = upmsOrganizationService.deleteByPrimaryKeys(ids);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "根据id获取组织")
    @RequiresPermissions("upms:organization:read")
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id") int id) {
        UpmsOrganization upmsOrganization = upmsOrganizationService.selectByPrimaryKey(id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, upmsOrganization);
    }

    @ApiOperation(value = "修改组织")
    @RequiresPermissions("upms:organization:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(
            @PathVariable("id") int id,
            @RequestParam(required = true, value = "name") String name,
            @RequestParam(required = false, value = "description") String description) {

        ComplexResult result = FluentValidator.checkAll()
                .on(name, new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        UpmsOrganization upmsOrganization = new UpmsOrganization();
        upmsOrganization.setOrganizationId(id);
        upmsOrganization.setName(name);
        upmsOrganization.setDescription(description);

        int count = upmsOrganizationService.updateByPrimaryKeySelective(upmsOrganization);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }
}
