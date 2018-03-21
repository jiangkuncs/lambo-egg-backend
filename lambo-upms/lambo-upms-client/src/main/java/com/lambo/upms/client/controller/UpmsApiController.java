package com.lambo.upms.client.controller;

import com.lambo.common.base.BaseController;
import com.lambo.upms.client.dao.model.UpmsUser;
import com.lambo.upms.client.service.api.UpmsClientApiService;
import com.lambo.upms.common.constant.UpmsResult;
import com.lambo.upms.common.constant.UpmsResultConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


/**
 * 用户相关
 */
@Controller
@Api(value = "用户相关", description = "用户相关")
@RequestMapping("/manage")
public class UpmsApiController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(UpmsApiController.class);

    @Autowired
    UpmsClientApiService upmsClientApiService;



    @ApiOperation(value = "获取当前登录用户" ,notes = "获取当前登录用户")
    @RequestMapping(value = "/user/get", method = RequestMethod.GET)
    @ResponseBody
    public Object get() {

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        UpmsUser upmsUser = upmsClientApiService.selectUpmsUserByUsername(username);
        Map<String,Object> resultMap = new HashMap();
        resultMap.put("userid",upmsUser.getUserId());
        resultMap.put("username",upmsUser.getUsername());
        resultMap.put("realname",upmsUser.getRealname());
        resultMap.put("sex",upmsUser.getSex());
        resultMap.put("avatar",upmsUser.getAvatar());
        resultMap.put("email",upmsUser.getEmail());
        resultMap.put("phone",upmsUser.getPhone());
        return new UpmsResult(UpmsResultConstant.SUCCESS,resultMap);

    }

    @ApiOperation(value = "判断当前用户是否具有某权限" ,notes = "判断当前用户是否具有某权限")
    @RequestMapping(value = "/permission/check", method = RequestMethod.GET)
    @ResponseBody
    public Object permissionCheck(
            @ApiParam(name = "permissionValue", required = true, value = "权限值")
            @RequestParam(value = "permissionValue") String permissionValue) {

        return SecurityUtils.getSubject().isPermitted(permissionValue);
    }

}