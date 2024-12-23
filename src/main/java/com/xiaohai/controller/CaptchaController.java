package com.xiaohai.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.xiaohai.model.dto.CaptchaDTO;
import com.xiaohai.model.vo.CaptchaVO;
import com.xiaohai.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

//验证码控制器
@Slf4j
@RestController
public class CaptchaController {


    @Autowired
    private StringRedisTemplate redisTemplate;
//    @Autowired
//    private DefaultKaptcha captchaProducer;
//
//
//
//    //生成验证码并返回UUID标识这个验证码
//    @GetMapping("/captcha")
//    public void getCaptcha(HttpServletResponse response) throws IOException {
//        response.setDateHeader("Expires", 0);
//        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
//        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
//        response.setHeader("Pragma", "no-cache");
//        response.setContentType("image/jpeg");
//
//        // 生成验证码文本
//        String capText = captchaProducer.createText();
//        String UUId = UUID.randomUUID().toString();
//        response.setHeader("Uuid", UUId);
//        // 将验证码文本存入Redis中，以便后续验证
////        response.get().setAttribute("captchaCode", capText);
//        redisTemplate.opsForValue().set(UUId,capText,60, TimeUnit.SECONDS);
//
//        // 生成验证码图片
//        BufferedImage bi = captchaProducer.createImage(capText);
//        ImageIO.write(bi, "jpg", response.getOutputStream());
//
////        return Result.success(null);
//        //将UUID设置为相应头
//
//    }

    @GetMapping("/captcha")
    public Result<CaptchaVO> getCaptcha() throws IOException {
        log.info("生成验证码--");
        //生成验证码code
        String code = generateCaptchaCode();
        //转小写
        code = code.toLowerCase();

        //生成图片
        System.setProperty("java.awt.headless", "true");
        String url = generateCaptchaImage(code);
        String uuid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(uuid, code, 30, TimeUnit.MINUTES);
        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setUuid(uuid);
        captchaVO.setCaptchaImage(url);
        return Result.success(captchaVO);
    }



    @PostMapping("/validateCaptcha")
    public Result<String > verifyCaptcha(@RequestBody CaptchaDTO captchaDTO, HttpServletRequest request) {

        String uuid = captchaDTO.getUuid();
        //转小写
        String code = captchaDTO.getCode();
        code = code.toLowerCase();

        String redisCode = redisTemplate.opsForValue().get(uuid);
        if(redisCode==null || redisCode.equals("")){
            return Result.success("验证码过期了");//为了适应服务器不能加载图片，所以只能全部放行了
        }
        if(code.equals(redisCode)) {
            return Result.success(null);
        }


        return Result.success("验证码错误");
    }


    private static final int WIDTH = 150;
    private static final int HEIGHT = 50;
    private static final int CODE_LENGTH = 6;
    private static final String CHAR_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";


    private static String generateCaptchaCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(CHAR_STRING.charAt(random.nextInt(CHAR_STRING.length())));
        }
        return code.toString();
    }

    private static String generateCaptchaImage(String captchaCode) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        // Set background color and fill the image
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw random lines for interference
        Random random = new Random();
        g2d.setColor(Color.BLACK);
        for (int i = 0; i < 15; i++) {
            int x1 = random.nextInt(WIDTH);
            int y1 = random.nextInt(HEIGHT);
            int x2 = random.nextInt(WIDTH);
            int y2 = random.nextInt(HEIGHT);
            g2d.drawLine(x1, y1, x2, y2);
        }

        // Draw the captcha code
        g2d.setFont(new Font("Arial", Font.BOLD, 40));
        g2d.setColor(Color.BLACK);
        g2d.drawString(captchaCode, 10, 40);

        g2d.dispose();

        // Convert the image to a byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        byte[] imageBytes = baos.toByteArray();

        // Encode the byte array to Base64
        return Base64.getEncoder().encodeToString(imageBytes);
    }

}
