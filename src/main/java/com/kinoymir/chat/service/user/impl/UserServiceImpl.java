package com.kinoymir.chat.service.user.impl;

import com.kinoymir.chat.common.ChatRuntimeException;
import com.kinoymir.chat.config.shiro.MyShiroToken;
import com.kinoymir.chat.dao.user.UserDao;
import com.kinoymir.chat.dao.user.UserExtraDao;
import com.kinoymir.chat.entity.user.User;
import com.kinoymir.chat.entity.user.UserExtra;
import com.kinoymir.chat.service.user.UserService;
import org.apache.shiro.authc.AccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao ud;

    @Autowired
    private UserExtraDao ued;

    /**
     * 用户注册
     *
     * @param user 用户表
     */
    @Override
    public User register(User user) {
        checkCellPhone(user.getCellPhone());
        checkEmail(user.getEmail());
        checkName(user.getName());
        user.setRegisterTime(LocalDateTime.now());
        user = ud.save(user);
        createUserExtra(user);
        return user;
    }

    /**
     * 创建用户关联信息
     *
     * @param user 用户表
     */
    @Override
    public UserExtra createUserExtra(User user) {
        Long userId = user.getId();
        if (userId == null) {
            throw new ChatRuntimeException("用户id为空");
        }
        UserExtra ue = new UserExtra(user);
        return ued.save(ue);
    }

    /**
     * 用户登录
     *
     * @param token shiro封装的用户信息
     */
    @Override
    public User login(MyShiroToken token) {
        User user = null;
        if (StringUtils.hasText(token.getCellPhone())) {
            user = ud.findByCellPhone(token.getCellPhone());
        } else if (StringUtils.hasText(token.getEmail())) {
            user = ud.findByEmail(token.getEmail());
        }
        if (null == user) {
            throw new AccountException("帐号不存在");
        }
        if (!user.getPwd().equals(token.getPwd())) {
            throw new AccountException("密码错误");
        }
        user.setLastLoginTime(LocalDateTime.now());
        ud.save(user);
        return user;
    }

    /**
     * 修改用户的邮箱或密码
     *
     * @param id 用户id
     * @param user 传入的用户信息
     */
    @Override
    public void editUser(Long id, User user) {
        User user_old = findById(id);
        String email=user.getEmail();
        if (StringUtils.hasText(email)) {
            checkEmail(email);
            user_old.setEmail(email);
            UserExtra ue =findByUserId(user_old.getId());
            ue.setEmail(email);
            ued.save(ue);
        }
        if (StringUtils.hasText(user.getPwd())) {
            user_old.setPwd(user.getPwd());
        }
        ud.save(user_old);
    }

    /**
     * 修改用户的额外信息
     *
     * @param userId 用户id
     * @param userExtra 用户的额外信息
     */
    @Override
    public void editUserExtra(Long userId, UserExtra userExtra) {
        UserExtra userExtra_old = findByUserId(userId);
        if (StringUtils.hasText(userExtra.getPersonNote())) {
            userExtra_old.setPersonNote(userExtra.getPersonNote());
        }
        if (null != userExtra.getAvatarCode()) {
            userExtra_old.setAvatarCode(userExtra.getAvatarCode());
        }
        ued.save(userExtra_old);
    }


    /**
     * 通过id获取用户
     *
     * @param id 用户id
     * @return 用户表
     */
    @Override
    public User findById(Long id) {
        User user = ud.findOne(id);
        if (user == null) {
            throw new ChatRuntimeException("用户不存在");
        }
        return user;
    }

    /**
     * 通过用户id获取其详细信息
     *
     * @param id 用户id
     * @return 用户额外信息
     */
    @Override
    public UserExtra findByUserId(Long id) {
        return ued.findByUserId(id);
    }

    /**
     * 检验此邮箱是否可以使用
     *
     * @param email 邮箱
     */
    @Override
    public void checkEmail(String email) {
        User user_tmp = ud.findByEmail(email);
        if (user_tmp != null) {
            throw new ChatRuntimeException("邮箱已被使用");
        }
    }

    /**
     * 检验此手机号是否可以使用
     *
     * @param cellPhone 手机
     */
    @Override
    public void checkCellPhone(String cellPhone) {
        User user_tmp = ud.findByCellPhone(cellPhone);
        if (user_tmp != null) {
            throw new ChatRuntimeException("手机号已被使用");
        }
    }

    /**
     * 检验此名字是否可以使用
     *
     * @param name 用户名
     */
    @Override
    public void checkName(String name) {
        User user_tmp = ud.findByName(name);
        if (user_tmp != null) {
            throw new ChatRuntimeException("用户名已被使用");
        }
    }
}
