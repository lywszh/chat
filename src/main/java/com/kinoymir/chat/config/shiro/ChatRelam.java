package com.kinoymir.chat.config.shiro;
//
import com.kinoymir.chat.entity.user.User;
import com.kinoymir.chat.service.user.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

//import org.springframework.beans.factory.annotation.Autowired;
//
public class ChatRelam extends AuthorizingRealm {
    @Autowired
    private UserService us;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Long userId = (Long)principalCollection.fromRealm(getName()).iterator().next();
        Set<String> roles =new HashSet<String>();
        if(userId<10){
            roles.add("admin");
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        MyShiroToken token = (MyShiroToken) authenticationToken;
        User user=us.login(token);
        return new SimpleAuthenticationInfo(user.getId(), token.getPwd(), getName());
    }


}
