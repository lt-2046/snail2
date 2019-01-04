package com.snail.shiro.cache;

import net.sf.ehcache.Element;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by liutao on 2019/1/1.
 */
public class ShiroECCache<K, V> implements org.apache.shiro.cache.Cache<K, V> {
    Logger logger = LogManager.getLogger(ShiroECCache.class.getName());
    private CacheManager cacheManager;
    private  net.sf.ehcache.CacheManager ehCacheManager;
    private Cache cache;
    private net.sf.ehcache.Ehcache ehcache;
    //    private RedisCache cache2;
    public ShiroECCache(String name, CacheManager cacheManager,net.sf.ehcache.CacheManager ehCacheManager) {
        if (name == null || cacheManager == null) {
            throw new IllegalArgumentException("cacheManager or CacheName cannot be null.");
        }
        this.cacheManager = cacheManager;
        this.ehCacheManager = ehCacheManager;
        //这里首先是从父类中获取这个cache,如果没有会创建一个redisCache,初始化这个redisCache的时候
        //会设置它的过期时间如果没有配置过这个缓存的，那么默认的缓存时间是为0的，如果配置了，就会把配置的时间赋予给这个RedisCache
        //如果从缓存的过期时间为0，就表示这个RedisCache不存在了，这个redisCache实现了spring中的cache
        this.cache = cacheManager.getCache(name);
        this.ehcache = ehCacheManager.getEhcache(name);
    }

    public V get(K key) throws CacheException {
        logger.info("从缓存中获取key为{}的缓存信息 ......." + cache.getName(), key);
        if (key == null) {
            return null;
        }
//        ValueWrapper valueWrapper = cache.get(key);
//        if (valueWrapper == null) {
//            return null;
//        }
//        return (V) valueWrapper.get();
        Element value = ehcache.get(key);
        if (value == null) {
            return null;
        }
        return (V) value.getObjectValue();
    }

    public V put(K key, V value) throws CacheException {
        logger.info("创建新的缓存，信息为：{}={}", key, value);
        cache.put(key, value);
        return get(key);
    }

    public V remove(K key) throws CacheException {
        logger.info("干掉key为{}的缓存", key);
        V v = get(key);
        cache.evict(key);//干掉这个名字为key的缓存
        return v;
    }

    public void clear() throws CacheException {
        logger.info("清空所有的缓存");
        cache.clear();
    }

    public int size() {
        return cacheManager.getCacheNames().size();
    }

    /**
     * 获取缓存中所的key值
     */
    public Set<K> keys() {
        return (Set<K>) cacheManager.getCacheNames();
    }

    /**
     * 获取缓存中所有的values值
     */
    public Collection<V> values() {
        logger.error(cache.getName());
        logger.error(cacheManager.getCacheNames());
        try {
            Collection<String> cacheNames = cacheManager.getCacheNames();
            for (String cacheName: cacheNames){
                net.sf.ehcache.Ehcache ehcacheTemp = ehCacheManager.getCache(cacheName);
                System.out.println("cacheName    " + cacheName);
                List list = ehcacheTemp.getKeys();
                for(Object l :list){
                    System.out.print(l + "         ");
                    Element temp = ehcacheTemp.get(l);
//                    System.out.println(temp.getObjectValue());
                    if(temp.getObjectValue() instanceof SimpleSession){
                        SimpleSession simpleSession = (SimpleSession)temp.getObjectValue();
                        System.out.println(simpleSession.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
                    }
                }
            }
//            System.out.println(ehcache.getName());
//            List list = ehcache.getKeys();
//            for(Object l :list){
//                System.out.println(l);
//                Element temp = ehcache.get(l);
//                System.out.println(temp.getObjectValue());
//                ValueWrapper cacheV = cache.get(l);
//                if(cacheV.get() instanceof UserVo){
//                    UserVo user = (UserVo) cacheV.get() ;
//
//                }
//                System.out.println(cacheV);
//            }
//            System.out.println(list);
        }catch (Exception e){
            e.printStackTrace();
        }
        Collection<V> test = null;
//        return (Collection<V>) cache.get(cacheManager.getCacheNames()).get();
        return test;
    }

    public String toString() {
        return "ShiroSpringCache [cache=" + cache + "]";
    }
}