package com.xiaohai.controller;

import com.xiaohai.model.dto.ExecuteCodeDTO;
import com.xiaohai.service.ExecuteCodeService;
import com.xiaohai.service.impl.JavaNativeCodeSandbox;
import com.xiaohai.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin
@Api(tags = "执行代码接口", value = "执行代码接口")
public class ExecuteCodeController {

    @Autowired
   private ExecuteCodeService executeCodeService;

    //执行代码接口
    @ApiOperation("执行代码")
    @PostMapping("/executeCode")
    public Result executeCode(@RequestBody ExecuteCodeDTO executeCodeDTO) {
        log.info("执行代码：{}",executeCodeDTO);
        Result result = executeCodeService.executeCode(executeCodeDTO);
        return result;
    }

}
