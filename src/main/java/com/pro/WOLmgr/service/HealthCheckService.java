package com.pro.WOLmgr.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Log4j2
@Service
public class HealthCheckService {
    public boolean healthCheck(String ip) {
        boolean result = false;

        try {
            //InetAddress.getByName() 메소드는 도메인 이름 또는 IP 주소를 이용하여 InetAddress 객체를 생성하는 메소드
            //유효하지 않은 ip나 호스트를 잡기 위한것
            InetAddress inetAddress = InetAddress.getByName(ip);
            //InetAddress inetAddress = InetAddress.getByAddress(ip, new byte[]{(byte) 175, (byte) 123, (byte) 128, (byte) 92});
            // 만약 ip만 들어온다면 위 주석처리한 코드도 사용가능

            //isReachable() 메서드는 주어진 IP 주소 또는 호스트명의 호스트가 네트워크 상에서 도달 가능한지 여부를 확인하는 메서드
            if (inetAddress.isReachable(2000)) {
                // 2초내에 핑이 테스트에 성공한경우 로그로 테스트 성공을 찍어주고 반환할 변수를 true 로 바꿔서 반환
                log.info("ping test success");
                result = true;
            } else {
                // 2초내에 핑이 테스트에 실패한경우로 로그로 핑 테스트 실패을 찍어주고 반환할 변수를 false 인 상태로 반환
                // 핑 테스트 실패
                log.info("ping test fail");
            }
        } catch (UnknownHostException e) {
            // 호스트나 IP 주소가 잘못되었을 때 예외 처리
            e.printStackTrace();
        } catch (IOException e) {
            // 핑 테스트 중 예외 발생 시 예외 처리
            e.printStackTrace();
        }
        //결과값 반환
        return result;
    }
}
