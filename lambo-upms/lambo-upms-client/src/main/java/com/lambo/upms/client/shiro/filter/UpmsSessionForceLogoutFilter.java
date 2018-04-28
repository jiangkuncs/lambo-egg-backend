package com.lambo.upms.client.shiro.filter;

import com.lambo.common.utils.web.ResponseUtil;
import com.lambo.upms.client.constant.UpmsClientResult;
import com.lambo.upms.client.constant.UpmsClientResultConstant;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 强制退出会话过滤器
 * Created by lambo on 2017/3/1.
 */
public class UpmsSessionForceLogoutFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Session session = getSubject(request, response).getSession(false);
        if(session == null) {
            return true;
        }
        boolean forceout = session.getAttribute("FORCE_LOGOUT") == null;
        return  forceout;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        ResponseUtil.sendJson(response,new UpmsClientResult(UpmsClientResultConstant.ACCESS_DENIED,"未登录或会话已失效"));
        return false;
    }

}
