package com.pro.WOLmgr.controller;

import com.pro.WOLmgr.dto.DeviceRequestDTO;
import com.pro.WOLmgr.dto.DeviceResponseDTO;
import com.pro.WOLmgr.service.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping("/device")
    public ResponseEntity<DeviceResponseDTO> createDevice(@RequestBody DeviceRequestDTO deviceRequestDTO){
//        TODO: 뷰단에서 자바스크립트 코드로 검증하도록 하자.
//        if(!deviceRequestDTO.getDeviceName().matches("[a-zA-Z0-9]+")){
//            return new ResponseEntity<>("Only English and numbers are available.",HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<>(deviceService.register(deviceRequestDTO), HttpStatus.OK);
    }
    @PutMapping("/device")
    public ResponseEntity<DeviceResponseDTO> updateDevice(@RequestBody DeviceRequestDTO deviceRequestDTO){
//        TODO: 뷰단에서 자바스크립트 코드로 검증하도록 하자.
//        if(!deviceRequestDTO.getDeviceName().matches("[a-zA-Z0-9]+")){
//            return new ResponseEntity<>("Only English and numbers are available.",HttpStatus.BAD_REQUEST);
//        }
        //TODO: 뭔가 좀 더 좋은 JPA 로직이 있을 듯 하다
        deviceService.delete(deviceRequestDTO.getDeviceName());
        return new ResponseEntity<>(deviceService.register(deviceRequestDTO), HttpStatus.OK);
    }


    @GetMapping("/device")
    public List<DeviceResponseDTO> readDeviceList(@RequestParam(
            name = "username",
            required = false,
            defaultValue = "") String username)
    {
        return username.isEmpty()?
                deviceService.readDeviceList():
                deviceService.userAccessDevices(username);
    }

    @GetMapping("/device/{deviceName}")
    public DeviceResponseDTO readDevice(@PathVariable String deviceName)
    {
        return DeviceResponseDTO.fromEntity(deviceService.getDeviceEntity(deviceName));
    }


    @PutMapping("/device/deviceDuplication/{deviceName}")
    public ResponseEntity<Boolean> deviceNameRead(@PathVariable String deviceName){
        return new ResponseEntity<>(deviceService.deviceNameCheck(deviceName),HttpStatus.OK);
    }

    @PutMapping("/device/macDuplication/{macAddress}")
    public ResponseEntity<Boolean> deviceMacAddressRead(@PathVariable String macAddress){
        return new ResponseEntity<>(deviceService.macAddressCheck(macAddress),HttpStatus.OK);
    }

    @DeleteMapping("/device/{deviceName}")
    public ResponseEntity<String> deleteDevice(@PathVariable String deviceName){
        deviceService.delete(deviceName);
        return new ResponseEntity<>(deviceName,HttpStatus.NO_CONTENT);
    }
}
