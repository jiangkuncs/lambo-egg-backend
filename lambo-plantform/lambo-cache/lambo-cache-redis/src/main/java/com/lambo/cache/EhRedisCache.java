package com.lambo.cache;

import java.util.concurrent.Callable;

import com.alibaba.fastjson.JSON;
import com.lambo.common.utils.io.RedisUtil;
import com.lambo.common.utils.lang.ObjectUtils;
import com.lambo.common.utils.lang.StringUtils;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * 两级缓存，一级:ehcache,二级为redisCache
 *
 * @author yulin
 */
public class EhRedisCache implements Cache {

    private static final Logger logger = LoggerFactory.getLogger(EhRedisCache.class);

    private String name;

    private net.sf.ehcache.Cache ehCache;

    /**
     * redis里的缓存时长
     */
    private int liveTime;

    private int activeCount = 10;


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this;
    }

    /**
     * 获取自定义缓存
     */
    @Override
    public ValueWrapper get(Object key) {
        Element value = ehCache.get(key);
        if (logger.isInfoEnabled()) {
            logger.info("Cache level1 (ehcache) :{" + name + "}{" + key + "}={" + value + "}");
        }
        if (value != null) {
            return new SimpleValueWrapper(value.getObjectValue());
        }
        String keyStr = name + "-" + key.toString();
        Object objectValue = JSON.parseObject(RedisUtil.get(keyStr),Object.class);
        // 取出来之后缓存到本地
        ehCache.put(new Element(key, objectValue));
        if (logger.isInfoEnabled()) {
            logger.info("Cache level12 (redis) :{" + name + "}{" + key + "}={" + objectValue + "}");
        }
        return (objectValue != null ? new SimpleValueWrapper(objectValue) : null);

    }

    /**
     * 更新自定义缓存
     */
    @Override
    public void put(Object key, Object value) {
        if(null == key || value == key){
            return ;
        }
        ehCache.put(new Element(key, value));
        String keyStr = name + "-" + key.toString();
        Object valueObject = value;
        //redis里最长存8小时的缓存
        if (liveTime == -1) {
            liveTime = 60 * 60 * 8;
        }
        RedisUtil.set(keyStr, JSON.toJSONString(valueObject), liveTime);

    }

    /**
     * 删除指定key缓存
     */
    @Override
    public void evict(Object key) {
        ehCache.remove(key);
        String keyStr = name + "-" + key.toString();
        RedisUtil.remove(keyStr);
        if (logger.isInfoEnabled()) {
            logger.info("Cache level11 (ehcache) remove cache key=" + key);
            logger.info("Cache level12 (redis) remove cache key=" + keyStr);
        }
    }

    /**
     * 清除缓存
     */
    @Override
    public void clear() {
        ehCache.removeAll();
    }


    public net.sf.ehcache.Cache getEhCache() {
        return ehCache;
    }

    public void setEhCache(net.sf.ehcache.Cache ehCache) {
        this.ehCache = ehCache;
    }


    public int getLiveTime() {

        return liveTime;
    }

    public void setLiveTime(int liveTime) {

        this.liveTime = liveTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(int activeCount) {
        this.activeCount = activeCount;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        if (null == key || null == type) {
            return null;
        } else {
            final Class<T> finalType = type;
            final Object object = this.get(key);
            if (finalType != null && finalType.isInstance(object) && null != object) {
                return (T) object;
            } else {
                return null;
            }
        }
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.cache.Cache#putIfAbsent(java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        if (key == null || value == null) {
            return null;
        } else {
            this.put(key, value);
        }
        return new SimpleValueWrapper(value);
    }
}

