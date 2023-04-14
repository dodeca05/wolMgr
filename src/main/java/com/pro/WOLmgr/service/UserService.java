package com.pro.WOLmgr.service;

import com.pro.WOLmgr.dto.UserPrivacyDTO;
import com.pro.WOLmgr.entity.User;
import com.pro.WOLmgr.repository.UserRepository;
import com.pro.WOLmgr.util.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.Set;

import static com.pro.WOLmgr.dto.UserPrivacyDTO.toEntity;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Long register(UserPrivacyDTO userDTO) {
        // 최초 회원가입때는 회원의 권한을 유저로 한정합니다.
        Set<Role> roles = EnumSet.of(Role.USER);

        // 패스워드 인코딩을 하여 비밀번호 보안을 강화합니다.
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setRoles(roles);

        userRepository.save(toEntity(userDTO));
        User userInfo = userRepository.findByEmail(userDTO.getEmail());

        return userInfo.getUserNumber();
    }

    public boolean idCheck(String userid) { return userRepository.existsByUserId(userid); }
    public boolean emailCheck(String email){
        return userRepository.existsByEmail(email);
    }

}
