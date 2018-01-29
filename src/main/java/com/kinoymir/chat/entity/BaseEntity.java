package com.kinoymir.chat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kinoymir.chat.config.TimeConverter;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础实体类
 *
 * @author kino
 */
@MappedSuperclass
@EntityListeners(BaseEntityListener.class)
@Data
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    @JsonIgnore
    private Long version;

    @Column(name = "deleted")
    @JsonIgnore
    private boolean is_deleted;

    @Convert(converter = TimeConverter.class)
    @JsonIgnore
    private LocalDateTime createTime = LocalDateTime.now();

    @Convert(converter = TimeConverter.class)
    @JsonIgnore
    private LocalDateTime lastModifyTime = LocalDateTime.now();

    public BaseEntity() {
    }

    public String getCreateTime() {
        return createTime.toString();
    }


    public String getLastModifyTime() {
        return lastModifyTime.toString();
    }

}
