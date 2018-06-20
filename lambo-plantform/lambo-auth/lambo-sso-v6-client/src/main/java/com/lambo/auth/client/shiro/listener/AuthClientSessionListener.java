package com.lambo.auth.client.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lambo on 2017/2/12.
 */
public class AuthClientSessionListener implements SessionListener {

    private static Logger logger = LoggerFactory.getLogger(AuthClientSessionListener.class);

    @Override
    public void onStart(Session session) {
        logger.info("会话创建：" + session.getId());
    }

    @Override
    public void onStop(Session session) {
        logger.info("会话停止：" + session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        logger.info("会话过期：" + session.getId());
    }

}
