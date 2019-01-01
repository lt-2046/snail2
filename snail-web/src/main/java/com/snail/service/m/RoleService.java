package com.snail.service.m;

import com.snail.model.m.entity.MRole;
import com.snail.model.m.mapper.MRoleMapper;
import com.snail.web.m.vo.RoleVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liutao on 2018/12/29.
 */
@Service
public class RoleService {
    Logger logger = LogManager.getLogger(RoleService.class.getName());

    @Autowired
    private MRoleMapper roleMapper;

    public void saveRole(RoleVo roleVo) {
        MRole role = new MRole();
        try {
            BeanUtils.copyProperties(role, roleVo);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        role.setStart(0l);
        roleMapper.insert(role);
//        System.out.println("role id ........................." + role.getId());
    }

    public List<RoleVo> findRoleList() {
        List<MRole> result = roleMapper.selectAll();
        List<RoleVo> returnList = new ArrayList<RoleVo>();
        for (MRole role : result) {
            RoleVo roleVoTemp = new RoleVo();
            try {
                BeanUtils.copyProperties(roleVoTemp, role);
                returnList.add(roleVoTemp);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return returnList;
    }
}