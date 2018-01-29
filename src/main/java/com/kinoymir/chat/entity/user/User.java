package com.kinoymir.chat.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kinoymir.chat.config.TimeConverter;
import com.kinoymir.chat.entity.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity(name = "user_user")
@Data
public class User extends BaseEntity {


    @JsonIgnore
    @NotBlank(message = "密码不能为空")
    private String pwd;

    @NotBlank(message = "邮箱号不能为空")
    @Email(message = "邮箱格式错误")
    private String email;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^(1[34578]\\d{9})$", message = "手机格式错误")
    private String cellPhone;


    @NotBlank(message = "用户名不能为空")
    @Length(max=8,message = "用户名过长")
    private String name;

    /**
     * 注册时间
     */
    @Convert(converter = TimeConverter.class)
    @JsonIgnore
    private LocalDateTime registerTime;

    /**
     * 最后一次登录时间
     */
    @Convert(converter = TimeConverter.class)
    @JsonIgnore
    private LocalDateTime lastLoginTime;
}
