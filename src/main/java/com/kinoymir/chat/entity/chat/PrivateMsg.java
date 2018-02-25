package com.kinoymir.chat.entity.chat;

import lombok.Data;

@Data
public class PrivateMsg {

    private Long receiverId;

    private String msg;

}
