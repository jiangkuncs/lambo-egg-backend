package com.lambo.auth.client.shiro.filter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lambo.auth.client.constant.AuthClientResult;
import com.lambo.auth.client.constant.AuthClientResultConstant;
import com.lambo.common.utils.io.PropertiesFileUtil;
import com.lambo.common.utils.web.CookieUtils;
import com.lambo.common.utils.web.ResponseUtil;
import com.lambo.common.utils.web.http.HttpClientUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 重写authc过滤器
 *
 * @author sunzhen
 */
public class UpmsAuthenticationFilter extends AuthenticationFilter {

    private final static Logger logger = LoggerFactory.getLogger(UpmsAuthenticationFilter.class);

    PropertiesFileUtil ssoConfig = PropertiesFileUtil.getInstance("lambo-sso-v6-client");


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        boolean isAuthenticated = subject.isAuthenticated();
        if (!isAuthenticated) {
            try {
                JSONObject userInfoObject = getV6UserInfo((HttpServletRequest) request);
                String userId = userInfoObject.getString("user_id");
                subject.login(new UsernamePasswordToken(userId, ""));
                Session session = subject.getSession();
                //把用户信息保存到session里
                saveUserInfoToSession(userInfoObject,session);
                return true;
            } catch (Exception e) {
                logger.error("单点登录失败", e);
                return false;
            }
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        ResponseUtil.sendJson(response, new AuthClientResult(AuthClientResultConstant.ACCESS_DENIED, "单点登录失败或会话已失效"));
        return false;
    }

    /**
     * 请求v6单点服务获取用户信息
     *
     * @param request
     * @return
     */
    private JSONObject getV6UserInfo(HttpServletRequest request) throws Exception {

        if(logger.isInfoEnabled()){
            logger.info("与v6单点登录开始....");
        }

        String apacheIp = ssoConfig.get("v6.apache.ip");
        String userInfoRestUrl = ssoConfig.get("v6.userinfo.url");

        if (!apacheIp.startsWith("http")) {
            apacheIp = "http://" + apacheIp;
        }
        if (apacheIp.endsWith("/")) {
            apacheIp = apacheIp.substring(0, apacheIp.length() - 1);
        }

        String ssoToken = CookieUtils.getCookie(request, "sso_token");
        // 获得登陆后的 Cookie
        org.apache.commons.httpclient.Cookie cookie = new org.apache.commons.httpclient.Cookie();
        cookie.setName("JSESSIONID");
        cookie.setValue(ssoToken);
        cookie.setPath("/");
        cookie.setDomain(apacheIp);

        String postUrl = apacheIp + userInfoRestUrl;
        if(logger.isInfoEnabled()){
            logger.info("请求用户信息url:"+postUrl);
        }

        HttpPost httpPost = new HttpPost(postUrl);
        // 每次访问需授权的网址时需带上前面的 cookie 作为通行证
        httpPost.setHeader("cookie", cookie.toString());

        String result = HttpClientUtils.executeRequest(httpPost);
        if(logger.isInfoEnabled()){
            logger.info("用户信息字符串:"+result);
        }
        result = result.substring(1, result.length() - 1).replaceAll("\\\\", "");

        JSONObject userInfoObj = JSONObject.parseObject(result);
        return userInfoObj;
    }

    /**
     * 把用户信息放到session里
     * @param userInfoObject
     * @param session
     * @throws Exception
     */
    private void saveUserInfoToSession(JSONObject userInfoObject,Session session) throws Exception{
        String userId = userInfoObject.getString("user_id");
        String userName = userInfoObject.getString("user_name");
        String comId = userInfoObject.getString("com_id");
        JSONArray dataPermissions = userInfoObject.getJSONArray("organ_types");
        session.setAttribute("userId",userId);
        session.setAttribute("userName",userName);
        session.setAttribute("comId",comId);
        session.setAttribute("dataPermissions",dataPermissions);
    }

}
