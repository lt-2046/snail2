package com.snail.web.m.controller;

import com.snail.service.m.PermissionService;
import com.snail.web.m.vo.PermissionVo;
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
public class MPermissionController {
    Logger logger = LogManager.getLogger(MPermissionController.class.getName());
    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value="/findPermission.do")
    public String findPermission(ModelMap map) throws Exception {

        List<PermissionVo> returnList = permissionService.findPermissionList();


        map.put("rolePermissionList", returnList);
        return "/m/permission";
    }
}