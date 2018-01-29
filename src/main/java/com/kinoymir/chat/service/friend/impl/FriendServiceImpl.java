package com.kinoymir.chat.service.friend.impl;

import com.kinoymir.chat.entity.friend.FriendApply;
import com.kinoymir.chat.entity.friend.FriendShip;
import com.kinoymir.chat.service.friend.FriendService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class FriendServiceImpl implements FriendService {
    /**
     * 发送好友申请，创建好友申请
     *
     * @param sendId
     * @param receiverId
     * @return
     */
    @Override
    public FriendApply createApply(Long sendId, Long receiverId) {
        return null;
    }

    /**
     * 查找好友申请
     *
     * @param id
     * @return
     */
    @Override
    public FriendApply findApplyById(Long id) {
        return null;
    }

    /**
     * 确认好友申请
     *
     * @param userId
     * @param applyId
     */
    @Override
    public void affirmApply(Long userId, Long applyId) {

    }

    /**
     * 拒绝 忽略好友申请
     *
     * @param userId
     * @param applyId
     */
    @Override
    public void refuseApply(Long userId, Long applyId) {

    }

    /**
     * 删除好友申请
     *
     * @param id
     * @return
     */
    @Override
    public void delById(Long id) {

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
