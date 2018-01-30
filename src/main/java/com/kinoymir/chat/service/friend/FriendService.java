package com.kinoymir.chat.service.friend;

import com.kinoymir.chat.entity.friend.FriendApply;
import com.kinoymir.chat.entity.friend.FriendShip;

import java.util.List;

public interface FriendService {
    /**
     * 发送好友申请，创建好友申请
     *
     * @param sendId 发送者Id
     * @param receiverId 接收者Id
     * @return 创建的申请表
     */
    FriendApply createApply(Long sendId, Long receiverId);

    /**
     * 查找好友申请
     *
     * @param id 申请表id
     * @return 查找到的申请表
     */
    FriendApply findApplyById(Long id);

    /**
     * 确认是否为本人操作好友申请
     *
     * @param userId 操作者Id
     * @param applyId 申请表Id
     */
    void checkApplyLegal(Long userId, Long applyId);

    /**
     * 确认好友申请
     *
     * @param userId 操作者Id
     * @param applyId 申请表Id
     */
    void affirmApply(Long userId, Long applyId);

    /**
     * 拒绝 忽略好友申请
     *
     * @param userId 操作者Id
     * @param applyId 申请表Id
     */
    void refuseApply(Long userId, Long applyId);

    /**
     * 删除好友申请
     *
     * @param id 申请表Id
     */
    void delApplyById(Long id);

    /**
     * 通过接收者获取所有好友申请
     *
     * @return 好友申请集合
     */
    List<FriendApply> findByReceive(Long userId);

    /**
     * 通过用户获取其好友列表（未删除的）（排序按照，亲密好友，好友名称顺序）
     *
     * @param userId 操作者Id
     * @return 好友列表集合
     */
    List<FriendShip> findByMyself(Long userId);

    /**
     * 创建好友关系（同时创建两者，如果有被删除的则复原）
     *
     * @param myselfId 操作者Id
     * @param friendId 好友Id
     * @return 创建的好友关系中的本人作为关系所有者的记录
     */
    FriendShip createShip(Long myselfId, Long friendId);

    /**
     * 取消好友关系（只是逻辑删除，保留备注和亲密状态，必要时恢复）
     *
     * @param cancelId 需要取消的好友关系表Id
     * @param myselfId 操作者Id
     */
    void cancelShip(Long cancelId, Long myselfId);

    /**
     * 获取好友关系记录
     *
     * @param id 好友表Id
     * @return 查找到的好友记录
     */
    FriendShip findShipById(Long id);

    /**
     * 删除好友关系（工具方法使用）
     *
     * @param fs 好友表
     */
    void delShip(FriendShip fs);

    /**
     * 恢复好友关系
     *
     * @param fs 好友表
     */
    void recoverShip(FriendShip fs);

    /**
     * 修改好友备注
     *
     * @param id 好友表Id
     * @param remark 修改后的备注
     */
    void editShipRemark(Long id, String remark);

    /**
     * 修改好友亲密状态
     *
     * @param id 好友表Id
     */
    void editShipCloseFriend(Long id);
}
