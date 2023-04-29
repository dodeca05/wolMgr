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
    public ResponseEntity<DeviceResponseDTO> deviceRegister(@RequestBody DeviceRequestDTO deviceRequestDTO){
//        TODO: 뷰단에서 자바스크립트 코드로 검증하도록 하자.
//        if(!deviceRequestDTO.getDeviceName().matches("[a-zA-Z0-9]+")){
//            return new ResponseEntity<>("Only English and numbers are available.",HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<>(deviceService.register(deviceRequestDTO), HttpStatus.OK);
    }
    @GetMapping("/device")
    public List<DeviceResponseDTO>readDeviceList()
    {
        return deviceService.readDeviceList();
    }


    @GetMapping("/device/duplication/{deviceName}")
    public ResponseEntity<Boolean> deviceNameRead(@PathVariable String deviceName){
        return new ResponseEntity<>(deviceService.deviceNameCheck(deviceName),HttpStatus.OK);
    }

    @GetMapping("/device/duplication/{macAddress}")//Todo : 겹침 값 중복을 확인하는 별도의 api로 통합 필요 Get -> Put 변경
    public ResponseEntity<Boolean> deviceMacAddressRead(@PathVariable String macAddress){
        return new ResponseEntity<>(deviceService.macAddressCheck(macAddress),HttpStatus.OK);
    }

    @DeleteMapping("/device/{deviceNumber}")//Todo:권한 체크 및 deviceNumber를 deviceName 변경 고려
    public ResponseEntity<Long> deviceDelete(@PathVariable Long deviceNumber){
        deviceService.delete(deviceNumber);
        return new ResponseEntity<>(deviceNumber,HttpStatus.NO_CONTENT);
    }
}
