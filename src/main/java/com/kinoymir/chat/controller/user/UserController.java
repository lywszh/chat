package com.kinoymir.chat.controller.user;

import com.kinoymir.chat.common.ChatRuntimeException;
import com.kinoymir.chat.common.JsonResult;
import com.kinoymir.chat.controller.BaseController;
import com.kinoymir.chat.entity.user.User;
import com.kinoymir.chat.entity.user.UserExtra;
import com.kinoymir.chat.service.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    private final UserService us;

    @Autowired
    public UserController(UserService us) {
        this.us = us;
    }

    /**
     * 用户尚且没有登录，无法访问页面
     */
    @GetMapping("/notLogin")
    public void notLogin() {
        throw new ChatRuntimeException("尚未登录");
    }

    /**
     * 用户没有权限访问
     */
    @GetMapping("/403")
    public void noPermission() {
        throw new ChatRuntimeException("没有权限");
    }

    /**
     * 注册的用户 同时生成用户登录表和用户信息表
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public JsonResult register(@Validated @ModelAttribute User user) {
        User user_re=us.register(user);
        setUserIntoCache(user_re,false);
        return new JsonResult().success();
    }

    /**
     * 用户登录，手机号和邮箱必须有一个不为空
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public JsonResult login(@ModelAttribute User user,boolean rememberMe) {
        setUserIntoCache(user,rememberMe);
        return new JsonResult().success();
    }

    /**
     * 用户登出，如果有额外操作，在这里实现。
     *
     * @return
     */
    @PostMapping("/logout")
    public JsonResult logout() {
        SecurityUtils.getSubject().logout();
        return new JsonResult().success();
    }

    /**
     * 修改用户的登录信息，仅可以修改邮箱和密码，手机号不允许修改
     *
     * @param user
     * @return
     */
    @PostMapping("/edit/user")
    public JsonResult editUser(@ModelAttribute User user) {
        Long userId=getUserIdFromCache();
        us.editUser(userId,user);
        return new JsonResult().success();
    }

    /**
     * 修改用户的额外信息，诸如 头像 个性签名
     *
     * @param userExtra
     * @return
     */
    @PostMapping("/edit/userExtra")
    public JsonResult editUserExtra(@ModelAttribute UserExtra userExtra) {
        Long userId=getUserIdFromCache();
        us.editUserExtra(userId,userExtra);
        return new JsonResult().success();
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/info")
    public JsonResult info(){
        UserExtra ue =us.findByUserId(getUserIdFromCache());
        return new JsonResult().success().dataObj(ue);
    }

}
