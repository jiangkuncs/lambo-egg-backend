package com.lambo.auth.client.shiro.session;

import com.lambo.auth.client.util.SerializableUtil;
import com.lambo.common.utils.io.RedisUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.*;

/**
 * 基于redis的sessionDao，缓存共享session
 * Created by lambo on 2017/2/23.
 */
public class AuthClientSessionDao extends CachingSessionDAO {

    private static Logger logger = LoggerFactory.getLogger(AuthClientSessionDao.class);
    /**
     * 会话key
     */
    private final static String LAMBO_SSO_SHIRO_SESSION_ID = "lambo-sso-shiro-session-id";
    /**
     * 会话code
     */
    private final static String LAMBO_SSO_CODE = "lambo-sso-code";
    /**
     * 全局会话列表key
     */
    private final static String LAMBO_SSO_SESSION_IDS = "lambo-sso-session-ids";
    /**
     * code key
     */
    private final static String LAMBO_SSO_CODE_USERNAME = "lambo-sso-code-username";


    private static Map<String, Session> sessionMap = new HashMap<String, Session>();

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        RedisUtil.set(LAMBO_SSO_SHIRO_SESSION_ID + "_" + sessionId, SerializableUtil.serialize(session), (int) session.getTimeout() / 1000);
        if (logger.isInfoEnabled()) {
            logger.info("doCreate >>>>> sessionId={}", sessionId);
        }

        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        String session = RedisUtil.get(LAMBO_SSO_SHIRO_SESSION_ID + "_" + sessionId);
        if (logger.isInfoEnabled()) {
            logger.info("doReadSession >>>>> sessionId={}", sessionId);
        }
        return SerializableUtil.deserialize(session);
    }

    @Override
    protected void doUpdate(Session session) {
        // 如果会话过期/停止 没必要再更新了
        if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
            return;
        }
        String sessionId = session.getId().toString();
        String code = RedisUtil.get(LAMBO_SSO_CODE + "_" + sessionId);
        int timeout = (int) session.getTimeout() / 1000;
        RedisUtil.set(LAMBO_SSO_SHIRO_SESSION_ID + "_" + sessionId, SerializableUtil.serialize(session), timeout);
        Jedis jedis = RedisUtil.getJedis();
        //只要这个属性还活着，其他的丢了就丢了，再创建就是了
        jedis.expire(LAMBO_SSO_CODE_USERNAME + "_" + code,timeout);
        jedis.close();
    }

    @Override
    protected void doDelete(Session session) {
        Jedis jedis = RedisUtil.getJedis();
        String sessionId = session.getId().toString();
        // 当前会话code
        String code = RedisUtil.get(LAMBO_SSO_CODE + "_" + sessionId);
        // 清除code校验值
        jedis.del(LAMBO_SSO_CODE_USERNAME + "_" + code);
        // 清除所有局部会话
        Set<String> sessionIds = jedis.smembers(LAMBO_SSO_SESSION_IDS + "_" + code);
        for (String sId : sessionIds) {
            jedis.del(LAMBO_SSO_CODE + "_" + sId);
        }
        jedis.del(LAMBO_SSO_SESSION_IDS + "_" + code);
        jedis.close();
        // 维护会话id列表，提供会话分页管理
        //RedisUtil.lrem(LAMBO_SSO_SESSION_IDS, 1, sessionId);
        if (logger.isInfoEnabled()) {
            logger.info("doUpdate >>>>> sessionId={}", sessionId);
        }
    }

    /**
     * 获取会话列表
     *
     * @param offset
     * @param limit
     * @return
     */
    public Map getActiveSessions(int offset, int limit) {
        Map sessions = new HashMap();
        Jedis jedis = RedisUtil.getJedis();
        // 获取在线会话总数
        long total = jedis.llen(LAMBO_SSO_SESSION_IDS);
        // 获取当前页会话详情
        List<String> ids = jedis.lrange(LAMBO_SSO_SESSION_IDS, offset, (offset + limit - 1));
        List<Session> rows = new ArrayList<>();
        for (String id : ids) {
            String session = RedisUtil.get(LAMBO_SSO_SHIRO_SESSION_ID + "_" + id);
            // 过滤redis过期session
            if (null == session) {
                RedisUtil.lrem(LAMBO_SSO_SESSION_IDS, 1, id);
                total = total - 1;
                continue;
            }
            rows.add(SerializableUtil.deserialize(session));
        }
        jedis.close();
        sessions.put("total", total);
        sessions.put("rows", rows);
        return sessions;
    }

    /**
     * 强制退出
     *
     * @param ids
     * @return
     */
    public int forceout(String ids) {
        String[] sessionIds = ids.split(",");
        for (String sessionId : sessionIds) {
            // 会话增加强制退出属性标识，当此会话访问系统时，判断有该标识，则退出登录
            String session = RedisUtil.get(LAMBO_SSO_SHIRO_SESSION_ID + "_" + sessionId);
            AuthClientSession upmsSession = (AuthClientSession) SerializableUtil.deserialize(session);
            upmsSession.setStatus(AuthClientSession.OnlineStatus.force_logout);
            upmsSession.setAttribute("FORCE_LOGOUT", "FORCE_LOGOUT");
            RedisUtil.set(LAMBO_SSO_SHIRO_SESSION_ID + "_" + sessionId, SerializableUtil.serialize(upmsSession), (int) upmsSession.getTimeout() / 1000);
        }
        return sessionIds.length;
    }

    /**
     * 更改在线状态
     *
     * @param sessionId
     * @param onlineStatus
     */
    public void updateStatus(Serializable sessionId, AuthClientSession.OnlineStatus onlineStatus) {
        AuthClientSession session = (AuthClientSession) doReadSession(sessionId);
        if (null == session) {
            return;
        }
        session.setStatus(onlineStatus);
        RedisUtil.set(LAMBO_SSO_SHIRO_SESSION_ID + "_" + session.getId(), SerializableUtil.serialize(session), (int) session.getTimeout() / 1000);
    }

}
