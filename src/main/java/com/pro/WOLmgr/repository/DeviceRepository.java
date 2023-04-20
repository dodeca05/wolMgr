package com.pro.WOLmgr.repository;


import com.pro.WOLmgr.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device,Long> {

    boolean existsByIpAddress(String ip);
    boolean existsByDeviceNumber(Long deviceNumber);
}
