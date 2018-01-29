package com.kinoymir.chat.entity.friend;

import com.kinoymir.chat.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;


/**
 * 好友申请表
 */
@Data
@Entity
@AllArgsConstructor
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
