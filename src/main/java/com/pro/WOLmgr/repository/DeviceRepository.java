package com.pro.WOLmgr.repository;


import com.pro.WOLmgr.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<DeviceEntity,Long> {

    boolean existsByMacAddress(String ip);
    boolean existsByDeviceName(String name);
    boolean existsByDeviceNumber(Long deviceNumber);
}
