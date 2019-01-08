package com.snail.web.m.controller;

import com.snail.service.m.PermissionService;
import com.snail.service.m.UserService;
import com.snail.shiro.utils.TokenManager;
import com.snail.web.base.BaseController;
import com.snail.web.m.vo.LoginVo;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class MLoginController extends BaseController {
    Logger logger = LogManager.getLogger(MLoginController.class.getName());
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/login.do")
    @ResponseBody
    public Map<String, Object> login(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashedMap();
        userService.login(loginVo);
        result.put("status", 200);
        result.put("message", "登录成功");
        //跳转地址
        /**
         * shiro 获取登录之前的地址
         * 之前0.1版本这个没判断空。
         */
        String url = TokenManager.getSaveRequsetUrl(request);
        ;

        if (StringUtils.isBlank(url)) {
            url = request.getContextPath() + "/m/menu.do";
        }
        result.put("back_url", url);
        return result;

    }

    @RequestMapping(value = "/menu.do")
    public String menu(ModelMap map, LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String menu = (String) TokenManager.getVal2Session("menu");
        if(menu==null){
            String menuString = permissionService.findPermissionByMenu();
            TokenManager.setVal2Session("menu",menuString);
        }

        return "menu";
    }
}