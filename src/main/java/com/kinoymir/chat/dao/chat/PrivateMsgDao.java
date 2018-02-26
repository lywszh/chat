package com.kinoymir.chat.dao.chat;

import com.kinoymir.chat.dao.BaseDao;
import com.kinoymir.chat.entity.chat.PrivateMsg;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateMsgDao extends BaseDao<PrivateMsg,Long>{
}
