package com.snail.web.m.controller;

import com.snail.service.m.RoleService;
import com.snail.web.m.vo.RoleVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by liutao on 2018/12/29.
 */
@Controller
@RequestMapping(value = "/m")
public class MRoleController {
    Logger logger = LogManager.getLogger(MRoleController.class.getName());
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/saverole.do")
    public String sayHello(ModelMap map, RoleVo roleVo) throws Exception {

        roleService.saveRole(roleVo);

        return "hello";
    }

    @RequestMapping(value="/findRole.do")
    public String findPermission(ModelMap map) throws Exception {

        List<RoleVo> returnList = roleService.findRoleList();


        map.put("roleList", returnList);
        return "/m/role";
    }
}