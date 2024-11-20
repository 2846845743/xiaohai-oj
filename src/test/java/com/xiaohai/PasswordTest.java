package com.xiaohai;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.xiaohai.mapper.*;
import com.xiaohai.model.dto.ExecuteMessage;
import com.xiaohai.model.dto.QuestionSaveDTO;
import com.xiaohai.model.dto.StatusPageQueryDTO;
import com.xiaohai.model.po.User;
import com.xiaohai.model.vo.QuestionPageQueryVO;
import com.xiaohai.model.vo.StatusPageQueryVO;
import com.xiaohai.model.vo.UserInfo;
import com.xiaohai.service.QuestionService;
import com.xiaohai.service.UserService;
import com.xiaohai.utils.ProcessUtils;
import com.xiaohai.utils.Result;
import com.xiaohai.utils.UserHolder;
import org.checkerframework.checker.units.qual.A;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.xml.bind.DatatypeConverter;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class PasswordTest {
////    @Test
////    void test_executeTime() {
////        String codeUrl = "D:\\Main.class";
////        StringBuilder inputArgsBuilder = new StringBuilder();
////        List<String> inputList = new ArrayList<>();
////        inputList.add("1");inputList.add("2");
////        for (String inputArg : inputList) {
////            inputArgsBuilder.append(inputArg).append("\n");
////        }
////        String inputArgs = inputArgsBuilder.toString();
////
////        // 构建运行命令
////        String runCmd = String.format("java -Xmx256m -Dfile.encoding=UTF-8 -cp %s Main", codeUrl);
////
////        try {
////            Process runProcess = Runtime.getRuntime().exec(runCmd);
////
////            // 向子进程的标准输入流写入数据
////            try (OutputStream os = runProcess.getOutputStream()) {
////                os.write(inputArgs.getBytes());
////                os.flush();
////            }
////            // 获取执行结果信息
////            ExecuteMessage executeMessage = ProcessUtils.runProcessAndGetMessage(runProcess, "运行");
////            System.out.println("代码程序执行信息：" + executeMessage);
////        } catch (Exception e) {
////            throw new RuntimeException("程序执行错误" + e);
////        }
////
////
////    }
////
////
////
////    @Test
////    public void test_salt() {
////        //测试加盐后算法
////        String password = "123456";
////        String saltString = "96cc6f8c44be2b1f200120ea60ba42a7";
////        String saltedPassword = password + saltString;//加盐后密码
////        MessageDigest md = null;
////        try {
////            md = MessageDigest.getInstance("MD5");
////        } catch (NoSuchAlgorithmException e) {
////            throw new RuntimeException(e);
////        }
////        byte[] hashedPassword = md.digest(saltedPassword.getBytes());//加盐后的md5
////        String end = DatatypeConverter.printHexBinary(hashedPassword);
////        System.out.println(end);
////
////    }
//
//    @Autowired
//    private QuestionMapper questionMapper;
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//
//
//    @Autowired
//    private QuestionSubmitMapper questionSubmitMapper;
//
//
//    @Test
//    public void test_redis(){
//        List<String> strings = questionMapper.selectTypeNameByQuestionNumber(102);
//        System.out.println(strings);
//
//
//    }
//    @Autowired
//    private QuestionService questionService;
//    @Test
//    public void test_insert(){
//        QuestionSaveDTO questionSaveDTO = new QuestionSaveDTO();
//        questionSaveDTO.setNumber(1008);
//        questionSaveDTO.setDescription("sasa");
//        questionSaveDTO.setTitle("title");
//        questionSaveDTO.setInputDesc("输入描述");
//        questionSaveDTO.setOutputDesc("输出描述");
//        Map<String,String> map = new HashMap<>();
//        map.put("1 2","3");
//        map.put("4 6","10");
//
////        questionSaveDTO.setTypeList("1,2,数组");
////        questionSaveDTO.setTestCase(map);
//        questionService.saveQuestion(questionSaveDTO);
//    }

//    @Autowired
//    UserService userService;
//    @Test
//    public void test_userInfo(){
//        //测试用户信息接口
//        Result<UserInfo> info =
//                userService.info();
//        UserInfo data = info.getData();
//        System.out.println(data );
//    }

}
