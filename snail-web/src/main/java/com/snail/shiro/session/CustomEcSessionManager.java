package com.snail.shiro.session;

import com.snail.shiro.cache.ShiroECCacheManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;

import java.util.Collection;
import java.util.List;

/**
 * Created by liutao on 2019/1/1.
 */
public class CustomEcSessionManager {

    Logger logger = LogManager.getLogger(CustomEcSessionManager.class.getName());
    private ShiroECCacheManager shiroECCacheManager;
    private EnterpriseCacheSessionDAO enterpriseCacheSessionDAO;

    public ShiroECCacheManager getShiroECCacheManager() {
        return shiroECCacheManager;
    }

    public void setShiroECCacheManager(ShiroECCacheManager shiroECCacheManager) {
        this.shiroECCacheManager = shiroECCacheManager;
    }

    public EnterpriseCacheSessionDAO getEnterpriseCacheSessionDAO() {
        return enterpriseCacheSessionDAO;
    }

    public void setEnterpriseCacheSessionDAO(EnterpriseCacheSessionDAO enterpriseCacheSessionDAO) {
        this.enterpriseCacheSessionDAO = enterpriseCacheSessionDAO;
    }

    public List getAllUser(){
        Collection<Session> allSession = enterpriseCacheSessionDAO.getActiveSessions();
        if(allSession==null){
            return null;
        }
        for (Session session : allSession) {
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if(null == obj){
                return null;
            }
            if(obj instanceof SimplePrincipalCollection){
                SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
                obj = spc.getPrimaryPrincipal();
            }
        }

        return null;
    }
}