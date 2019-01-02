package com.snail.shiro.cache;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;
/**
 * Created by liutao on 2019/1/1.
 */
public class ShiroECCacheManager<K, V> implements CacheManager ,Destroyable {
    private org.springframework.cache.CacheManager cacheManager;
    //由于使用spring项目托管cache接口。无法获取cache中全部信息，所以增加ehcache原生包中缓存管理类进行直接管理
    private  net.sf.ehcache.CacheManager ehCacheManager;

    public net.sf.ehcache.CacheManager getEhCacheManager() {
        return ehCacheManager;
    }

    public void setEhCacheManager(net.sf.ehcache.CacheManager ehCacheManager) {
        this.ehCacheManager = ehCacheManager;
    }

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
        return new ShiroECCache<K,V>(name,getCacheManager(),getEhCacheManager());
    }
}