package com.kinoymir.chat.service.user;

import com.kinoymir.chat.config.shiro.MyShiroToken;
import com.kinoymir.chat.entity.user.User;
import com.kinoymir.chat.entity.user.UserExtra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface UserService {
    /**
     * 用户注册
     */
    User register(User user);

    /**
     * 创建用户关联信息
     *
     * @param user 用户表
     */
    UserExtra createUserExtra(User user);

    /**
     * 用户登录
     *
     * @param token shiro封装的用户信息
     */
    User login(MyShiroToken token);


    /**
     * 修改用户的邮箱或密码
     *
     * @param id   用户id
     * @param user 传入的用户信息
     */
    void editUser(Long id, User user);


    /**
     * 修改用户的额外信息
     *
     * @param userId    用户id
     * @param userExtra 用户额外信息
     */
    void editUserExtra(Long userId, UserExtra userExtra);

    /**
     * 通过id获取用户
     *
     * @param id 用户id
     * @return 用户表
     */
    User findById(Long id);

    /**
     * 通过用户id获取其详细信息
     *
     * @param id 用户id
     * @return 用户额外信息
     */
    UserExtra findByUserId(Long id);

    /**
     * 检验此邮箱是否可以使用
     *
     * @param email 邮箱
     */
    void checkEmail(String email);

    /**
     * 检验此手机号是否可以使用
     *
     * @param cellPhone 手机
     */
    void checkCellPhone(String cellPhone);

    /**
     * 检验此名字是否可以使用
     *
     * @param name 用户名
     */
    void checkName(String name);

    /**
     * 后台，获取所有用户数据
     *
     * @param name      查询的用户名
     * @param cellPhone 查询的手机号
     * @param email     查询的邮箱号
     * @param pageable  分页封装
     * @return 返回封装的数据
     */
    Page<UserExtra> listUser(String name, String cellPhone, String email, Pageable pageable);
}
