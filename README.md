# Wake-on-LAN 서비스

이 프로젝트는 컴퓨터의 전원을 제어하기 위한 스프링(Spring) 기반의 서비스입니다. REST API를 통해 컴퓨터의 정보와 이름을 입력받아 Wake-on-LAN 신호를 보내어 컴퓨터를 켤 수 있습니다. 또한, 각 컴퓨터의 전원 제어는 토큰화되어 있어 권한 관리 기능을 포함하고 있습니다.

## 기능

- 컴퓨터 정보와 이름을 입력받아 REST API를 통해 컴퓨터를 켤 수 있습니다.
- 각 컴퓨터의 전원 제어는 토큰화되어 있어 권한 관리 기능을 제공합니다.

## 요구 사항

- Java 8 이상
- Spring Framework
- Wake-on-LAN 기능을 지원하는 네트워크 환경

## 설치 및 설정

1. 이 저장소를 클론합니다.
2. db 정보를 환경변수로 등록합니다. 환경 변수로 다음과 같은 이름으로 db를 등록합니다
- db_driverClassName
- db_url
- db_username
- db_password
3. `application.properties` 에서 서버 포트를 지정합니다.(기본 :8081)
```properties


# 서버 포트 설정
server.port=8080
```
4. 프로젝트를 빌드 후 실행합니다.

5. 접속 후 계정 생성 하고 로그인을 하면 다음과 같은 화면이 나오게 됩니다.

![스크린샷 2023-06-03 오후 11 00 56](https://github.com/dodeca05/wolMgr/assets/49556303/bbd02b7c-3b5a-4d05-8cd1-dee2466e375a)

