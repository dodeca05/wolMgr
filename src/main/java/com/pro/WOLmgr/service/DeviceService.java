package com.pro.WOLmgr.service;

import com.pro.WOLmgr.dto.DeviceAuthDTO;
import com.pro.WOLmgr.dto.DeviceRequestDTO;
import com.pro.WOLmgr.dto.DeviceResponseDTO;
import com.pro.WOLmgr.entity.DeviceAuthEntity;
import com.pro.WOLmgr.entity.DeviceAuthId;
import com.pro.WOLmgr.entity.DeviceEntity;
import com.pro.WOLmgr.entity.UserEntity;
import com.pro.WOLmgr.repository.DeviceAuthRepository;
import com.pro.WOLmgr.repository.DeviceRepository;
import com.pro.WOLmgr.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;
    private final DeviceAuthRepository deviceAuthRepository;

    public DeviceResponseDTO register(DeviceRequestDTO deviceRequestDTO){
        DeviceEntity deviceEntity = DeviceRequestDTO.fromDTO(deviceRequestDTO);
        DeviceResponseDTO responseDTO = DeviceResponseDTO.fromEntity(deviceRepository.save(deviceEntity));
        return responseDTO;
    }

    @Transactional
    public void delete(String deviceName){
        deviceRepository.deleteByDeviceName(deviceName);
    }

    public boolean macAddressCheck(String ipAddress){
        return deviceRepository.existsByMacAddress(ipAddress);
    }
    public boolean deviceNameCheck(String deviceName) { return deviceRepository.existsByDeviceName(deviceName); }

    public DeviceAuthDTO accessRegister(DeviceAuthDTO dto){
        for (int i = 0; i < dto.getDeviceId().size(); i++) {
            DeviceAuthId deviceAuthId = DeviceAuthId
                    .builder()
                    .authDevice(dto.getDeviceId().get(i))
                    .authUser(dto.getUserId())
                    .build();
            DeviceAuthEntity deviceAuthEntity = DeviceAuthEntity
                    .builder()
                    .id(deviceAuthId)
                    .build();
            deviceAuthRepository.save(deviceAuthEntity);
        }
        return dto;
    }

    public void accessDelete(DeviceAuthDTO dto){
        for (int i = 0; i < dto.getDeviceId().size(); i++) {
            DeviceAuthId deviceAuthId = DeviceAuthId
                    .builder()
                    .authDevice(dto.getDeviceId().get(i))
                    .authUser(dto.getUserId())
                    .build();
            deviceAuthRepository.deleteById(deviceAuthId);
        }
    }

    public DeviceEntity getDeviceEntity(String deviceName) {
        return deviceRepository.findByDeviceName(deviceName);
    }

    public List<DeviceResponseDTO> readDeviceList(){
        return deviceRepository
                .findAll()
                .stream()
                .map(device -> DeviceResponseDTO.fromEntity(device))
                .collect(Collectors.toList());
    }

    public List<DeviceResponseDTO> userAccessDevices(String username){
        UserEntity user = userRepository.findByUsername(username);
        List<DeviceAuthEntity> deviceAuthEntities = deviceAuthRepository.findByAuthUser(user);
        List<DeviceResponseDTO> result = new ArrayList<>();
        for (DeviceAuthEntity temp: deviceAuthEntities) {
            result.add(DeviceResponseDTO
                    .fromEntity(deviceRepository
                            .findByDeviceName(
                                    temp.getAuthDevice().getDeviceName())));
        }
        return result;
    }
    public boolean hasUserPermission(String userName,String deviceName)
    {
        UserEntity user = userRepository.findByUsername(userName);
        List<DeviceAuthEntity> deviceAuthEntities = deviceAuthRepository.findByAuthUser(user);
        for (DeviceAuthEntity temp: deviceAuthEntities) {
            String permissonedDeviceName=temp.getAuthDevice().getDeviceName();
            if(permissonedDeviceName.trim().equals(deviceName.trim()))
                return true;
        }
        return false;
    }
}
