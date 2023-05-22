package com.pro.WOLmgr.repository;


import com.pro.WOLmgr.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<DeviceEntity,String> {

    boolean existsByMacAddress(String ip);
    boolean existsByDeviceName(String name);
    void deleteByDeviceName(String deviceName);
    DeviceEntity findByDeviceName(String deviceName);
}
