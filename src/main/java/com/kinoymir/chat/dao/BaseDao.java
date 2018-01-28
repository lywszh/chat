package com.kinoymir.chat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface BaseDao<T, ID extends Serializable> extends JpaRepository<T, ID> {
}
