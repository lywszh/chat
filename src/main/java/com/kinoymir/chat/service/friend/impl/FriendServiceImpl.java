package com.kinoymir.chat.service.friend.impl;

import com.kinoymir.chat.common.ChatRuntimeException;
import com.kinoymir.chat.dao.friend.FriendApplyDao;
import com.kinoymir.chat.dao.friend.FriendShipDao;
import com.kinoymir.chat.entity.friend.FriendApply;
import com.kinoymir.chat.entity.friend.FriendShip;
import com.kinoymir.chat.service.friend.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendApplyDao fad;

    @Autowired
    private FriendShipDao fsd;


    /**
     * 发送好友申请，创建好友申请
     *
     * @param sendId
     * @param receiverId
     * @return
     */
    @Override
    public FriendApply createApply(Long sendId, Long receiverId) {
        FriendApply fa = fad.findBySendIdAndReceiverId(sendId, receiverId);
        if (fa != null) {
            return fa;
        }
        fa = new FriendApply(sendId, receiverId);
        return fad.save(fa);
    }

    /**
     * 查找好友申请
     *
     * @param id
     * @return
     */
    @Override
    public FriendApply findApplyById(Long id) {
        FriendApply fa = fad.findOne(id);
        if (fa == null) {
            throw new ChatRuntimeException("好友申请不存在");
        }
        return fa;
    }

    /**
     * 确认是否为本人操作好友申请
     *
     * @param userId
     * @param applyId
     */
    @Override
    public void checkApplyLegal(Long userId, Long applyId) {
        FriendApply fa = findApplyById(applyId);
        if(fa.getReceiverId()!=userId){
            throw new ChatRuntimeException("非本人操作");
        }
    }

    /**
     * 确认好友申请
     *
     * @param userId
     * @param applyId
     */
    @Override
    public void affirmApply(Long userId, Long applyId) {
        checkApplyLegal(userId,applyId);

    }

    /**
     * 拒绝 忽略好友申请
     *
     * @param userId
     * @param applyId
     */
    @Override
    public void refuseApply(Long userId, Long applyId) {
        checkApplyLegal(userId,applyId);
        delById(applyId);
    }

    /**
     * 删除好友申请
     *
     * @param id
     * @return
     */
    @Override
    public void delById(Long id) {
        FriendApply fa = findApplyById(id);
        fad.delete(id);
    }

    /**
     * 通过接收者获取所有好友申请
     *
     * @return
     */
    @Override
    public List<FriendApply> findByReceive(Long userId) {
        return null;
    }

    /**
     * 通过用户获取其好友列表（未删除的）（排序按照，亲密好友，好友名称顺序）
     *
     * @param userId
     * @return
     */
    @Override
    public List<FriendShip> findByMyself(Long userId) {
        return null;
    }

    /**
     * 创建好友关系
     *
     * @param myselfId
     * @param friendId
     * @return
     */
    @Override
    public FriendShip createShip(Long myselfId, Long friendId) {
        return null;
    }

    /**
     * 取消好友关系（只是逻辑删除，保留备注和亲密状态，必要时恢复）
     *
     * @param cancelId
     * @param myselfId
     */
    @Override
    public void cancelShip(Long cancelId, Long myselfId) {

    }

    /**
     * 获取好友关系记录
     *
     * @param id
     * @return
     */
    @Override
    public FriendShip findShipById(Long id) {
        return null;
    }

    /**
     * 删除好友关系（工具方法使用）
     *
     * @param id
     */
    @Override
    public void delShip(Long id) {

    }

    /**
     * 恢复好友关系
     *
     * @param id
     */
    @Override
    public void recoverShip(Long id) {

    }

    /**
     * 修改好友备注
     *
     * @param id
     * @param remark
     */
    @Override
    public void editShipRemark(Long id, String remark) {

    }

    /**
     * 修改好友亲密状态
     *
     * @param id
     */
    @Override
    public void editShipCloseFriend(Long id) {

    }
}
