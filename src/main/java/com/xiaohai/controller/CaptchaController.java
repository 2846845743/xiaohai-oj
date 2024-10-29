package com.xiaohai.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.xiaohai.model.dto.CaptchaDTO;
import com.xiaohai.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

//验证码控制器
@CrossOrigin(exposedHeaders = "Uuid")
@RestController
public class CaptchaController {

    @Autowired
    private DefaultKaptcha captchaProducer;

    @Autowired
    private StringRedisTemplate redisTemplate;

    //生成验证码并返回UUID标识这个验证码
    @GetMapping("/captcha")
    public void getCaptcha(HttpServletResponse response) throws IOException {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        // 生成验证码文本
        String capText = captchaProducer.createText();
        String UUId = UUID.randomUUID().toString();
        response.setHeader("Uuid", UUId);
        // 将验证码文本存入Redis中，以便后续验证
//        response.get().setAttribute("captchaCode", capText);
        redisTemplate.opsForValue().set(UUId,capText,60, TimeUnit.SECONDS);

        // 生成验证码图片
        BufferedImage bi = captchaProducer.createImage(capText);
        ImageIO.write(bi, "jpg", response.getOutputStream());

//        return Result.success(null);
        //将UUID设置为相应头

    }

    @PostMapping("/validateCaptcha")
    public Result<String > verifyCaptcha(@RequestBody CaptchaDTO captchaDTO, HttpServletRequest request) {
        String uuid = request.getHeader("Uuid");
        captchaDTO.setUuid(uuid);
        String redisCode = redisTemplate.opsForValue().get(uuid);
        if(redisCode==null || redisCode.equals("")){
            return Result.fail("验证码过期了");
        }
        if(captchaDTO.getCode().equals(redisCode)) {
            return Result.success(null);
        }


        return Result.fail("验证码错误");
    }
}
