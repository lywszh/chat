package com.kinoymir.chat.entity.friend;

import com.kinoymir.chat.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
/**
 * 好友申请表
 */
public class FriendApply extends BaseEntity{

    /**
     * 发送人Id
     */
    private Long sendId;
    /**
     * 接收人Id
     */
    private Long receiverId;
}
