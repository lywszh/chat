package com.kinoymir.chat.controller.friend;

import com.kinoymir.chat.common.JsonResult;
import com.kinoymir.chat.controller.BaseController;
import com.kinoymir.chat.entity.friend.FriendApply;
import com.kinoymir.chat.entity.friend.FriendShip;
import com.kinoymir.chat.service.friend.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController extends BaseController {

    @Autowired
    private FriendService fs;

    /**
     * 新建好友申请
     *
     * @param userId
     * @return
     */
    @RequestMapping("/sendApply")
    public JsonResult sendApply(Long userId) {
        Long myselfId=getUserIdFromCache();
        fs.createShip(myselfId,userId);
        return new JsonResult().success();
    }

    /**
     * 获取登录用户所有好友申请
     *
     * @return
     */
    @RequestMapping("/listApply")
    public JsonResult applyList() {
        Long myselfId=getUserIdFromCache();
        List<FriendApply> fas=fs.findByReceive(myselfId);
        return new JsonResult().success().dataObj(fas);
    }

    /**
     * 确认好友申请（需要判断确认者是否为接收者）
     *
     * @param id
     * @return
     */
    @RequestMapping("/affirmApply")
    public JsonResult affirmApply(Long id) {
        Long myselfId=getUserIdFromCache();
        fs.affirmApply(myselfId,id);
        return new JsonResult().success();
    }

    /**
     * 拒绝或者忽略 好友申请（直接物理删除）
     *
     * @param id
     * @return
     */
    @RequestMapping("/refuseApply")
    public JsonResult refuseApply(Long id) {
        Long myselfId=getUserIdFromCache();
        fs.refuseApply(myselfId,id);
        return new JsonResult().success();
    }

    /**
     * 查询好友列表
     *
     * @return
     */
    @RequestMapping("/shipList")
    public JsonResult shipList() {
        Long myselfId=getUserIdFromCache();
        List<FriendShip> fss=fs.findByMyself(myselfId);
        return new JsonResult().success().dataObj(fss);
    }

    /**
     * 删除好友关系（同步删除对方列表）
     *
     * @return
     */
    @RequestMapping("/shipDel")
    public JsonResult delShip(Long id) {
        Long myselfId=getUserIdFromCache();
        fs.cancelShip(id,myselfId);
        return new JsonResult().success();
    }

    /**
     * 修改好友的备注
     *
     * @param shipId
     * @param remark
     * @return
     */
    @RequestMapping("/shipRemark")
    public JsonResult remarkShip(Long shipId, String remark) {
        fs.editShipRemark(shipId,remark);
        return new JsonResult().success();
    }

    /**
     * 修改亲密好友状态
     *
     * @param shipId
     * @return
     */
    @RequestMapping("/shipCloseStatus")
    public JsonResult closeStatusShip(Long shipId) {
        fs.editShipCloseFriend(shipId);
        return new JsonResult().success();
    }

}
