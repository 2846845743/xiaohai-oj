package com.xiaohai.model.dto;

import lombok.Data;

//注册DTO
@Data
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private String code;//验证码！
    private String uuid;//接收前端UUID。
}
