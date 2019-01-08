package com.snail.service.m;

import com.snail.model.m.entity.MPermission;
import com.snail.model.m.mapper.MPermissionMapper;
import com.snail.utils.tree.Tree;
import com.snail.utils.tree.TreeNode;
import com.snail.web.m.vo.PermissionVo;
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
public class PermissionService {
    Logger logger = LogManager.getLogger(PermissionService.class.getName());
    @Autowired
    private MPermissionMapper permissionMapper;

    @Autowired

    public List<PermissionVo> findPermissionList() {

        List<MPermission> result = permissionMapper.selectAllPermission();
        List<PermissionVo> returnList = new ArrayList<PermissionVo>();
        for (MPermission permission : result) {
            PermissionVo permissionVoTemp = new PermissionVo();
            try {
                BeanUtils.copyProperties(permissionVoTemp, permission);
                returnList.add(permissionVoTemp);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return returnList;
    }

    public List<TreeNode> findPermissionByRole() {

        List<MPermission> result = permissionMapper.selectAllPermission();
        List<PermissionVo> returnList = new ArrayList<PermissionVo>();
//        for (MPermission permission : result) {
//            PermissionVo permissionVoTemp = new PermissionVo();
//            try {
//
//                BeanUtils.copyProperties(permissionVoTemp, permission);
//
//
//                returnList.add(permissionVoTemp);
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }
        Tree tree = new Tree(result);
//        System.out.println(tree.buildTree());
        return tree.buildTree();
    }


}