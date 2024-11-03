package com.xiaohai.utils;

import com.xiaohai.model.dto.UserRedis;
import com.xiaohai.model.po.User;

public class UserHolder {
    private static final ThreadLocal<UserRedis> tl = new ThreadLocal<>();

    public static void saveUser(UserRedis user){
        tl.set(user);
    }

    public static UserRedis getUser(){
        return tl.get();
    }

    public static void removeUser(){
        tl.remove();
    }
}
