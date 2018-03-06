package com.kinoymir.chat.entity.systemMsg;

import com.kinoymir.chat.entity.BaseEntity;
import com.kinoymir.chat.entity.enums.MsgType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * 系统消息表
 */
@EqualsAndHashCode(callSuper = true)
@Entity(name = "msg_system")
@Data
public class SystemMsg extends BaseEntity {

    /**
     * 接收者Id
     */
    private Long receiverId;

    /**
     * 消息的类别
     */
    @Enumerated(EnumType.STRING)
    private MsgType msgType=MsgType.text;

    /**
     * 消息的主要内容
     */
    private String content;
}
