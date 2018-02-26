package com.lambo.upms.server.controller.manage;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.lambo.common.annotation.EnableExportTable;
import com.lambo.common.base.BaseController;
import com.lambo.common.validator.LengthValidator;
import com.lambo.upms.server.service.api.UpmsApiService;
import com.lambo.upms.common.constant.UpmsResult;
import com.lambo.upms.common.constant.UpmsResultConstant;
import com.lambo.upms.server.dao.model.*;
import com.lambo.upms.server.service.api.UpmsPermissionService;
import com.lambo.upms.server.service.api.UpmsSystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
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
 * 权限controller
 * Created by lambo on 2017/2/6.
 */
@Controller
@Api(value = "权限管理", description = "权限管理")
@RequestMapping("/manage/permission")
public class UpmsPermissionController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(UpmsPermissionController.class);

    @Autowired
    private UpmsPermissionService upmsPermissionService;

    @Autowired
    private UpmsSystemService upmsSystemService;

    @Autowired
    private UpmsApiService upmsApiService;


    @ApiOperation(value = "权限列表")
    @RequiresPermissions("upms:permission:read")
    @RequestMapping(value = "/list")
    @ResponseBody
    @EnableExportTable
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, defaultValue = "0", value = "type") int type,
            @RequestParam(required = false, defaultValue = "0", value = "systemId") int systemId,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UpmsPermissionExample upmsPermissionExample = new UpmsPermissionExample();
        UpmsPermissionExample.Criteria criteria = upmsPermissionExample.createCriteria();
        if (0 != type) {
            criteria.andTypeEqualTo((byte) type);
        }
        if (0 != systemId) {
            criteria.andSystemIdEqualTo(systemId);
        }
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            upmsPermissionExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            criteria.andNameLike("%" + search + "%");
            //upmsPermissionExample.
        }
        List<UpmsPermission> rows = upmsPermissionService.selectByExampleForOffsetPage(upmsPermissionExample, offset, limit);
        long total = upmsPermissionService.countByExample(upmsPermissionExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "角色权限列表")
    @RequiresPermissions("upms:permission:read")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object role(@PathVariable("id") int id) {

        return upmsPermissionService.getTreeByRoleId(id);
    }

    @ApiOperation(value = "用户权限列表")
    @RequiresPermissions("upms:permission:read")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object user(
            @PathVariable("id") int id,
            @ApiParam(name = "type", required = true, value = "权限类型(-1:减权限,1:增权限)")
            @RequestParam(required = false, value = "type") String type) {

        return upmsPermissionService.getTreeByUserId(id, NumberUtils.toByte(type));
    }


    @ApiOperation(value = "新增权限")
    @RequiresPermissions("upms:permission:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(
            @RequestParam(required = true, value = "systemId") int systemId,
            @RequestParam(required = true, value = "pid") int pid,
            @RequestParam(required = true, value = "name") String name,
            @ApiParam(name = "type", required = true, value = "类型(1:目录,2:菜单,3:按钮)")
            @RequestParam(required = true, value = "type") String type,
            @RequestParam(required = false, value = "permissionValue") String permissionValue,
            @RequestParam(required = false, value = "uri") String uri,
            @RequestParam(required = false, value = "icon") String icon,
            @ApiParam(name = "status", required = true, value = "状态(0:禁止,1:正常)")
            @RequestParam(required = false, value = "status") String status) {

        ComplexResult result = FluentValidator.checkAll()
                .on(name, new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        UpmsPermission upmsPermission = new UpmsPermission();
        long time = System.currentTimeMillis();
        upmsPermission.setSystemId(systemId);
        upmsPermission.setPid(pid);
        upmsPermission.setName(name);
        upmsPermission.setType(NumberUtils.toByte(type));
        upmsPermission.setPermissionValue(permissionValue);
        upmsPermission.setUri(uri);
        upmsPermission.setIcon(icon);
        upmsPermission.setCtime(time);
        upmsPermission.setStatus(NumberUtils.toByte(status));
        upmsPermission.setOrders(time);
        int count = upmsPermissionService.insertSelective(upmsPermission);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除权限")
    @RequiresPermissions("upms:permission:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = upmsPermissionService.deleteByPrimaryKeys(ids);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "获取权限")
    @RequiresPermissions("upms:permission:read")
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id") String id) {
        UpmsPermission upmsPermission = upmsPermissionService.selectByPrimaryKey(NumberUtils.toInt(id));
        return new UpmsResult(UpmsResultConstant.SUCCESS, upmsPermission);
    }

    @ApiOperation(value = "修改权限")
    @RequiresPermissions("upms:permission:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(
            @PathVariable("id") int id,
            @RequestParam(required = true, value = "systemId") int systemId,
            @RequestParam(required = true, value = "pid") int pid,
            @RequestParam(required = true, value = "name") String name,
            @ApiParam(name = "type", required = true, value = "类型(1:目录,2:菜单,3:按钮)")
            @RequestParam(required = true, value = "type") String type,
            @RequestParam(required = false, value = "permissionValue") String permissionValue,
            @RequestParam(required = false, value = "uri") String uri,
            @RequestParam(required = false, value = "icon") String icon,
            @ApiParam(name = "status", required = true, value = "状态(0:禁止,1:正常)")
            @RequestParam(required = false, value = "status") String status) {

        ComplexResult result = FluentValidator.checkAll()
                .on(name, new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        UpmsPermission upmsPermission = new UpmsPermission();
        upmsPermission.setPermissionId(id);
        upmsPermission.setSystemId(systemId);
        upmsPermission.setPid(pid);
        upmsPermission.setName(name);
        upmsPermission.setType(NumberUtils.toByte(type));
        upmsPermission.setPermissionValue(permissionValue);
        upmsPermission.setUri(uri);
        upmsPermission.setIcon(icon);
        upmsPermission.setStatus(NumberUtils.toByte(status));

        int count = upmsPermissionService.updateByPrimaryKeySelective(upmsPermission);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

}
