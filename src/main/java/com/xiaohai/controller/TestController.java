package com.xiaohai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class TestController {

    @GetMapping("/whh")
    public void tes(HttpServletResponse response) {
        response.addHeader("whh","whh");
    }

}
