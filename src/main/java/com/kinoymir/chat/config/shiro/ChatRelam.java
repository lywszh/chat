package com.kinoymir.chat.config.shiro;
//
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

//import org.springframework.beans.factory.annotation.Autowired;
//
public class ChatRelam extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }

    //
//
//    @Autowired
//    private UserService us;
//
//
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        return info;
//    }
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
//            throws AuthenticationException {
//        MyShiroToken token = (MyShiroToken) authenticationToken;
//        User user = us.login(token);
//        return new SimpleAuthenticationInfo(user, user.getPwd(), getName());
//    }
//
}
