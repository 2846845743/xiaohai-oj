package com.xiaohai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaohai.model.dto.UserDTO;
import com.xiaohai.model.dto.UserLoginDTO;
import com.xiaohai.model.po.User;
import com.xiaohai.utils.Result;

import javax.servlet.http.HttpServletRequest;

public interface UserService extends IService<User> {
    void register(UserDTO userDTO) throws Exception;

    Result<String> login(UserLoginDTO user);

    Result<String> getAvator();

    Result<String> logout(HttpServletRequest request);
}
