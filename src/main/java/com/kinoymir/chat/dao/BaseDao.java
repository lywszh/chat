package com.kinoymir.chat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BaseDao<T, ID extends Serializable> extends JpaRepository<T, ID> {
    /**
     * 获取所有未被删除的
     * @return 所有对象
     */
    List<T> findByDeletedIsFalse();
}
