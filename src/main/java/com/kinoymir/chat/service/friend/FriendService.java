package com.kinoymir.chat.service.friend;

import com.kinoymir.chat.entity.friend.FriendApply;
import com.kinoymir.chat.entity.friend.FriendShip;

import java.util.List;

public interface FriendService {
    /**
     * 发送好友申请，创建好友申请
     *
     * @param sendId
     * @param receiverId
     * @return
     */
    FriendApply createApply(Long sendId, Long receiverId);

    /**
     * 查找好友申请
     *
     * @param id
     * @return
     */
    FriendApply findApplyById(Long id);

    /**
     * 确认是否为本人操作好友申请
     *
     * @param userId
     * @param applyId
     */
    void checkApplyLegal(Long userId, Long applyId);

    /**
     * 确认好友申请
     *
     * @param userId
     * @param applyId
     */
    void affirmApply(Long userId, Long applyId);

    /**
     * 拒绝 忽略好友申请
     *
     * @param userId
     * @param applyId
     */
    void refuseApply(Long userId, Long applyId);

    /**
     * 删除好友申请
     *
     * @param id
     * @return
     */
    void delById(Long id);

    /**
     * 通过接收者获取所有好友申请
     *
     * @return
     */
    List<FriendApply> findByReceive(Long userId);

    /**
     * 通过用户获取其好友列表（未删除的）（排序按照，亲密好友，好友名称顺序）
     *
     * @param userId
     * @return
     */
    List<FriendShip> findByMyself(Long userId);

    /**
     * 创建好友关系
     *
     * @param myselfId
     * @param friendId
     * @return
     */
    FriendShip createShip(Long myselfId, Long friendId);

    /**
     * 取消好友关系（只是逻辑删除，保留备注和亲密状态，必要时恢复）
     *
     * @param cancelId
     * @param myselfId
     */
    void cancelShip(Long cancelId, Long myselfId);

    /**
     * 获取好友关系记录
     *
     * @param id
     * @return
     */

    FriendShip findShipById(Long id);

    /**
     * 删除好友关系（工具方法使用）
     *
     * @param id
     */
    void delShip(Long id);

    /**
     * 恢复好友关系
     *
     * @param id
     */
    void recoverShip(Long id);

    /**
     * 修改好友备注
     *
     * @param id
     * @param remark
     */
    void editShipRemark(Long id, String remark);

    /**
     * 修改好友亲密状态
     *
     * @param id
     */
    void editShipCloseFriend(Long id);
}
