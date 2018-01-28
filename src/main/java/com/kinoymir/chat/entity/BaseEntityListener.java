package com.kinoymir.chat.entity;

import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class BaseEntityListener {
    @PreUpdate
    public static void preUpdate(BaseEntity baseEntity) {
        baseEntity.setLastModifyTime(LocalDateTime.now());
    }
}
