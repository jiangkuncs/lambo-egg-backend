package com.lambo.auth.client.shiro.filter;

import com.lambo.auth.client.constant.AuthClientResult;
import com.lambo.auth.client.constant.AuthClientResultConstant;
import com.lambo.common.utils.io.RedisUtil;
import com.lambo.common.utils.web.CookieUtils;
import com.lambo.common.utils.web.ResponseUtil;
import com.lambo.auth.client.shiro.session.AuthClientSessionDao;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 重写authc过滤器
 * Created by shulambo on 2017/3/11.
 */
public class AuthClientAuthenticationFilter extends AuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthClientAuthenticationFilter.class);

    /**
     * 会话code
     */
    private final static String LAMBO_SSO_CODE = "lambo-sso-code";
    /**
     * 单点同一个code所有局部会话key
     */
    private final static String LAMBO_SSO_SESSION_IDS = "lambo-sso-session-ids";
    /**
     * code key
     */
    private final static String LAMBO_SSO_CODE_USERNAME = "lambo-sso-code-username";
    /**
     * sso code in cookie
     */
    private final static String LAMBO_SSO_COOKIE_KEY = "lambo-sso-key";


    @Autowired
    AuthClientSessionDao upmsSessionDao;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return validateClient(request, response);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        ResponseUtil.sendJson(response, new AuthClientResult(AuthClientResultConstant.ACCESS_DENIED, "未登录或会话已失效"));
        return false;
    }

    /**
     * 检查登录状态
     *
     * @param request
     */
    private boolean validateClient(ServletRequest request, ServletResponse response) {
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        int timeOut = (int) session.getTimeout() / 1000;
        // 判断是否有会话
        String codeInRedis = RedisUtil.get(LAMBO_SSO_CODE + "_" + session.getId());
        if (StringUtils.isNotBlank(codeInRedis)) {
            return true;
        }
        // 判断是否有认证中心code
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String code = CookieUtils.getCookie(httpServletRequest, LAMBO_SSO_COOKIE_KEY);
        // 已拿到code
        if (StringUtils.isNotBlank(code)) {
            String username = RedisUtil.get(LAMBO_SSO_CODE_USERNAME + "_" + code);
            if (StringUtils.isNotBlank(username)) {
                // code校验正确，创建会话
                RedisUtil.set(LAMBO_SSO_CODE + "_" + sessionId, code, timeOut);
                // 保存code对应的会话sessionId，方便退出操作
                RedisUtil.sadd(LAMBO_SSO_SESSION_IDS + "_" + code, sessionId, timeOut);
                if(logger.isInfoEnabled()){
                    logger.debug("当前code={}，对应的注册系统个数：{}个", code, RedisUtil.getJedis().scard(LAMBO_SSO_SESSION_IDS + "_" + code));
                }
                // client无密认证
                subject.login(new UsernamePasswordToken(username, ""));
                return true;
            }
        }
        return false;
    }

}
