package com.snail.service.m;

import com.snail.model.m.entity.MRolePermission;
import com.snail.model.m.mapper.MRolePermissionMapper;
import com.snail.web.m.vo.RolePermissionVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liutao on 2018/12/30.
 */
@Service
public class RolePermissionService {
    Logger logger = LogManager.getLogger(RolePermissionService.class.getName());
    @Autowired
    private MRolePermissionMapper rolePermissionMapper;

    public List<RolePermissionVo> findRolePermissionList() {
        List<MRolePermission> result = rolePermissionMapper.selectAll();
        List<RolePermissionVo> returnList = new ArrayList<RolePermissionVo>();
        for (MRolePermission permission : result) {
            RolePermissionVo rolePermissionVoTemp = new RolePermissionVo();
            try {
                BeanUtils.copyProperties(rolePermissionVoTemp, permission);
                returnList.add(rolePermissionVoTemp);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return returnList;
    }
}