package com.snail.service.m;

import com.snail.model.m.entity.MUser;
import com.snail.model.m.mapper.MUserMapper;
import com.snail.shiro.session.CustomEcSessionManager;
import com.snail.shiro.utils.TokenManager;
import com.snail.web.m.vo.LoginVo;
import com.snail.web.m.vo.UserVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by liutao on 2018/12/24.
 */
@Service
public class UserService {
    Logger logger = LogManager.getLogger(UserService.class.getName());
    @Autowired
    private MUserMapper userMapper;

    @Autowired
    private CustomEcSessionManager customEcSessionManager;

    public void saveUser(UserVo userVo) {

        MUser user = new MUser();
        try {
            BeanUtils.copyProperties(user, userVo);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        user.setStart(0);
        user.setCreateTime(new Date());
        userMapper.insert(user);
    }

    public List<UserVo> findUserList(UserVo userVo) {
        List<MUser> result = userMapper.selectAll();
        List<UserVo> returnList = new ArrayList<UserVo>();
        for (MUser user : result) {
            UserVo userVoTemp = new UserVo();
            try {
                BeanUtils.copyProperties(userVoTemp, user);
                returnList.add(userVoTemp);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        customEcSessionManager.getAllUser();
        Session sessiom = SecurityUtils.getSubject().getSession();
        System.out.println(sessiom.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
        return returnList;
    }

    public void login(LoginVo loginVo) throws Exception {
//        if (!currentUser.isAuthenticated()) {

        try {
            UserVo userVo = TokenManager.logIn(loginVo.getUsername(), loginVo.getPassword(), loginVo.isRememberMe(), UserVo.class);


            System.out.println(userVo);


            customEcSessionManager.getAllUser();
            Session sessiom = SecurityUtils.getSubject().getSession();
            Collection<Object> attributeKeys = sessiom.getAttributeKeys();
            System.out.println(sessiom.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));

        } catch (UnknownAccountException uae) {
            throw new Exception("账户不存在");
        } catch (IncorrectCredentialsException ice) {
            throw new Exception("密码不正确");
        } catch (LockedAccountException lae) {
            throw new Exception("用户被锁定了 ");
        } catch (AuthenticationException ae) {
            ae.printStackTrace();
            throw new Exception("未知错误");
        }
//        }
    }

    public boolean findUser(UserVo userVo) {
        MUser user = new MUser();
        user.setUserName(userVo.getUserName());
        user.setPassword(userVo.getPassword());
        user.setStart(0);
        List<MUser> result = userMapper.select(user);

        return CollectionUtils.isNotEmpty(result);
    }

    public List<String> getPermissionByUserName(String name) {
        List<String> permissionList = new ArrayList<String>();
        permissionList.add("admin");

        return permissionList;
    }

    public String getPasswordByUserName(String name) {
        return "index.jspl";
    }
}