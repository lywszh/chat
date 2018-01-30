package com.kinoymir.chat.dao.friend;

import com.kinoymir.chat.dao.BaseDao;
import com.kinoymir.chat.entity.friend.FriendApply;

import java.util.List;

public interface FriendApplyDao extends BaseDao<FriendApply,Long> {
    FriendApply findBySendIdAndReceiverId(Long sendId,Long receiverId);
    List<FriendApply> findByReceiverId(Long receiverId);
}
