package com.pro.WOLmgr.service;

import com.pro.WOLmgr.dto.DeviceRequestDTO;
import com.pro.WOLmgr.dto.DeviceResponseDTO;
import com.pro.WOLmgr.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static com.pro.WOLmgr.dto.DeviceRequestDTO.toEntity;
import static com.pro.WOLmgr.dto.DeviceResponseDTO.toDTO;

@Service
@RequiredArgsConstructor
@Log4j2
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceResponseDTO register(DeviceRequestDTO deviceRequestDTO){
        return toDTO(deviceRepository.save(toEntity(deviceRequestDTO)));
    }

    public boolean delete(Long deleteNum){
        deviceRepository.deleteById(deleteNum);
        return deviceRepository.existsByDeviceNumber(deleteNum);
    }

    public boolean macAddressCheck(String ipAddress){
        return deviceRepository.existsByMacAddress(ipAddress);
    }
    public boolean deviceNameCheck(String deviceName){ return deviceRepository.existsByDeviceName(deviceName); }
}
