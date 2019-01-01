package com.snail.web.m.controller;

import com.snail.service.m.UserService;
import com.snail.web.m.vo.UserVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by liutao on 2018/12/21.
 */
@Controller
@RequestMapping(value = "/m")
public class MUserController {
    Logger logger = LogManager.getLogger(MUserController.class.getName());
    @Autowired
    private UserService userService;

    @RequestMapping(value="/saveuser.do")
    public String saveUser(ModelMap map,UserVo user) throws Exception {

        userService.saveUser( user);


        map.put("user", user);
        return "hello";
    }

    @RequestMapping(value="/findUser.do")
    public String findUser(ModelMap map,UserVo user) throws Exception {

        Subject currentUser = SecurityUtils.getSubject();


        List<UserVo> returnList = userService.findUserList(user);


        map.put("userList", returnList);
        return "/m/user";
    }
}