package com.kinoymir.chat.entity.critical;

import com.kinoymir.chat.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

/**
 * 倒计时（倒计日）
 */
@Entity(name = "down_time")
@Data
@EqualsAndHashCode(callSuper = true)
public class DownTime extends BaseEntity {

    /**
     * 倒计时的名称
     */
    private String name;

    /**
     * 倒计时的描述
     */
    private String description;

    /**
     * 倒计的时间长度（毫秒数）
     */
    private Long dt;

    /**
     * 开放状态
     */
    private boolean openStatus = false;

    /**
     * 如果私有时，关联的用户id
     */
    private Long userId;
}
