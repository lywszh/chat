package com.kinoymir.chat.config.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.util.StringUtils;

import com.kinoymir.chat.entity.user.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MyShiroToken extends UsernamePasswordToken {
    /**
     * 
     */
    private static final long serialVersionUID = -8274779401886849654L;

    private String email;

    private String cellPhone;

    private String pwd;


    public MyShiroToken() {
    }

    public MyShiroToken(User user) {
        super(StringUtils.hasText(user.getCellPhone()) ? user.getCellPhone() : user.getEmail(), user.getPwd());
        this.email = user.getEmail();
        this.cellPhone = user.getCellPhone();
        this.pwd = user.getPwd();
    }

}
