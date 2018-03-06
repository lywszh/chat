package com.kinoymir.chat.entity.critical;

import com.kinoymir.chat.config.system.TimeConverter;
import com.kinoymir.chat.entity.BaseEntity;
import com.kinoymir.chat.entity.enums.RemindTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

/**
 * 时间点
 */
@Entity(name = "time_point")
@Data
@EqualsAndHashCode(callSuper = true)
public class TimePoint extends BaseEntity {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 时间点名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 输入时间的时间戳
     */
    private Long timeStamp;

    /**
     * 输入时间的时间点
     */
    @Convert(converter = TimeConverter.class)
    private LocalDateTime tp;

    /**
     * 倒计的提醒时间（默认为三天）
     */
    @Enumerated(EnumType.STRING)
    private RemindTime remindTime=RemindTime.threeDay;

}
