package com.kinoymir.chat.controller.chat;

import com.kinoymir.chat.controller.BaseController;
import com.kinoymir.chat.entity.chat.PrivateMsg;
import com.kinoymir.chat.entity.user.UserExtra;
import com.kinoymir.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/privateChat")
public class PrivateChatController extends BaseController {

    private final UserService us;

    private final SimpMessagingTemplate smt;

    @Autowired
    public PrivateChatController(UserService us, SimpMessagingTemplate smt) {
        this.us = us;
        this.smt = smt;
    }

    @MessageMapping("/str")
    public void sendMsg(PrivateMsg msg) {
        System.out.println(msg);
        UserExtra ue = this.us.findByUserId(msg.getReceiverId());
        smt.convertAndSendToUser(msg.getReceiverId().toString(),"/queue",msg);


    }
}
