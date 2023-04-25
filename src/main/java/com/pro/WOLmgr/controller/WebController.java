package com.pro.WOLmgr.controller;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;


@ApiIgnore //개발자 페이지인 swagger에 프론트 관련 api를 노출 시킬 필요가 없으므로 이 어노테이션을 추가하여 아래 api는 swagger에 노출하지 않도록 합니다.
@Controller
@RequiredArgsConstructor
@Log4j2
public class WebController {
    @GetMapping("/wol/login")
    public void login(){}

    @GetMapping("/")
    public String index() { return "/wol/login"; }

    @GetMapping("/web/main")
    public String main(){return "/wol";}
}
