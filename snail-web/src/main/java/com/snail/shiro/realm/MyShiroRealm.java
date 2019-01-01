package com.snail.shiro.realm;

import com.snail.service.m.UserService;
import com.snail.web.m.vo.UserVo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liutao on 2018/12/24.
 */
@Service
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //根据自己的需求编写获取授权信息，这里简化代码获取了用户对应的所有权限
//        String username = (String) principalCollection.fromRealm(getName()).iterator().next();
//        if (username != null) {
//            List<String> perms = userService.getPermissionByUserName(username);
//            if (perms != null && !perms.isEmpty()) {
//                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//                for (String each : perms) {
//                    //将权限资源添加到用户信息中
//                    info.addStringPermission(each);
//                }
//                return info;
//            }
//        }
        return null;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if(authenticationToken==null){
            return null;
        }
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        // 通过表单接收的用户名，调用currentUser.login的时候执行
        String username = token.getUsername();
        char[] password = token.getPassword();
        if(username==null || password==null){
            return null;
        }
        UserVo user = new UserVo();
        user.setUserName(username);
        user.setPassword(new String(password));

        if(userService.findUser(user)){
           return new SimpleAuthenticationInfo(user, password, getName());
        }
        return null;
    }
}