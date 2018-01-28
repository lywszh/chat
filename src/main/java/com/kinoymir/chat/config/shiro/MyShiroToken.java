package com.kinoymir.chat.config.shiro;

import com.kinoymir.chat.entity.user.User;
import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.util.StringUtils;

@Data
public class MyShiroToken extends UsernamePasswordToken {
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
