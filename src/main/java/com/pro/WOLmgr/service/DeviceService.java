package com.pro.WOLmgr.service;

import com.pro.WOLmgr.dto.DeviceInfoDTO;
import com.pro.WOLmgr.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static com.pro.WOLmgr.dto.DeviceInfoDTO.toEntity;

@Service
@RequiredArgsConstructor
@Log4j2
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public boolean register(DeviceInfoDTO deviceInfoDTO){
        deviceRepository.save(toEntity(deviceInfoDTO));
        return deviceRepository.existsByIpAddress(deviceInfoDTO.getIpAddress());
    }

    public boolean delete(Long deleteNum){
        deviceRepository.deleteById(deleteNum);
        return deviceRepository.existsByDeviceNumber(deleteNum);
    }
}
