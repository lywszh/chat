package com.kinoymir.chat.controller.chat;

import com.kinoymir.chat.controller.BaseController;
import com.kinoymir.chat.entity.chat.PrivateMsg;
import com.kinoymir.chat.service.chat.PrivateChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/chat")
public class ChatController extends BaseController {

    private final PrivateChatService pcs;


    @Autowired
    public ChatController( PrivateChatService pcs) {
        this.pcs=pcs;
    }

    /**
     * 发送私信
     * @param msg
     */
    @MessageMapping("/str")
    public void sendMsg(Principal userId, @Validated @ModelAttribute PrivateMsg msg) {
        System.out.println(msg);
        pcs.sendMsg(new Long(userId.getName()),msg);
    }


    /*
    @GetMapping("/info")
    public ConcurrentMap getWebSocketInfo(){
        ConcurrentMap<String, String> ids = ssr.getAllSessionIds();
        return ids;
    }
    */
}
