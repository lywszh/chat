package com.kinoymir.chat.controller.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kinoymir.chat.common.JsonResult;
import com.kinoymir.chat.controller.BaseController;
import com.kinoymir.chat.entity.user.UserExtra;
import com.kinoymir.chat.service.user.UserService;

/**
 * 后台管理
 */
@Controller
@RequestMapping("/back")
public class BackController extends BaseController {

    private final UserService us;

    @Autowired
    public BackController(UserService us) {
        this.us = us;
    }

    /**
     * 后台模板页面
     *
     * @return 页面
     */
    @GetMapping("/template")
    public String template() {
        return "common/backTemplate";
    }

    /**
     * 后台的登录页面
     *
     * @return 页面
     */
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "登录");
        return "back/login";
    }

    /**
     * 后台首页
     *@param model 页面需要的数据封装
     * @return 页面
     */
    @GetMapping("/index")
    public String index(Model model) {
        if(getUserIdFromCache()==null){
            return "redirect:login";
        }
        model.addAttribute("title", "首页");
        model.addAttribute("page", "index");
        return "back/index";
    }

    /**
     * 用户管理页面
     *
     * @param model 页面需要的数据封装
     * @return 页面
     */
    @GetMapping("/userManager")
    public String userManager(Model model) {
        model.addAttribute("title", "用户管理");
        model.addAttribute("page", "userManager");
        return "back/userManager";
    }

    /**
     * 聊天测试页面
     * @param model 页面需要的数据封装
     * @return 页面
     */
    @GetMapping("/chatTest")
    public String chatTest(Model model){
        model.addAttribute("title", "聊天测试");
        model.addAttribute("page", "chatTest");
        return "back/chatTest";
    }

    /**
     * 分页查询用户数据
     * @param name 用户名
     * @param cellPhone 用户手机号
     * @param email 邮箱
     * @param page 第几页
     * @param size 每一页的数据量
     * @return 封装的数据
     */
    @PostMapping("/listUser")
    @ResponseBody
    public JsonResult listUserByPage(String name, String cellPhone, String email, int page, int size) {
        Pageable pageable = new PageRequest(page-1, size, new Sort(Sort.Direction.ASC, "id"));
        Page<UserExtra> pue = us.listUser(name, cellPhone, email, pageable);
        return new JsonResult().success().dataObj(pue);
    }
}
