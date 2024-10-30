package com.xiaohai.controller;


import com.xiaohai.model.dto.UserDTO;
import com.xiaohai.model.dto.UserLoginDTO;
import com.xiaohai.model.po.User;
import com.xiaohai.service.UserService;
import com.xiaohai.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //用户登录
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserLoginDTO user) throws Exception {
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


}
