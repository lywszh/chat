package com.kinoymir.chat.service.chat.impl;

import com.kinoymir.chat.common.ChatRuntimeException;
import com.kinoymir.chat.dao.chat.PrivateMsgDao;
import com.kinoymir.chat.entity.chat.PrivateMsg;
import com.kinoymir.chat.entity.chat.PrivateSendMsg;
import com.kinoymir.chat.entity.user.User;
import com.kinoymir.chat.service.chat.PrivateChatService;
import com.kinoymir.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PrivateChatServiceImpl implements PrivateChatService {

    private final PrivateMsgDao pmd;

    private final UserService us;

    private final SimpMessagingTemplate smt;

    @Autowired
    public PrivateChatServiceImpl(PrivateMsgDao pmd, UserService us,SimpMessagingTemplate smt) {
        this.pmd = pmd;
        this.us=us;
        this.smt = smt;
    }

    /**
     * 绑定的订阅地址
     */
    private static final String DESTINATION="/queue";


    /**
     * 发送私人消息（发送并且存入数据库）
     *
     * @param sendId     发送者
     * @param msg        消息内容
     */
    @Override
    public void sendMsg(Long sendId,PrivateMsg msg) {
        Long receiverId=msg.getReceiverId();
        if(receiverId==null){
            throw new ChatRuntimeException("发送对象为空");
        }
        /*

        后期加一个如果不是好友，就不能发送的限制
        */


        User user =us.findById(sendId);

        /**
         * 验证sendId合法后再存入数据库
         */
        msg.setSendId(sendId);
        pmd.save(msg);

        /**
         * 封装发送给客户端的消息
         */
        PrivateSendMsg psm=new PrivateSendMsg();
        psm.setMsg(msg.getMsg());
        psm.setSendName(user.getName());
        smt.convertAndSendToUser(msg.getReceiverId().toString(),DESTINATION,psm);
    }
}
