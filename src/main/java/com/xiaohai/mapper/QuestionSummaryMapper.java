package com.xiaohai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.xiaohai.model.dto.QuestionPageQueryDTO;
import com.xiaohai.model.vo.QuestionPageQueryVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionSummaryMapper extends BaseMapper<QuestionPageQueryVO> {


    List<QuestionPageQueryVO> queryByPage(QuestionPageQueryDTO questionPageQueryDTO);

    @Insert("insert into question_summary values (" +
            "#{questionNumber},\n" +
            "#{questionTitle},\n" +
            "#{passNumber},\n" +
            "#{submitNumber},\n" +
            "#{hasUserPassed}" +
            ")")
    void insertOne(QuestionPageQueryVO questionPageQueryVO);

    @Select("select * from question_summary")
    Page<QuestionPageQueryVO> queryPage(String title, Integer userId);

    @Select("select count(*) from question_submit where user_id = #{id} and result =1")
    int queryPassNumberById(Integer id);

    @Select("select count(*) from question_submit where user_id = #{id} and result =3")
    int queryWrongNumberById(Integer id);

    @Select("select count(*) from question_submit where user_id = #{id}")
    int querySubmitNumberById(Integer id);

    @Select("select distinct question_number from question_submit where user_id =#{id}")
    List<Integer> queryPassQuestions(Integer id);
}
