package com.lambo.auth.client.controller;

import com.lambo.common.base.BaseController;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.common.utils.idgen.IdGenerate;
import com.lambo.common.utils.io.RedisUtil;
import com.lambo.common.utils.web.CookieUtils;
import com.lambo.common.utils.web.http.ServletUtils;
import com.lambo.auth.client.shiro.session.AuthClientSession;
import com.lambo.auth.client.shiro.session.AuthClientSessionDao;
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
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 单点登录管理
 * Created by lambo on 2016/12/10.
 */
@Controller
@RequestMapping("/sso")
@Api(value = "单点登录管理", description = "单点登录管理")
public class AuthCenterController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthCenterController.class);
    /**
     * 会话code
     */
    private final static String LAMBO_SSO_CODE = "lambo-sso-code";
    /**
     * 全局会话key列表
     */
    private final static String LAMBO_SSO_SESSION_IDS = "lambo-sso-session-ids";
    /**
     * code key
     */
    private final static String LAMBO_SSO_CODE_USERNAME = "lambo-sso-code-username";

    /**
     * cookie key
     */
    private final static String LAMBO_SSO_COOKIE_KEY = "lambo-sso-key";


    @Autowired
    AuthClientSessionDao upmsSessionDao;


    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        @ApiParam(name = "rememberMe", value = "记住密码(true/false)")
                        @RequestParam(value = "rememberMe", required = false) String rememberMe) {
        if (StringUtils.isBlank(username)) {
            return new BaseResult(BaseResultConstant.FAILED, "帐号不能为空！");
        }
        if (StringUtils.isBlank(password)) {
            return new BaseResult(BaseResultConstant.FAILED, "密码不能为空！");
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        // 判断是否已登录，如果已登录，则返回成功提示
        String hasCode = RedisUtil.get(LAMBO_SSO_CODE + "_" + sessionId);
        // code校验值
        if (StringUtils.isBlank(hasCode)) {
            // 先注销
            SecurityUtils.getSubject().logout();
            // 使用shiro认证
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            try {
                if (BooleanUtils.toBoolean(rememberMe)) {
                    usernamePasswordToken.setRememberMe(true);
                } else {
                    usernamePasswordToken.setRememberMe(false);
                }
                subject.login(usernamePasswordToken);
            } catch (UnknownAccountException e) {
                return new BaseResult(BaseResultConstant.FAILED, "帐号不存在！");
            } catch (IncorrectCredentialsException e) {
                return new BaseResult(BaseResultConstant.FAILED, "密码错误！");
            } catch (LockedAccountException e) {
                return new BaseResult(BaseResultConstant.FAILED, "帐号已锁定！");
            }
            // 更新session状态
            upmsSessionDao.updateStatus(sessionId, AuthClientSession.OnlineStatus.on_line);
            // 全局会话sessionId列表，供会话管理
            //RedisUtil.lpush(LAMBO_SSO_SESSION_IDS, sessionId.toString());

            // 默认验证帐号密码正确，创建code
            String code = IdGenerate.uuid();
            //过期时间
            int timeout = (int) subject.getSession().getTimeout() / 1000;
            // 会话的code
            RedisUtil.set(LAMBO_SSO_CODE + "_" + sessionId, code, timeout);
            // code校验值
            RedisUtil.set(LAMBO_SSO_CODE_USERNAME + "_" + code, username, timeout);
            // 保存code对应的会话sessionId，方便退出操作
            RedisUtil.sadd(LAMBO_SSO_SESSION_IDS + "_" + code, sessionId, timeout);

            //往Cookie里设置单点登录code
            CookieUtils.setCookie(ServletUtils.getResponse(), LAMBO_SSO_COOKIE_KEY, code, "/",-1);
        }

        return new BaseResult(BaseResultConstant.SUCCESS, "登录成功");
    }


    @ApiOperation(value = "退出登录")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Object logout() {
        // shiro退出登录
        SecurityUtils.getSubject().logout();
        return new BaseResult(BaseResultConstant.SUCCESS, "注销成功");
    }

}