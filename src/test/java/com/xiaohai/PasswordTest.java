package com.xiaohai;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaohai.model.dto.ExecuteMessage;
import com.xiaohai.model.po.User;
import com.xiaohai.utils.ProcessUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.bind.DatatypeConverter;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PasswordTest {
//    @Test
//    void test_executeTime() {
//        String codeUrl = "D:\\Main.class";
//        StringBuilder inputArgsBuilder = new StringBuilder();
//        List<String> inputList = new ArrayList<>();
//        inputList.add("1");inputList.add("2");
//        for (String inputArg : inputList) {
//            inputArgsBuilder.append(inputArg).append("\n");
//        }
//        String inputArgs = inputArgsBuilder.toString();
//
//        // 构建运行命令
//        String runCmd = String.format("java -Xmx256m -Dfile.encoding=UTF-8 -cp %s Main", codeUrl);
//
//        try {
//            Process runProcess = Runtime.getRuntime().exec(runCmd);
//
//            // 向子进程的标准输入流写入数据
//            try (OutputStream os = runProcess.getOutputStream()) {
//                os.write(inputArgs.getBytes());
//                os.flush();
//            }
//            // 获取执行结果信息
//            ExecuteMessage executeMessage = ProcessUtils.runProcessAndGetMessage(runProcess, "运行");
//            System.out.println("代码程序执行信息：" + executeMessage);
//        } catch (Exception e) {
//            throw new RuntimeException("程序执行错误" + e);
//        }
//
//
//    }
//
//
//
//    @Test
//    public void test_salt() {
//        //测试加盐后算法
//        String password = "123456";
//        String saltString = "96cc6f8c44be2b1f200120ea60ba42a7";
//        String saltedPassword = password + saltString;//加盐后密码
//        MessageDigest md = null;
//        try {
//            md = MessageDigest.getInstance("MD5");
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//        byte[] hashedPassword = md.digest(saltedPassword.getBytes());//加盐后的md5
//        String end = DatatypeConverter.printHexBinary(hashedPassword);
//        System.out.println(end);
//
//    }

}
