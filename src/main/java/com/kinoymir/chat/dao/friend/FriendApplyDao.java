package com.kinoymir.chat.dao.friend;

import com.kinoymir.chat.dao.BaseDao;
import com.kinoymir.chat.entity.friend.FriendApply;

public interface FriendApplyDao extends BaseDao<FriendApply,Long> {
    FriendApply findBySendIdAndReceiverId(Long sendId,Long receiverId);
}
