package com.pro.WOLmgr.controller;

import com.pro.WOLmgr.dto.DeviceAuthRequestDTO;
import com.pro.WOLmgr.dto.DeviceAuthResponseDTO;
import com.pro.WOLmgr.dto.DeviceRequestDTO;
import com.pro.WOLmgr.entity.DeviceEntity;
import com.pro.WOLmgr.dto.DeviceResponseDTO;
import com.pro.WOLmgr.service.DeviceService;
import com.pro.WOLmgr.service.WakeOnLanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Log4j2
public class WolServiceController{
    private final DeviceService deviceService;
    private final WakeOnLanService wakeOnLanService;

    @PutMapping("/wake/{deviceName}")
    public String sendPacket(@PathVariable String deviceName) {
        //TODO : 권한 체크
        DeviceEntity deviceEntity=deviceService.getDeviceEntity(deviceName);
        try {
            wakeOnLanService.sendMagicPacket(deviceEntity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "{'status':'success'}";
    }

    @GetMapping("/healthCheck/{deviceName}")
    public String healthCheck(@PathVariable String deviceName){
        // TODO: healthCheck 로직 구현
        throw new NotImplementedException("구현이 되지 않은 서비스입니다.");
        //return "{ 'key' : 'value' }";
    }

    @PostMapping("/device")
    public ResponseEntity<DeviceResponseDTO> deviceRegister(@RequestBody DeviceRequestDTO deviceRequestDTO){
//        TODO: 뷰단에서 자바스크립트 코드로 검증하도록 하자.
//        if(!deviceRequestDTO.getDeviceName().matches("[a-zA-Z0-9]+")){
//            return new ResponseEntity<>("Only English and numbers are available.",HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<>(deviceService.register(deviceRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/device/duplication/{deviceName}")
    public ResponseEntity<Boolean> deviceNameRead(@PathVariable String deviceName){
        return new ResponseEntity<>(deviceService.deviceNameCheck(deviceName),HttpStatus.OK);
    }

    @GetMapping("/device/duplication/{macAddress}")
    public ResponseEntity<Boolean> deviceMacAddressRead(@PathVariable String macAddress){
        return new ResponseEntity<>(deviceService.macAddressCheck(macAddress),HttpStatus.OK);
    }

    @DeleteMapping("/device/{deviceNumber}")
    public ResponseEntity<Long> deviceDelete(@PathVariable Long deviceNumber){
        deviceService.delete(deviceNumber);
        return new ResponseEntity<>(deviceNumber,HttpStatus.NO_CONTENT);
    }

    @GetMapping("/access")
    public ResponseEntity<?> accessRead(@ModelAttribute DeviceAuthRequestDTO deviceAuthRequestDTO){
        return deviceService.accessCheck(deviceAuthRequestDTO)?
                new ResponseEntity<>(deviceAuthRequestDTO,HttpStatus.OK):
                new ResponseEntity<>("Access failed.",HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/access")
    public ResponseEntity<DeviceAuthResponseDTO> accessCreate(@RequestBody DeviceAuthRequestDTO deviceAuthRequestDTO){
        return new ResponseEntity<>(deviceService.accessRegister(deviceAuthRequestDTO),HttpStatus.OK);
    }

    @DeleteMapping("/access")
    public ResponseEntity<DeviceAuthRequestDTO> accessDelete(@ModelAttribute DeviceAuthRequestDTO deviceAuthRequestDTO){
        deviceService.accessDelete(deviceAuthRequestDTO);
        return new ResponseEntity<>(deviceAuthRequestDTO, HttpStatus.NO_CONTENT);
    }

}