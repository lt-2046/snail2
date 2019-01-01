package com.snail.shiro.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;

/**
 * Created by liutao on 2018/12/31.
 */
public class TokenManager<T> {
    Logger logger = LogManager.getLogger(TokenManager.class.getName());


    /**
     * 获取当前用户的Session
     *
     * @return
     */
    public static Session getSession() {

        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 把值放入到当前登录用户的Session里
     *
     * @param key
     * @param value
     */
    public static void setVal2Session(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    /**
     * 从当前登录用户的Session里取值
     *
     * @param key
     * @return
     */
    public static Object getVal2Session(Object key) {
        return getSession().getAttribute(key);
    }

    /**
     * 获取当前登录的用户对象
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T getToken(Class<T> tClass) {
        return (T)SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 登陆
     * @param username
     * @param password
     * @param rememberMe 是否记住用户
     * @param tClass 返回类型
     * @param <T>
     * @return
     */
    public static <T> T logIn(String username,String password,boolean rememberMe,Class<T> tClass) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(rememberMe);
        SecurityUtils.getSubject().login(token);
        return (T)getToken(tClass);
    }

    /**
     * 退出
     */
    public static void logOut() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * 判断是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        return null != SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 返回用户登陆前用户最后的访问地址
     * @param request
     * @return 如果登录前没有进行访问，则返回null
     */
    public static String getSaveRequsetUrl(ServletRequest request) {
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        String url = null;
        if (null != savedRequest) {
            url = savedRequest.getRequestUrl();
        }
        return url;
    }

}