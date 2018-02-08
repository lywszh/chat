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
import java.util.stream.Collectors;

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
     * @param sendId 发送者Id
     * @param receiverId 接收者Id
     * @return 创建的申请表
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
     * @param id 申请表id
     * @return 查找到的申请表
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
     * @param userId 操作者Id
     * @param applyId 申请表Id
     */
    @Override
    public void checkApplyLegal(Long userId, Long applyId) {
        FriendApply fa = findApplyById(applyId);
        if (fa.getReceiverId().equals(userId)) {
            throw new ChatRuntimeException("非本人操作");
        }
    }

    /**
     * 确认好友申请
     *
     * @param userId 操作者Id
     * @param applyId 申请表Id
     */
    @Override
    public void affirmApply(Long userId, Long applyId) {
        checkApplyLegal(userId, applyId);
        FriendApply fa =findApplyById(applyId);
        createShip(userId,fa.getSendId());
    }

    /**
     * 拒绝 忽略好友申请
     *
     * @param userId 操作者Id
     * @param applyId 申请表Id
     */
    @Override
    public void refuseApply(Long userId, Long applyId) {
        checkApplyLegal(userId, applyId);
        delApplyById(applyId);
    }

    /**
     * 删除好友申请
     *
     * @param id 申请表Id
     */
    @Override
    public void delApplyById(Long id) {
        FriendApply fa = findApplyById(id);
        fad.delete(fa);
    }

    /**
     * 通过接收者获取所有好友申请
     *
     * @return 好友申请集合
     */
    @Override
    public List<FriendApply> findByReceive(Long userId) {
        return fad.findByReceiverId(userId);
    }

    /**
     * 通过用户获取其好友列表（未删除的）（排序按照，亲密好友，好友名称顺序）
     *
     * @param userId 操作者Id
     * @return 好友列表集合
     */
    @Override
    public List<FriendShip> findByMyself(Long userId) {
        List<FriendShip> fss = fsd.findByMyselfId(userId);
        fss = fss.parallelStream().filter(t -> !t.isDeleted()).collect(Collectors.toList());
        return fss;
    }

    /**
     * 创建好友关系（同时创建两者，如果有被删除的则复原）
     *
     * @param myselfId 操作者Id
     * @param friendId 好友Id
     * @return 创建的好友关系中的本人作为关系所有者的记录
     */
    @Override
    public FriendShip createShip(Long myselfId, Long friendId) {
        FriendShip fs = fsd.findByMyselfIdAndFriendId(myselfId, friendId);
        FriendShip fs_relate  =fsd.findByMyselfIdAndFriendId(friendId,myselfId);
        if(fs!=null&&fs.isDeleted()&&fs_relate!=null&&fs_relate.isDeleted()){
            recoverShip(fs);
            recoverShip(fs_relate);
            return fs;
        }
        fs=new FriendShip(myselfId,friendId,false,"");
        fs_relate=new FriendShip(friendId,myselfId,false,"");
        fsd.save(fs);
        fsd.save(fs_relate);
        return fs;
    }

    /**
     * 取消好友关系（只是逻辑删除，保留备注和亲密状态，必要时恢复）
     *
     * @param cancelId 需要取消的好友关系表Id
     * @param myselfId 操作者Id
     */
    @Override
    public void cancelShip(Long cancelId, Long myselfId) {
        FriendShip fs = findShipById(cancelId);
        if (fs.getMyselfId().equals(myselfId)) {
            throw new ChatRuntimeException("非本人操作");
        }
        FriendShip fs_relate = fsd.findByMyselfIdAndFriendId(fs.getFriendId(), myselfId);
        delShip(fs);
        delShip(fs_relate);
    }

    /**
     * 获取好友关系记录
     *
     * @param id 好友表Id
     * @return  查找到的好友记录
     */
    @Override
    public FriendShip findShipById(Long id) {
        FriendShip fs = fsd.findOne(id);
        if (fs == null) {
            throw new ChatRuntimeException("好友记录不存在");
        }
        if (fs.isDeleted()) {
            throw new ChatRuntimeException("好友关系不存在");
        }
        return fs;
    }

    /**
     * 删除好友关系（工具方法使用）
     *
     * @param fs 好友表
     */
    @Override
    public void delShip(FriendShip fs) {
        if (fs == null) {
            throw new ChatRuntimeException("记录为空");
        }
        fs.setDeleted(true);
        fsd.save(fs);
    }

    /**
     * 恢复好友关系
     *
     * @param fs 好友表
     */
    @Override
    public void recoverShip(FriendShip fs) {
        if (fs == null) {
            throw new ChatRuntimeException("记录为空");
        }
        fs.setDeleted(false);
        fsd.save(fs);
    }

    /**
     * 修改好友备注
     *
     * @param id 好友表Id
     * @param remark 修改后的备注
     */
    @Override
    public void editShipRemark(Long id, String remark) {
        FriendShip fs = findShipById(id);
        fs.setRemark(remark);
        fsd.save(fs);
    }

    /**
     * 修改好友亲密状态
     *
     * @param id 好友表Id
     */
    @Override
    public void editShipCloseFriend(Long id) {
        FriendShip fs = findShipById(id);
        fs.setCloseFriend(!fs.isCloseFriend());
        fsd.save(fs);
    }
}
