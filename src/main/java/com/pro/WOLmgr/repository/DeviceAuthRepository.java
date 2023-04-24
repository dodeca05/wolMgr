package com.pro.WOLmgr.repository;

import com.pro.WOLmgr.entity.DeviceAuthEntity;
import com.pro.WOLmgr.entity.DeviceEntity;
import com.pro.WOLmgr.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceAuthRepository extends JpaRepository<DeviceAuthEntity,Long> {

    boolean existsByAuthDeviceAndAuthUser(DeviceEntity authDevice, UserEntity authUser);
    boolean existsByDeviceAuthNumber(Long deviceAuthNumber);
}
