package com.kinoymir.chat.service.user.impl;

import com.kinoymir.chat.entity.user.User;
import com.kinoymir.chat.entity.user.UserExtra;
import com.kinoymir.chat.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    /**
     * 用户注册
     *
     * @param user
     */
    @Override
    public User createUser(User user) {
        return null;
    }

    /**
     * 创建用户关联信息
     *
     * @param user
     */
    @Override
    public UserExtra createUserExtra(User user) {
        return null;
    }

    /**
     * 修改用户的邮箱或密码
     *
     * @param user
     * @param email
     * @param pwd
     */
    @Override
    public void editUser(User user, String email, String pwd) {

    }

    /**
     * 修改用户的头像或个性签名
     *
     * @param user
     * @param code
     * @param personNote
     */
    @Override
    public void editUserExtra(User user, Integer code, String personNote) {

    }

    /**
     * 通过id获取用户
     *
     * @param id
     * @return
     */
    @Override
    public User findById(Long id) {
        return null;
    }

    /**
     * 通过用户id获取其详细信息
     *
     * @param id
     * @return
     */
    @Override
    public UserExtra findByUserId(Long id) {
        return null;
    }
}
