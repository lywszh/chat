package com.kinoymir.chat.entity.friend;

import com.kinoymir.chat.entity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 好友关系表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FriendShip extends BaseEntity {
    /**
     * 好友关系所有者Id
     */
      private Long myselfId;

    /**
     * 好友关系中好友Id
     */
     private Long friendId;

    /**
     * 是否为亲密好友
     */
    @Column(name = "is_closeFriend")
    private boolean closeFriend;

    /**
     * 好友备注
     */
    private String remark;



}
