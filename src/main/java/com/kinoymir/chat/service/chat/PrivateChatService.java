package com.kinoymir.chat.service.chat;

import com.kinoymir.chat.entity.chat.PrivateMsg;

public interface PrivateChatService {
    /**
     * 发送私人消息（发送并且存入数据库）
     *
     * @param sendId     发送者
     * @param msg        消息内容
     */
    void sendMsg(Long sendId, PrivateMsg msg);
}
