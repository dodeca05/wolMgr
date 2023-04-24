package com.pro.WOLmgr.service;

import com.pro.WOLmgr.dto.DeviceAuthRequestDTO;
import com.pro.WOLmgr.dto.DeviceAuthResponseDTO;
import com.pro.WOLmgr.dto.DeviceRequestDTO;
import com.pro.WOLmgr.dto.DeviceResponseDTO;
import com.pro.WOLmgr.entity.DeviceAuthEntity;
import com.pro.WOLmgr.entity.DeviceEntity;
import com.pro.WOLmgr.entity.UserEntity;
import com.pro.WOLmgr.repository.DeviceAuthRepository;
import com.pro.WOLmgr.repository.DeviceRepository;
import com.pro.WOLmgr.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;
    private final DeviceAuthRepository deviceAuthRepository;

    public DeviceResponseDTO register(DeviceRequestDTO deviceRequestDTO){
        DeviceEntity deviceEntity = new DeviceRequestDTO().toEntity(deviceRequestDTO);
        DeviceResponseDTO responseDTO = new DeviceResponseDTO().toDTO(deviceRepository.save(deviceEntity));
        return responseDTO;
    }

    public boolean delete(Long deleteNum){
        deviceRepository.deleteById(deleteNum);
        return deviceRepository.existsByDeviceNumber(deleteNum);
    }

    public boolean macAddressCheck(String ipAddress){
        return deviceRepository.existsByMacAddress(ipAddress);
    }
    public boolean deviceNameCheck(String deviceName) { return deviceRepository.existsByDeviceName(deviceName); }

    public boolean accessCheck(DeviceAuthRequestDTO deviceAuthRequestDTO){
        Optional<UserEntity> user = userRepository.findById(deviceAuthRequestDTO.getUserId());
        Optional<DeviceEntity> device = deviceRepository.findById(deviceAuthRequestDTO.getDeviceId());

        boolean isAuth = false;

        if(user.isPresent()&&device.isPresent()){
            isAuth = deviceAuthRepository.existsByAuthDeviceAndAuthUser(device.get(),user.get());
        }

        return isAuth;
    }

    public DeviceAuthResponseDTO accessRegister(DeviceAuthRequestDTO deviceAuthRequestDTO){
        Optional<UserEntity> user = userRepository.findById(deviceAuthRequestDTO.getUserId());
        Optional<DeviceEntity> device = deviceRepository.findById(deviceAuthRequestDTO.getDeviceId());

        if(user.isPresent()&&device.isPresent()){
            DeviceAuthEntity deviceAuthEntity = new DeviceAuthRequestDTO().toEntity(user.get(),device.get());
            deviceAuthRepository.save(deviceAuthEntity);

            DeviceAuthResponseDTO deviceAuthResponseDTO = new DeviceAuthResponseDTO().toEntity(deviceAuthEntity);

            return deviceAuthResponseDTO;
        }

        return null;
    }

    public boolean accessDelete(Long deviceAuthNum){
        deviceAuthRepository.deleteById(deviceAuthNum);
        return deviceAuthRepository.existsByDeviceAuthNumber(deviceAuthNum);
    }
}
