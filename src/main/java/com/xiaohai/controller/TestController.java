package com.xiaohai.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class TestController {

    @GetMapping("/whh")
    public void tes(HttpServletResponse response) {
        log.info("WHH接口");
        response.addHeader("whh","whh");
    }

}
