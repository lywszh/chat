package com.kinoymir.chat.service.user;

import com.kinoymir.chat.entity.user.User;
import com.kinoymir.chat.entity.user.UserExtra;

public interface UserService {
    /**
     * 用户注册
     */
    User createUser(User user);

    /**
     * 创建用户关联信息
     *
     * @param user
     */
    UserExtra createUserExtra(User user);

    /**
     * 用户登录
     */
//    void login();


    /**
     * 修改用户的邮箱或密码
     *
     * @param user
     * @param email
     * @param pwd
     */
    void editUser(User user, String email, String pwd);


    /**
     * 修改用户的头像或个性签名
     *
     * @param user
     * @param code
     * @param personNote
     */
    void editUserExtra(User user, Integer code, String personNote);

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
