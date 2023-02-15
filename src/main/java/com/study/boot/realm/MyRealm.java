package com.study.boot.realm;

import com.study.boot.domain.User;
import com.study.boot.service.UserService;
import com.study.boot.util.MD5Utils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());
        User user = userService.getUserByUsername(username);
        AuthenticationInfo info = null;
        if (user != null){
            info = new SimpleAuthenticationInfo(
                    authenticationToken.getPrincipal(),
                    user.getPwd(),
                    ByteSource.Util.bytes(MD5Utils.SALT),
                    authenticationToken.getPrincipal().toString()
            );
        }

        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println(principalCollection.getPrimaryPrincipal());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("admin");
        info.addStringPermission("user:delete");
        return info;
    }
}
