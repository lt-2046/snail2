package com.snail.shiro.cache;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;
/**
 * Created by liutao on 2019/1/1.
 */
public class ShiroECCacheManager<K, V> implements CacheManager ,Destroyable {
    private org.springframework.cache.CacheManager cacheManager;

    public org.springframework.cache.CacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(org.springframework.cache.CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void destroy() throws Exception {
        cacheManager = null;
    }

    public <K, V> Cache<K, V> getCache(String name)  {
        if (name == null ){
            return null;
        }
        return new ShiroECCache<K,V>(name,getCacheManager());
    }
}