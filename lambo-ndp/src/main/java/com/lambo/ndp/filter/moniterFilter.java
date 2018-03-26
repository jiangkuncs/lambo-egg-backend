package com.lambo.ndp.filter;

import com.alibaba.fastjson.JSONObject;
import com.lambo.ndp.constant.IdentCodeConstant;
import com.lambo.ndp.constant.MoniterConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class moniterFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(moniterFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(countVisits()){
            //弹出验证码
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code",IdentCodeConstant.OPEN_IDENT_CODE.code);
            jsonObject.put("data",1);
            PrintWriter out = response.getWriter();
            out.write(jsonObject.toString());
            out.close();
        }else{
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean countVisits(){
        boolean result = false;
        if(MoniterConstants.visitMap == null){
            MoniterConstants.visitMap = new HashMap();
        }
        Subject subject = SecurityUtils.getSubject();
        String userName = (String) subject.getPrincipal();
        if(StringUtils.isNotBlank(userName)){
            int count = 0;
            if(null != MoniterConstants.visitMap.get(userName)){
                count = (int)MoniterConstants.visitMap.get(userName);
            }
            if(count >= MoniterConstants.threshold){
                result = true;
            }else{
                count = count + 1;
                MoniterConstants.visitMap.put(userName,count);
            }
        }
        return result;
    }
}
