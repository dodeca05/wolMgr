package com.pro.WOLmgr.Jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.pro.WOLmgr.entity.UserEntity;
import com.pro.WOLmgr.repository.UserRepository;
import com.pro.WOLmgr.util.Role;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

@AllArgsConstructor
@Log4j2
public class JwtTokenReader {

    private Environment env;
    private UserRepository userRepository;
    private String token;

    public String getName()
            throws
            JWTDecodeException,
            SignatureVerificationException {
        return JWT
                .require(Algorithm.HMAC512(env.getProperty("jwt_secret")))
                .build()
                .verify(this.token.replace("Bearer ", ""))
                .getClaim("username") // 토큰의 "username" 클레임 값을 가져옵니다.
                .asString(); // "username" 값을 문자열로 변환합니다.;
    }

    public Role getRole()
            throws
            JWTDecodeException,
            SignatureVerificationException {

        String username = this.getName();
        if (username != null)
            return Role.valueOf( userRepository.findByUsername(username).getRoleList().get(0));

        return null;
    }


}
