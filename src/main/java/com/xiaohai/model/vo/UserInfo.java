package com.xiaohai.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserInfo {

    private String username;
    private String email;
    private int passNumber;
    private int submitNumber;
    private int wrongNumber;//解答错误数
    private List<Integer> passQuestions;//通过题目大全
    private String avatarUrl;
}
