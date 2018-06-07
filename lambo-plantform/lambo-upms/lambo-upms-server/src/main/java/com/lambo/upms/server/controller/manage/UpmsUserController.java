package com.lambo.upms.server.controller.manage;

import com.alibaba.fastjson.JSONArray;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.lambo.common.annotation.EnableExportTable;
import com.lambo.common.base.BaseController;
import com.lambo.common.utils.codec.Md5Utils;
import com.lambo.common.validator.LengthValidator;
import com.lambo.common.validator.NotNullValidator;
import com.lambo.upms.common.constant.UpmsResult;
import com.lambo.upms.common.constant.UpmsResultConstant;
import com.lambo.upms.server.dao.model.*;
import com.lambo.upms.server.service.api.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * 用户controller
 * Created by lambo on 2017/2/6.
 */
@Controller
@Api(value = "用户管理", description = "用户管理")
@RequestMapping("/manage/user")
public class UpmsUserController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(UpmsUserController.class);

    @Autowired
    private UpmsUserService upmsUserService;

    @Autowired
    private UpmsRoleService upmsRoleService;

    @Autowired
    private UpmsOrganizationService upmsOrganizationService;

    @Autowired
    private UpmsUserOrganizationService upmsUserOrganizationService;

    @Autowired
    private UpmsUserRoleService upmsUserRoleService;

    @Autowired
    private UpmsUserPermissionService upmsUserPermissionService;

    @ApiOperation(value = "获取用户所属组织")
    @RequiresPermissions("upms:user:organization")
    @RequestMapping(value = "/organization/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object organization(@PathVariable("id") int id) {

        // 用户拥有组织
        UpmsUserOrganizationExample upmsUserOrganizationExample = new UpmsUserOrganizationExample();
        upmsUserOrganizationExample.createCriteria().andUserIdEqualTo(id);
        List<UpmsUserOrganization> upmsUserOrganizations = upmsUserOrganizationService.selectByExample(upmsUserOrganizationExample);

        return new UpmsResult(UpmsResultConstant.SUCCESS, upmsUserOrganizations);
    }

    @ApiOperation(value = "更新用户组织")
    @RequiresPermissions("upms:user:organization")
    @RequestMapping(value = "/organization/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object organization(
            @PathVariable("id") int id,
            @RequestParam(required = true,  value = "orgIds") String orgIds) {
        String[] organizationIds = orgIds.split(",");
        upmsUserOrganizationService.organization(organizationIds, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, "");
    }

    @ApiOperation(value = "获取用户角色")
    @RequiresPermissions("upms:user:role")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object role(@PathVariable("id") int id) {
        UpmsUserRoleExample upmsUserRoleExample = new UpmsUserRoleExample();
        upmsUserRoleExample.createCriteria().andUserIdEqualTo(id);
        List<UpmsUserRole> upmsUserRoles = upmsUserRoleService.selectByExample(upmsUserRoleExample);
        return new UpmsResult(UpmsResultConstant.SUCCESS, upmsUserRoles);
    }

    @ApiOperation(value = "更新用户角色")
    @RequiresPermissions("upms:user:role")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object role(
            @PathVariable("id") int id,
            @RequestParam(required = true,  value = "roleIds") String roles) {
        String[] roleIds = roles.split(",");
        upmsUserRoleService.role(roleIds, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, "");
    }

    @ApiOperation(value = "用户权限")
    @RequiresPermissions("upms:user:permission")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object permission(
            @PathVariable("id") int id,
            @RequestParam(required = true,  value = "datas") String jsonDatas) {
        JSONArray datas = JSONArray.parseArray(jsonDatas);
        upmsUserPermissionService.permission(datas, id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, datas.size());
    }

    @ApiOperation(value = "用户列表")
    @RequiresPermissions("upms:user:read")
    @RequestMapping(value = "/list")
    @ResponseBody
    @EnableExportTable
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order,
            @RequestParam(required = false, defaultValue = "", value = "search") String search) {
        UpmsUserExample upmsUserExample = new UpmsUserExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            upmsUserExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            upmsUserExample.or()
                    .andRealnameLike("%" + search + "%");
            upmsUserExample.or()
                    .andUsernameLike("%" + search + "%");
        }
        List<UpmsUser> rows = upmsUserService.selectByExampleForOffsetPage(upmsUserExample, offset, limit);
        int total = upmsUserService.countByExample(upmsUserExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "根据ID查询用户")
    @RequiresPermissions("upms:user:read")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id") int id) {
        UpmsUser upmsUser = upmsUserService.selectByPrimaryKey(id);
        return new UpmsResult(UpmsResultConstant.SUCCESS, upmsUser);
    }

    @ApiOperation(value = "新增用户")
    @RequiresPermissions("upms:user:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(
            @ApiParam(required = true, name = "userName", value = "用户账户")
            @RequestParam(required = true, value = "userName") String userName,
            @RequestParam(required = true, value = "password") String password,
            @RequestParam(required = false, defaultValue = "", value = "realName") String realName,
            @RequestParam(required = false, defaultValue = "", value = "avater") String avater,
            @RequestParam(required = false, defaultValue = "", value = "phone") String phone,
            @RequestParam(required = false, defaultValue = "", value = "email") String email,
            @ApiParam(required = false, name = "sex", value = "用户性别0：女性，1：男性")
            @RequestParam(required = false, defaultValue = "0", value = "sex") String sex
    ) {
        ComplexResult result = FluentValidator.checkAll()
                .on(userName, new LengthValidator(1, 20, "帐号"))
                .on(password, new LengthValidator(5, 32, "密码"))
                .on(realName, new NotNullValidator("姓名"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        UpmsUser upmsUser = new UpmsUser();
        upmsUser.setUsername(userName);
        upmsUser.setRealname(realName);
        upmsUser.setAvatar(avater);
        upmsUser.setPhone(phone);
        upmsUser.setEmail(email);
        upmsUser.setSex(Byte.parseByte(sex));
        long time = System.currentTimeMillis();
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        upmsUser.setSalt(salt);
        upmsUser.setPassword(Md5Utils.md5(password + upmsUser.getSalt()));
        upmsUser.setCtime(time);
        upmsUser = upmsUserService.createUser(upmsUser);
        if (null == upmsUser) {
            return new UpmsResult(UpmsResultConstant.FAILED, "帐号名已存在！");
        }
        logger.info("新增用户，主键：userId={}", upmsUser.getUserId());
        return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
    }

    @ApiOperation(value = "删除用户")
    @RequiresPermissions("upms:user:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = upmsUserService.deleteByPrimaryKeys(ids);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改用户")
    @RequiresPermissions("upms:user:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(
            @PathVariable("id") int id,
            @ApiParam(required = true, name = "userName", value = "用户账户")
            @RequestParam(required = false, value = "userName") String userName,
            @RequestParam(required = false, defaultValue = "", value = "realName") String realName,
            @RequestParam(required = false, defaultValue = "", value = "avater") String avater,
            @RequestParam(required = false, defaultValue = "", value = "phone") String phone,
            @RequestParam(required = false, defaultValue = "", value = "email") String email,
            @ApiParam(required = false, name = "sex", value = "用户性别0：女性，1：男性")
            @RequestParam(required = false, defaultValue = "", value = "sex") String sex) {
        ComplexResult result = FluentValidator.checkAll()
                .on(userName, new LengthValidator(1, 20, "帐号"))
                .on(realName, new NotNullValidator("姓名"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        UpmsUser upmsUser = new UpmsUser();
        if(!"".equals(userName)){
            upmsUser.setUsername(userName);
        }
        if(!"".equals(realName)){
            upmsUser.setRealname(realName);
        }
        if(!"".equals(avater)){
            upmsUser.setAvatar(avater);
        }
        if(!"".equals(phone)){
            upmsUser.setPhone(phone);
        }
        if(!"".equals(email)){
            upmsUser.setEmail(email);
        }
        if(!"".equals(sex)){
            upmsUser.setSex(Byte.parseByte(sex));
        }
        // 不允许直接改密码
        upmsUser.setPassword(null);
        upmsUser.setUserId(id);
        int count = upmsUserService.updateByPrimaryKeySelective(upmsUser);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

}
