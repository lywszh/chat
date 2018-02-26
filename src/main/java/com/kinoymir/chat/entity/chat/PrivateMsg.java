package com.kinoymir.chat.entity.chat;

import com.kinoymir.chat.entity.BaseEntity;
import com.kinoymir.chat.entity.enums.MsgType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "chat_private")
/**
 * 客户端发送给服务端的私人消息（点对点）
 */
public class PrivateMsg extends BaseEntity{

    private Long sendId;

    private Long receiverId;

    private String msg;

    @Enumerated(EnumType.STRING)
    private MsgType msgType=MsgType.text;

}
