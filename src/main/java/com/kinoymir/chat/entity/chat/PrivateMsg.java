package com.kinoymir.chat.entity.chat;

import com.kinoymir.chat.entity.BaseEntity;
import com.kinoymir.chat.entity.enums.MsgType;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
/**
 * 客户端发送给服务端的私人消息（点对点）
 */
public class PrivateMsg extends BaseEntity{

    private Long sendId;
    @NotBlank(message = "接受者不可为空")
    private Long receiverId;

    @NotBlank(message = "消息内容不可为空")
    @Length(max=255,message = "消息过长")
    private String msg;

    @Enumerated(EnumType.STRING)
    private MsgType msgType=MsgType.text;

}
