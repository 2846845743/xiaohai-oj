package com.xiaohai.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaohai.mapper.UserMapper;
import com.xiaohai.model.po.User;
import com.xiaohai.utils.UserHolder;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@RestController
@Api(tags = "上传接口", value = "上传接口")
public class UploadController {
    @Autowired
    private UserMapper userMapper;
    @Value("${minio.bucketName}")
    private String bucketName;
    @Autowired
    private MinioClient minioClient;

    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        Integer userId = UserHolder.getUser().getId();
        log.info("上传文件:{},{}", file, userId);
        try {
            // 获取当前日期并格式化为yyMMdd
            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            // 生成UUID
            String uniqueID = UUID.randomUUID().toString();
            // 获取文件后缀
            String originalFilename = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            // 组合新的文件名
            String newFileName = currentDate + "_" + uniqueID + fileExtension;
            User dbUser = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId, userId));
            dbUser.setAvatarUrl("117.72.91.159:8999/user01/"+newFileName);
            userMapper.update(dbUser, new LambdaQueryWrapper<User>().eq(User::getId, userId));

            InputStream inputStream = file.getInputStream();
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(newFileName)
                            .stream(inputStream, inputStream.available(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
            //tODO 应该返回图片URL
            return dbUser.getAvatarUrl();
        } catch (MinioException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            return "Error uploading file to MinIO: " + e.getMessage();
        }
    }

}
