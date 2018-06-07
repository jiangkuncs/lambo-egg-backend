package com.lambo.auth.client.controller;

import com.lambo.auth.client.constant.AuthClientResult;
import com.lambo.auth.client.constant.AuthClientResultConstant;
import com.lambo.auth.client.service.api.AuthClientApiService;
import com.lambo.common.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;


/**
 * 登录管理
 * Created by lambo on 2016/12/10.
 */
@Controller
@Api(value = "登录管理", description = "登录管理")
public class AuthClientController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(AuthClientController.class);

    @Autowired
    AuthClientApiService authClientApiService;



    @ApiOperation(value = "登录" ,notes = "执行登录操作")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(
            @ApiParam(name = "username", required = true, value = "用户名")
            @RequestParam(value = "username") String username,
            @ApiParam(name = "password", required = true, value = "密码")
            @RequestParam(value = "password") String password,
            @ApiParam(name = "rememberMe", required = false, value = "记住密码(true/false)")
            @RequestParam(value = "rememberMe", required=false) String rememberMe) {

        username = StringUtils.trim(username);

        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            subject.logout();
        }

        // 使用shiro认证
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        if (BooleanUtils.toBoolean(rememberMe)) {
            usernamePasswordToken.setRememberMe(true);
        } else {
            usernamePasswordToken.setRememberMe(false);
        }
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            return new AuthClientResult(AuthClientResultConstant.INVALID_USERNAME, "帐号不存在！");
        } catch (IncorrectCredentialsException e) {
            return new AuthClientResult(AuthClientResultConstant.INVALID_PASSWORD, "密码错误！");
        } catch (LockedAccountException e) {
            return new AuthClientResult(AuthClientResultConstant.INVALID_ACCOUNT, "帐号已锁定！");
        }

        return new AuthClientResult(AuthClientResultConstant.SUCCESS, "登录成功");

    }

    @ApiOperation(value = "退出登录", notes = "退出当前用户, 啥参数也不用传")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Object logout(HttpServletResponse response) {
        // shiro退出登录
        SecurityUtils.getSubject().logout();
        return new AuthClientResult(AuthClientResultConstant.SUCCESS, "注销成功");
    }

}