package com.lambo.upms.server.controller.manage;

import com.alibaba.fastjson.JSONArray;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.lambo.common.annotation.EnableExportTable;
import com.lambo.common.base.BaseController;
import com.lambo.common.validator.LengthValidator;
import com.lambo.upms.common.constant.UpmsResult;
import com.lambo.upms.common.constant.UpmsResultConstant;
import com.lambo.upms.server.dao.model.*;
import com.lambo.upms.server.service.api.UpmsRolePermissionService;
import com.lambo.upms.server.service.api.UpmsRoleService;
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
 * 角色controller
 * Created by lambo on 2017/2/6.
 */
@Controller
@Api(value = "角色管理", description = "角色管理")
@RequestMapping("/manage/role")
public class UpmsRoleController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(UpmsRoleController.class);

    @Autowired
    private UpmsRoleService upmsRoleService;

    @Autowired
    private UpmsRolePermissionService upmsRolePermissionService;

    @ApiOperation(value = "角色权限")
    @RequiresPermissions("upms:role:permission")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object permission(
            @PathVariable("id") int id,
            @RequestParam(required = true, value = "dataJson") String dataJson) {
        JSONArray datas = JSONArray.parseArray(dataJson);
        int result = upmsRolePermissionService.rolePermission(datas, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, result);
    }

    @ApiOperation(value = "角色列表")
    @RequiresPermissions("upms:role:read")
    @RequestMapping(value = "/list")
    @ResponseBody
    @EnableExportTable
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UpmsRoleExample upmsRoleExample = new UpmsRoleExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            upmsRoleExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            upmsRoleExample.or()
                    .andTitleLike("%" + search + "%");
        }
        List<UpmsRole> rows = upmsRoleService.selectByExampleForOffsetPage(upmsRoleExample, offset, limit);
        long total = upmsRoleService.countByExample(upmsRoleExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "新增角色")
    @RequiresPermissions("upms:role:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(
            @RequestParam(required = true, value = "name") String name,
            @RequestParam(required = false, value = "title") String title,
            @RequestParam(required = false, value = "description") String description) {
        UpmsRole upmsRole = new UpmsRole();
        ComplexResult result = FluentValidator.checkAll()
                .on(name, new LengthValidator(1, 20, "名称"))
                .on(title, new LengthValidator(1, 20, "标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        long time = System.currentTimeMillis();
        upmsRole.setName(name);
        upmsRole.setTitle(title);
        upmsRole.setName(description);
        upmsRole.setCtime(time);
        upmsRole.setOrders(time);
        int count = upmsRoleService.insertSelective(upmsRole);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除角色")
    @RequiresPermissions("upms:role:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = upmsRoleService.deleteByPrimaryKeys(ids);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "根据ID查询角色")
    @RequiresPermissions("upms:role:read")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id") int id) {
        UpmsRole role = upmsRoleService.selectByPrimaryKey(id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, role);
    }

    @ApiOperation(value = "修改角色")
    @RequiresPermissions("upms:role:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(
            @PathVariable("id") int id,
            @RequestParam(required = true, value = "name") String name,
            @RequestParam(required = false, value = "title") String title,
            @RequestParam(required = false, value = "description") String description) {
        UpmsRole upmsRole = new UpmsRole();
        if(description != null){
            upmsRole.setDescription(description);
        }
        upmsRole.setName(description);
        ComplexResult result = FluentValidator.checkAll()
                .on(name, new LengthValidator(1, 20, "名称"))
                .on(title, new LengthValidator(1, 20, "标题"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        upmsRole.setName(name);
        upmsRole.setTitle(title);
        upmsRole.setRoleId(id);
        int count = upmsRoleService.updateByPrimaryKeySelective(upmsRole);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

}
