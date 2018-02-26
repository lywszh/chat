package com.kinoymir.chat.controller.chat;

import com.kinoymir.chat.controller.BaseController;
import com.kinoymir.chat.entity.chat.PrivateMsg;
import com.kinoymir.chat.entity.user.UserExtra;
import com.kinoymir.chat.service.chat.PrivateChatService;
import com.kinoymir.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/privateChat")
public class PrivateChatController extends BaseController {

    private final PrivateChatService pcs;

    @Autowired
    public PrivateChatController( PrivateChatService pcs) {
        this.pcs=pcs;
    }

    @MessageMapping("/str")
    public void sendMsg(PrivateMsg msg) {
        pcs.sendMsg(getUserIdFromCache(),msg);
    }
}
