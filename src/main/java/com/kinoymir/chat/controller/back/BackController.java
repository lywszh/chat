package com.kinoymir.chat.controller.back;

import com.kinoymir.chat.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台管理
 */
@Controller
@RequestMapping("/back")
public class BackController extends BaseController {


    /**
     * 后台模板页面
     *
     * @return 页面
     */
    @GetMapping("/template")
    public String template() {
        return "template";
    }

    /**
     * 后台的登录页面
     *
     * @return
     */
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "登录");
        return "login";
    }

    /**
     * 后台首页
     *
     * @return 页面
     */
    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("title", "首页");
        model.addAttribute("page", "index");
        return "index";
    }

    /**
     * 用户管理页面
     *
     * @param model
     * @return
     */
    @GetMapping("/userManager")
    public String userManager(Model model) {
        model.addAttribute("title", "用户管理");
        model.addAttribute("page", "userManager");
        return "userManager";
    }
}
