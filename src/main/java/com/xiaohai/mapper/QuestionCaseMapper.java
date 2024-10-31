package com.xiaohai.mapper;

import com.xiaohai.model.po.QuestionCase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionCaseMapper {

    //根据题目编号查询所有QuestionCase

    @Select("select * from question_case where question_number = #{questionNum}")
    List<QuestionCase> selectByQuestionNum(Integer questionNum);
}
