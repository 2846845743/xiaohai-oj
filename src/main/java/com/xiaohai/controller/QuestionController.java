package com.xiaohai.controller;


import com.xiaohai.model.dto.QuestionDTO;
import com.xiaohai.model.dto.QuestionPageQueryDTO;
import com.xiaohai.model.po.Question;
import com.xiaohai.service.QuestionService;
import com.xiaohai.utils.PageResult;
import com.xiaohai.utils.Result;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//题目相关控制器
@RestController
@RequestMapping("/question")
@Slf4j
@CrossOrigin
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/add")
    public Result add(@RequestBody QuestionDTO question) {
        //新增问题
        log.info("新增题目：{}",question);
        return questionService.addQuestion(question);
    }

    @GetMapping("/{number}")
    public Result get(@PathVariable Integer number) {
        log.info("根据编号获取问题详情：{}",number);
        return questionService.getByNumber(number);
    }

    @GetMapping("/page")
    public Result<PageResult> page(QuestionPageQueryDTO questionPQDto) {
        log.info("题目大全分页搜索：{}",questionPQDto);
        PageResult pageResult =  questionService.pageQuery(questionPQDto);
        return Result.success(pageResult);
    }
}
