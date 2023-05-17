package com.pro.WOLmgr.repository;

import com.pro.WOLmgr.entity.UserEntity;
import com.pro.WOLmgr.util.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String userName);
    UserEntity findByUserId(String id);
    // userId 컬럼값이 존재하는지 확인 존재하면 참 없으면 거짓
    boolean existsByUserId(String userId);
    // email 컬럼값이 존재하는지 확인 존재하면 참 없으면 거짓
    boolean existsByEmail(String email);
    boolean existsByUsername(String userName);

}
