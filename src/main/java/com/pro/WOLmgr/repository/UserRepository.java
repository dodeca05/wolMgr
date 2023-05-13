package com.pro.WOLmgr.repository;

import com.pro.WOLmgr.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String userName);
    UserEntity findByUserId(String id);

    // userId 컬럼값이 존재하는지 확인 존재하면 참 없으면 거짓
    boolean existsByUserId(String userId);

    // email 컬럼값이 존재하는지 확인 존재하면 참 없으면 거짓
    boolean existsByEmail(String email);


}
