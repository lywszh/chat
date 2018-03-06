package com.kinoymir.chat.entity.critical;

import com.kinoymir.chat.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

/**
 * 关联表 ，倒计时与时间点
 */
@EqualsAndHashCode(callSuper = true)
@Entity(name = "r_down_point")
@Data
public class RDownTimePoint extends BaseEntity{


    private Long downTimeId;

    private Long TimePointId;
}
