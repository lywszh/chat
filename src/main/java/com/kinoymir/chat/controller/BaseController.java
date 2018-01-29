package com.kinoymir.chat.controller;

import com.kinoymir.chat.config.shiro.MyShiroToken;
import com.kinoymir.chat.entity.user.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class BaseController {


    /**
     * 得到缓存里的用户ID
     *
     * @return
     */
    protected Long getUserIdFromCache() {
        Subject subject = SecurityUtils.getSubject();
        Long id = (Long) subject.getPrincipal();
        return id;
    }

    /**
     * 将用户ID存入缓存
     * @param user
     */
    protected  void setUserIntoCache(User user){
        MyShiroToken token = new MyShiroToken(user);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
    }


}
