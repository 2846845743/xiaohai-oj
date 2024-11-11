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
}
