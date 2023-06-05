package com.pro.WOLmgr;

import com.pro.WOLmgr.dto.DeviceRequestDTO;
import com.pro.WOLmgr.dto.UserPrivacyDTO;
import com.pro.WOLmgr.repository.UserRepository;
import com.pro.WOLmgr.service.DeviceService;
import com.pro.WOLmgr.service.UserService;
import com.pro.WOLmgr.util.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.EnumSet;
import java.util.Set;

import static com.pro.WOLmgr.util.Role.ADMIN;
import static com.pro.WOLmgr.util.Role.USER;

@SpringBootTest
public class testUserInsert {

    @Autowired
    private UserService userService;
    @Autowired
    private DeviceService deviceService;

    @Test
    public void testInsert(){
        Set<Role> roles = EnumSet.of(USER);
        for (int i = 1; i < 8; i++) {
            UserPrivacyDTO dto = UserPrivacyDTO
                    .builder()
                    .userId("testUser"+i)
                    .username("testUser"+i)
                    .password("1234")
                    .email("test"+i+"@naver.com")
                    .roles(roles)
                    .build();
            userService.userCreate(dto);
        }
        for (int i = 1; i < 10; i++) {
            DeviceRequestDTO dto = DeviceRequestDTO
                    .builder()
                    .deviceName("testDevice"+i)
                    .ipAddress(i+","+i+","+i+","+i)
                    .macAddress(i+","+i+","+i+","+i)
                    .build();
            deviceService.register(dto);
        }
    }

    @Test
    public void adminInsert(){
        Set<Role> roles = EnumSet.of(ADMIN);
        for (int i = 1; i < 8; i++) {
            UserPrivacyDTO dto = UserPrivacyDTO
                    .builder()
                    .userId("admin")
                    .username("admin123")
                    .password("1234")
                    .email("admin"+i+"@naver.com")
                    .roles(roles)
                    .build();
            userService.userCreate(dto);
        }
    }
}
