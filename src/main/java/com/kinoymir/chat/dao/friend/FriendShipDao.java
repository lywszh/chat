package com.kinoymir.chat.dao.friend;

import com.kinoymir.chat.dao.BaseDao;
import com.kinoymir.chat.entity.friend.FriendShip;

import java.util.List;

public interface FriendShipDao extends BaseDao<FriendShip,Long> {
    FriendShip findByMyselfIdAndFriendId(Long myselfId,Long friendId);
    List<FriendShip> findByMyselfId(Long myselfId);
}
