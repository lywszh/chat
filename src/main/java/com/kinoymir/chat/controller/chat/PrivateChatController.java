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
@RequestMapping("/chat/private")
public class PrivateChatController extends BaseController {

    @Autowired
    private UserService us;

    @Autowired
    private SimpMessagingTemplate smt;



    @MessageMapping("/str")
    public void sendMsg(PrivateMsg msg) {
        System.out.println(msg);
        UserExtra ue = this.us.findByUserId(msg.getReceiverId());
        smt.convertAndSendToUser(msg.getReceiverId().toString(),"/chat-subscibe/private",msg);


    }
}
