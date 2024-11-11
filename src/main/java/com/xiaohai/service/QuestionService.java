package com.xiaohai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaohai.model.dto.QuestionDTO;
import com.xiaohai.model.dto.QuestionPageQueryDTO;
import com.xiaohai.model.po.Question;
import com.xiaohai.utils.PageResult;
import com.xiaohai.utils.Result;

public interface QuestionService extends IService<Question> {
    PageResult pageQuery2(QuestionPageQueryDTO questionPQDto);
    Result addQuestion(QuestionDTO question);

    Result getByNumber(Integer number);

    PageResult pageQuery(QuestionPageQueryDTO questionPQDto);
}
