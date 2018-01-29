package com.kinoymir.chat.dao.user;

import com.kinoymir.chat.dao.BaseDao;
import com.kinoymir.chat.entity.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseDao<User,Long> {
    User findByEmail(String email);
    User findByCellPhone(String cellPhone);
    User findByName(String name);
}
