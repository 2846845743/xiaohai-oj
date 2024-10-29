package com.xiaohai;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaohai.model.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@SpringBootTest
public class PasswordTest {
    @Test
    void testLambdaQueryWrapper() {
    }


    @Test
    public void test_salt() {
        //测试加盐后算法
        String password = "123456";
        String saltString = "96cc6f8c44be2b1f200120ea60ba42a7";
        String saltedPassword = password + saltString;//加盐后密码
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] hashedPassword = md.digest(saltedPassword.getBytes());//加盐后的md5
        String end = DatatypeConverter.printHexBinary(hashedPassword);
        System.out.println(end);

    }

}
