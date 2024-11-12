package com.xiaohai.mapper;

import com.xiaohai.model.po.QuestionCase;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionCaseMapper {

    //根据题目编号查询所有QuestionCase

    @Select("select * from question_case where question_number = #{questionNum}")
    List<QuestionCase> selectByQuestionNum(Integer questionNum);

    @Insert("insert into question_case (question_number,input_list,output_list) " +
            "values (#{number} , #{inputCase},#{outputCase})")
    void insert(int number, String inputCase, String outputCase);
}
