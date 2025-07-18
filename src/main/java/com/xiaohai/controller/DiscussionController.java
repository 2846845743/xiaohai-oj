package com.xiaohai.controller;

import com.xiaohai.model.dto.DiscussSaveDTO;
import com.xiaohai.model.dto.DiscussionPageDTO;
import com.xiaohai.model.po.Discussion;
import com.xiaohai.service.DiscussionService;
import com.xiaohai.utils.PageResult;
import com.xiaohai.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//讨论api

@RestController
@RequestMapping("/discuss")
@Api(tags = "讨论接口",value = "讨论接口")
@Slf4j
public class DiscussionController {

    @Autowired
    private DiscussionService discussionService;

    @ApiOperation("提交讨论")
    @PostMapping("/save")
    public Result save(@RequestBody DiscussSaveDTO discussSaveDTO){
        //提交讨论
        log.info("提交讨论：{}",discussSaveDTO);
        discussionService.save(discussSaveDTO);
        return Result.success("提交成功");
    }

    @ApiOperation("讨论分页查询")
    @GetMapping("/page")
    public Result<PageResult> page(DiscussionPageDTO discussionPageDTO){
        log.info("讨论分页查询：{}",discussionPageDTO);
        PageResult pageResult =  discussionService.page(discussionPageDTO);
        return Result.success(pageResult);
    }


    //根据id查询讨论详情
    @ApiOperation("根据id查询讨论详情")
    @GetMapping("/{id}")
    public Result<Discussion> getById(@PathVariable("id") Long id){
        log.info("根据id查询讨论详情,{}",id);
        return discussionService.getById(id);
    }
}
