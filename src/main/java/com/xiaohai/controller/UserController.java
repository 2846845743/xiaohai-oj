package com.xiaohai.controller;


import com.xiaohai.model.dto.UserDTO;
import com.xiaohai.model.dto.UserLoginDTO;
import com.xiaohai.model.po.User;
import com.xiaohai.model.vo.UserInfo;
import com.xiaohai.service.UserService;
import com.xiaohai.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //查询用户个人信息接口
    @GetMapping("/info")
    public Result<UserInfo> info(){
        log.info("正在查询用户个人信息");
        return userService.info();
    }

    //用户登录
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserLoginDTO user) {
        //用户登录,登录成功返回token
        log.info("用户:{}正在登录", user);
        return userService.login(user);
    }


    //用户注册
    @PostMapping("/register")
    public Result register(@RequestBody UserDTO user) throws Exception {
        //用户注册
        log.info("用户:{}-正在注册", user);
        userService.register(user);
        return Result.success(null);
    }

    //获取用户头像
    @GetMapping("/avatar")
    public Result<String> getAvator() {
        //
        log.info("正在获取当前登录用户头像");
        return userService.getAvator();
    }

    //用户登出
    @PostMapping("/logout")
    public Result<String> logout(HttpServletRequest request){

        return userService.logout(request);
    }
}
