package com.kinoymir.chat.entity.chat;

import lombok.Data;

/**
 * 从服务端发给客户端的消息
 */
@Data
public class PrivateSendMsg {

    private String msg;

    private String sendName;
}
