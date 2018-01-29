package com.kinoymir.chat.dao.user;

import com.kinoymir.chat.dao.BaseDao;
import com.kinoymir.chat.entity.user.UserExtra;

public interface UserExtraDao extends BaseDao<UserExtra,Long> {
    UserExtra findByUserId(Long userId);
}
