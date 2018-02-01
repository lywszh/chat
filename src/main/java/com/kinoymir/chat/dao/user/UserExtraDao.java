package com.kinoymir.chat.dao.user;

import com.kinoymir.chat.dao.BaseDao;
import com.kinoymir.chat.entity.user.UserExtra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExtraDao extends BaseDao<UserExtra,Long> {
    UserExtra findByUserId(Long userId);

    @Query("select  ue from user_extra ue ")
    Page<UserExtra> searchAll(Pageable pageable);
    Page<UserExtra> findByName(String name,Pageable pageable);
    Page<UserExtra> findByCellPhone(String cellPhone,Pageable pageable);
    Page<UserExtra> findByEmail(String email,Pageable pageable);
    Page<UserExtra> findByNameAndCellPhone(String name,String cellPhone,Pageable pageable);
    Page<UserExtra> findByNameAndEmail(String name,String cellPhone,Pageable pageable);
    Page<UserExtra> findByCellPhoneAndEmail(String cellphone,String email,Pageable pageable);
    Page<UserExtra> findByNameAndCellPhoneAndEmail(String name,String cellphone,String email,Pageable pageable);
}
