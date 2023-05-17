package com.pro.WOLmgr.repository;

import com.pro.WOLmgr.entity.DeviceAuthEntity;
import com.pro.WOLmgr.entity.DeviceAuthId;
import com.pro.WOLmgr.entity.DeviceEntity;
import com.pro.WOLmgr.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceAuthRepository extends JpaRepository<DeviceAuthEntity, DeviceAuthId> {
    List<DeviceAuthEntity> findByAuthUser(UserEntity authUser);
}
