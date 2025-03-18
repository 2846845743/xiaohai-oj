package com.xiaohai.controller;


import com.xiaohai.mapper.TypeMapper;
import com.xiaohai.model.dto.QuestionDTO;
import com.xiaohai.model.dto.QuestionPageQueryDTO;
import com.xiaohai.model.dto.QuestionSaveDTO;
import com.xiaohai.model.po.Question;
import com.xiaohai.service.QuestionService;
import com.xiaohai.utils.PageResult;
import com.xiaohai.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//题目相关控制器
@RestController
@RequestMapping("/question")
@Slf4j
@CrossOrigin
@Api(tags = "题目接口", value = "题目接口")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private TypeMapper typeMapper;

    //查看所有标签
    @ApiOperation("查看所有标签")
    @GetMapping("/types")
    public Result types() {
        log.info("正在查看所有标签名称");
        List<String> strings =
                typeMapper.selectAllType();
        return Result.success(strings);
    }

//    @PostMapping("/add")
//    public Result add(@RequestBody QuestionDTO question) {
//        //新增问题
//        log.info("新增题目：{}",question);
//        return questionService.addQuestion(question);
//    }

    @ApiOperation("根据编号获取问题详情")
    @GetMapping("/{number}")
    public Result get(@PathVariable Integer number) {
        log.info("根据编号获取问题详情：{}",number);
        return questionService.getByNumber(number);
    }

    @ApiOperation("题目大全分页搜索")
    @GetMapping("/page")
    public Result<PageResult> page(QuestionPageQueryDTO questionPQDto) {
        log.info("题目大全分页搜索：{}",questionPQDto);
        PageResult pageResult =  questionService.pageQuery3(questionPQDto);
        return Result.success(pageResult);
    }

    @ApiOperation("新增题目")
    @PostMapping("/save")
    public Result add(@RequestBody QuestionSaveDTO questionSaveDTO) {
        log.info("新增题目-{}",questionSaveDTO);
        questionService.saveQuestion(questionSaveDTO);
        return Result.success(null);
    }

}
