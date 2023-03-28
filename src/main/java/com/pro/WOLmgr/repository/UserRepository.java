package com.pro.WOLmgr.repository;

import com.pro.WOLmgr.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Long findByEmail(String email);

}
