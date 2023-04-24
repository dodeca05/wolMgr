package com.pro.WOLmgr.Jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pro.WOLmgr.entity.UserEntity;
import com.pro.WOLmgr.util.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

// 스프링 시큐리티에 UsernamePasswordAuthenticationFilter 존재
// /login 요청시 username, password 전송하면 (POST)
// UsernamePasswordAuthenticationFilter이게 작동함
@RequiredArgsConstructor
@Log4j2
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final Environment env;

    // /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        log.info("jwt 로그인 시도중");

        // 1. username, password 받아서

        ObjectMapper om = new ObjectMapper();

        UserEntity userEntity = null;
        try {
            userEntity = om.readValue(request.getInputStream(), UserEntity.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info(userEntity);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userEntity.getUserId(), userEntity.getPassword());

        // PrincipalDetailsService의 loadUserByUsername() 를 사용함
        // DB에 있는 username과 password가 일치한다는 뜻
        Authentication authentication =
                authenticationManager.authenticate(authenticationToken);

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        log.info("로그인 완료 :" + principalDetails.getUserEntity().getUserId()); // 로그인 되면 뜸

        // 세션에 authentication 이거 저장됨 => 로그인 되었다는 뜻
        // 권한 관리를 시큐리티가 대신 해주기 때문에 편하려고 해주는 것
        // jwt쓰기 때문에 세션을 만들 이유가 없지만 권한처리때문에 세션 사용함

        return authentication;

    }

    // 위 메소드 실행 후 인증이 되면 이 함수 실행
    // JWT 토큰을 만들어서 리퀘 요청한 사용자에게 JWT토큰을 발행하면 됨
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {

        log.info("인증이 완료 됨");

        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        //RSA방식 아님 hash 암호방식
        String jwtToken = JWT.create()
                .withSubject(principalDetails.getUsername()) // 의미 없음 토큰 이름
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 만료시간 밀리초단위
                .withClaim("userId", principalDetails.getUserEntity().getUserId()) // 유저 아이디 비공개 클레임 내가 넣고싶은거 암거나 넣으면 댐
                .withClaim("userRole", principalDetails.getUserEntity().getRoleList()) // 유저 권한
                .withClaim("userNumber", principalDetails.getUserEntity().getUserNumber()) // 유저 넘버
                .sign(Algorithm.HMAC512(env.getProperty("jwt_secret"))); // 내 서버의 암호 암호는 서버가 마음대로 만들면됨

        response.addHeader("Authorization", "Bearer " + jwtToken);

        // JSON 형식의 문자열을 생성
        String jsonResponse = String.format("{\"Authorization\": \"Bearer %s\"}", jwtToken);

        // HTTP Response Body에 추가
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }
}
