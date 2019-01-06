package com.snail.web.m.controller;

import com.snail.service.m.UserService;
import com.snail.web.m.vo.LoginVo;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by liutao on 2018/12/29.
 */
@Controller
@RequestMapping(value = "/m")
public class MLoginController {
    Logger logger = LogManager.getLogger(MLoginController.class.getName());
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login.do")
    @ResponseBody
    public Map<String,Object>  login(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashedMap();
        userService.login(loginVo);
        result.put("status", 200);
        result.put("message", "登录成功");
        //跳转地址
        /**
         * shiro 获取登录之前的地址
         * 之前0.1版本这个没判断空。
         */
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        String url = null ;
        if(null != savedRequest){
            url = savedRequest.getRequestUrl();
        }
        if(StringUtils.isBlank(url)){
            url = request.getContextPath() + "/m/menu.do";
        }
        result.put("back_url", url);
        return result;

    }
    @RequestMapping(value = "/menu.do")
    public String  menu(ModelMap map,LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) throws Exception {
         map.put("hello","你好");
        return "menu";

    }
}