package com.xiaohai.controller;



import com.xiaohai.model.dto.StatusPageQueryDTO;
import com.xiaohai.service.StatusPageQueryService;
import com.xiaohai.utils.PageResult;
import com.xiaohai.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
//用户提交记录控制器
@RequestMapping("status")
@RestController
@Api(tags = "用户提交记录接口", value = "用户提交记录接口")
public class StatusController {
    @Autowired
    private StatusPageQueryService statusPageQueryService;

    @ApiOperation("分页查询用户提交记录")
    @GetMapping("/page")
    public Result<PageResult> page(StatusPageQueryDTO statusPageQueryDTO){
      log.info("statusPageQueryDTO:{}", statusPageQueryDTO);
      PageResult pageResult =statusPageQueryService.pageQuery(statusPageQueryDTO);
      return Result.success(pageResult);
    }
}
