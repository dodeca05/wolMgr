package com.pro.WOLmgr.Jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.pro.WOLmgr.entity.UserEntity;
import com.pro.WOLmgr.repository.UserRepository;
import com.pro.WOLmgr.util.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

@RequiredArgsConstructor
@Log4j2
public class JwtTokenReader {

    private final Environment env;
    private final UserRepository userRepository;

    public Role jwtReader(String value)
            throws
            JWTDecodeException,
            SignatureVerificationException {
        String token = value.replace("Bearer ", "");

        String username = JWT
                .require(Algorithm.HMAC512(env.getProperty("jwt_secret")))
                .build()
                .verify(token)
                .getClaim("username") // 토큰의 "username" 클레임 값을 가져옵니다.
                .asString(); // "username" 값을 문자열로 변환합니다.

        if (username != null) {
            UserEntity userEntity = userRepository.findByUsername(username);
            return Role.valueOf(userEntity.getRoleList().get(0));
        }

        return null;
    }


}
