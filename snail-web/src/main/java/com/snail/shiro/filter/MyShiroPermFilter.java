package com.snail.shiro.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by liutao on 2018/12/25.
 */
public class MyShiroPermFilter extends AccessControlFilter {

    /**
     * 表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return
     * @throws Exception
     */
    protected boolean isAccessAllowed(javax.servlet.ServletRequest servletRequest, javax.servlet.ServletResponse servletResponse, Object o) throws Exception {
        Object token = SecurityUtils.getSubject().getPrincipal();
        if (null != token || isLoginRequest(servletRequest, servletResponse)) {// && isEnabled()
            return Boolean.TRUE;
        }

        System.out.println("...........        "+((HttpServletRequest) servletRequest).getHeader("X-Requested-With"));
        return Boolean.FALSE;
    }

    /**
     * 表示访问拒绝时是否自己处理，如果返回true表示自己不处理且继续拦截器链执行，返回false表示自己已经处理了（比如重定向到另一个页面）
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //保存Request和Response 到登录后的链接
        saveRequestAndRedirectToLogin(servletRequest, servletResponse);
        return Boolean.FALSE;
    }
}