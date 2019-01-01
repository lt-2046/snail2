package com.snail.web.m.controller;

import com.snail.service.m.RolePermissionService;
import com.snail.web.m.vo.RolePermissionVo;
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
public class MRolePermissionController {
    Logger logger = LogManager.getLogger(MRolePermissionController.class.getName());
    @Autowired
    private RolePermissionService rolePermissionService;

    @RequestMapping(value="/findRolePermission.do")
    public String findRolePermission(ModelMap map) throws Exception {

        List<RolePermissionVo> returnList = rolePermissionService.findRolePermissionList();


        map.put("permissionList", returnList);
        return "/m/rolePermission";
    }
}