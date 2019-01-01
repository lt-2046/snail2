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

    protected boolean isAccessAllowed(javax.servlet.ServletRequest servletRequest, javax.servlet.ServletResponse servletResponse, Object o) throws Exception {
        Object token = SecurityUtils.getSubject().getPrincipal();
        if (null != token || isLoginRequest(servletRequest, servletResponse)) {// && isEnabled()
            return Boolean.TRUE;
        }

        System.out.println("...........        "+((HttpServletRequest) servletRequest).getHeader("X-Requested-With"));
        return Boolean.FALSE;
    }

    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //保存Request和Response 到登录后的链接
        saveRequestAndRedirectToLogin(servletRequest, servletResponse);
        return Boolean.FALSE;
    }
}