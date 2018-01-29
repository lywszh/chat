package com.kinoymir.chat.service.user;

import com.kinoymir.chat.config.shiro.MyShiroToken;
import com.kinoymir.chat.entity.user.User;
import com.kinoymir.chat.entity.user.UserExtra;

public interface UserService {
    /**
     * 用户注册
     */
    User register(User user);

    /**
     * 创建用户关联信息
     *
     * @param user
     */
    UserExtra createUserExtra(User user);

    /**
     * 用户登录
     */
    User login(MyShiroToken token);


    /**
     * 修改用户的邮箱或密码
     *
     * @param id
     * @param user
     */
    void editUser(Long id, User user);


    /**
     * 修改用户的额外信息
     *
     * @param UserId
     * @param userExtra
     */
    void editUserExtra(Long userId, UserExtra userExtra);

    /**
     * 通过id获取用户
     *
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * 通过用户id获取其详细信息
     *
     * @param id
     * @return
     */
    UserExtra findByUserId(Long id);
}
