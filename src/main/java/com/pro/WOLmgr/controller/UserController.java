package com.pro.WOLmgr.controller;

import com.pro.WOLmgr.Jwt.JwtTokenReader;
import com.pro.WOLmgr.dto.UserInfoDTO;
import com.pro.WOLmgr.dto.UserPrivacyDTO;
import com.pro.WOLmgr.repository.UserRepository;
import com.pro.WOLmgr.service.MailService;
import com.pro.WOLmgr.service.UserService;
import com.pro.WOLmgr.util.Role;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.pro.WOLmgr.util.Role.USER;

@Controller
@RequiredArgsConstructor
@Log4j2
public class UserController {
    private final MailService mailService;
    private final UserService userService;
    private final Environment env;
    private final UserRepository userRepository;


    //이메일 인증
    @GetMapping("/mailSendNum/{email}") // 메일 유효성 검사
    public ResponseEntity<String> mailSendNum(@PathVariable String email) throws MessagingException, IOException {
        return new ResponseEntity<>(mailService.joinEmail(email), HttpStatus.OK);
    }

    @GetMapping("/member")
    public ResponseEntity<List<UserInfoDTO>> readUserList(){
        return new ResponseEntity<>(userService.readUserList(),HttpStatus.OK);
    }

    @GetMapping("/member/{username}")
    public ResponseEntity<UserInfoDTO> readUser(@PathVariable String username){
        return new ResponseEntity<>(userService.readUser(username),HttpStatus.OK);
    }

    @GetMapping("/username/duplication/{username}")
    public ResponseEntity<Boolean> usernameCheck(@PathVariable String username){
        return new ResponseEntity<>(userService.userNameCheck(username),HttpStatus.OK);
    }

    @GetMapping("/id/duplication/{userId}") // 아이디 중복 체크
    public ResponseEntity<Boolean> idCheck(@PathVariable String userId){
        return new ResponseEntity<>(userService.idCheck(userId), HttpStatus.OK);
    }

    @GetMapping("/email/duplication/{email}") // 이메일 중복 체크
    public ResponseEntity<Boolean> emailCheck(@PathVariable String email){
        return new ResponseEntity<>(userService.emailCheck(email), HttpStatus.OK);
    }

    @GetMapping("/auth/duplication/{token}")
    public void example(@PathVariable String token, HttpServletResponse response) {
        response.setHeader("Authorization", token);
    }

    @PostMapping("/member") // 회원가입
    public ResponseEntity<UserInfoDTO> join(@RequestBody UserPrivacyDTO userDTO,
                                            @RequestHeader(value = "Authorization", required = false) String value){
        if( value == null || value.isEmpty() || new JwtTokenReader(env,userRepository).jwtReader(value) == null){
            userDTO.setRoles(Collections.singleton(USER));
        }
        return new ResponseEntity<>(userService.userCreate(userDTO),HttpStatus.OK);
    }

}
