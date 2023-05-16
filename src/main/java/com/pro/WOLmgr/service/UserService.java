package com.pro.WOLmgr.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.pro.WOLmgr.dto.UserInfoDTO;
import com.pro.WOLmgr.dto.UserPrivacyDTO;
import com.pro.WOLmgr.entity.UserEntity;
import com.pro.WOLmgr.repository.UserRepository;
import com.pro.WOLmgr.util.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.pro.WOLmgr.util.Role.SERVICE;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Environment env;

    public UserInfoDTO userCreate(UserPrivacyDTO userDTO) {
        // 패스워드 인코딩을 하여 비밀번호 보안을 강화합니다.
        if(userDTO.getRoles().contains(SERVICE)){
            String jwtToken = JWT.create()
                    .withSubject(userDTO.getUsername()) // 의미 없음 토큰 이름
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 30 * 12 * 10)) // 만료시간 밀리초단위
                    .withClaim("username", userDTO.getUsername()) // 유저 아이디 비공개 클레임 내가 넣고싶은거 암거나 넣으면 댐
                    .withClaim("userRole", "SERVICE") // 유저 권한
                    .sign(Algorithm.HMAC512(env.getProperty("jwt_secret"))); // 내 서버의 암호 암호는 서버가 마음대로 만들면됨
            userDTO.setServiceToken(jwtToken);
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(UserPrivacyDTO.fromDTO(userDTO));
        return userRepository.findByEmail(userDTO.getEmail()).toDto();
    }

    public UserInfoDTO userUpdate(UserInfoDTO dto){
        UserEntity user = userRepository.findByUsername(dto.getUsername());
        user.change(dto);
        userRepository.save(user);
        return userRepository.findByUsername(dto.getUsername()).toDto();
    }

    public List<UserInfoDTO> readUserList(){
        return userRepository
                .findAll()
                .stream()
                .map(user -> user.toDto())
                .collect(Collectors.toList());
    }

    public UserInfoDTO readUser(String username){
        return userRepository.findByUsername(username).toDto();
    }
    public boolean idCheck(String userid) { return userRepository.existsByUserId(userid); }
    public boolean emailCheck(String email){
        return userRepository.existsByEmail(email);
    }
    public boolean userNameCheck(String userName){
        return userRepository.existsByUsername(userName);
    }

}
