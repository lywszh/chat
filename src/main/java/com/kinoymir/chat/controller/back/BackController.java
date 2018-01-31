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
     * @return 页面
     */
    @GetMapping("/template")
    public String template(){
        return "template";
    }

    /**
     * 后台首页
     * @return 页面
     */
    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("title","首页");
        return "index";
    }
}
