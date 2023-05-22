package com.pro.WOLmgr.controller;

import com.pro.WOLmgr.Jwt.JwtTokenReader;
import com.pro.WOLmgr.dto.DeviceAuthDTO;
import com.pro.WOLmgr.dto.DeviceResponseDTO;
import com.pro.WOLmgr.repository.UserRepository;
import com.pro.WOLmgr.service.DeviceService;
import com.pro.WOLmgr.util.Role;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.core.env.Environment;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class AccessController {
    private final DeviceService deviceService;
    private final Environment env;
    private final UserRepository userRepository;

    @PostMapping("/access")
    public ResponseEntity<DeviceAuthDTO> accessCreate(@RequestBody DeviceAuthDTO deviceAuthDTO,@RequestHeader("Authorization") String authorizationHeader) throws AccessException {
        JwtTokenReader tokenReader=new JwtTokenReader(env,userRepository,authorizationHeader);
        Role role=tokenReader.getRole();
        if(role== Role.USER)
        {
            throw new AccessException("권한이 없습니다.");
        } else if (role==Role.MANAGER) {
            throw new NotImplementedException("구현이 되지 않은 기능입니다.");
        }
        return new ResponseEntity<>(deviceService.accessRegister(deviceAuthDTO),HttpStatus.OK);
    }

    @DeleteMapping("/access")
    public ResponseEntity<DeviceAuthDTO> accessDelete(@RequestBody DeviceAuthDTO deviceAuthDTO,@RequestHeader("Authorization") String authorizationHeader) throws AccessException {
        JwtTokenReader tokenReader=new JwtTokenReader(env,userRepository,authorizationHeader);
        if(tokenReader.getRole()!= Role.ADMIN)
        {
            throw new AccessException("권한이 없습니다.");
        }
        deviceService.accessDelete(deviceAuthDTO);
        return new ResponseEntity<>(deviceAuthDTO, HttpStatus.NO_CONTENT);
    }
}
