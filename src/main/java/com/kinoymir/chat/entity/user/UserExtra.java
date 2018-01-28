package com.kinoymir.chat.entity.user;

import com.kinoymir.chat.entity.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;

@Entity(name = "user_extra")
@Data
public class UserExtra extends BaseEntity {

    /**
     * 关联的用户id
     */
    private Long userId;

    @NotBlank(message = "用户名不能为空")
    @Length(max = 8, message = "用户名过长")
    private String name;

    /**
     * 个人说明，限制50字以内
     */
    @Length(max = 50, message = "个人说明过长")
    private String personNote;


    /**
     * 头像编号
     */
    private Integer avatarCode;
}
