package com.kinoymir.chat.controller.friend;

import com.kinoymir.chat.common.JsonResult;
import com.kinoymir.chat.controller.BaseController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/friend")
public class FriendController extends BaseController {
    /**
     * 新建好友申请
     *
     * @param userId
     * @return
     */
    @RequestMapping("/sendApply")
    public JsonResult sendApply(Long userId) {
        return new JsonResult().success();
    }

    /**
     * 获取登录用户所有好友申请
     *
     * @return
     */
    @RequestMapping("/listApply")
    public JsonResult applyList() {
        return new JsonResult().success();
    }

    /**
     * 确认好友申请（需要判断确认者是否为接收者）
     *
     * @param id
     * @return
     */
    @RequestMapping("/affirmApply")
    public JsonResult affirmApply(Long id) {
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
        return new JsonResult().success();
    }

    /**
     * 查询好友列表
     *
     * @return
     */
    @RequestMapping("/shipList")
    public JsonResult shipList() {
        return new JsonResult().success();
    }

    /**
     * 删除好友关系（同步删除对方列表）
     *
     * @return
     */
    @RequestMapping("/shipDel")
    public JsonResult delShip() {
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
        return new JsonResult().success();
    }

}
