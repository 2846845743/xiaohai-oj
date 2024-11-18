package com.xiaohai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.xiaohai.model.dto.RankDTO;
import com.xiaohai.model.dto.StatusPageQueryDTO;
import com.xiaohai.model.po.QuestionSubmit;
import com.xiaohai.model.vo.RankVO;
import com.xiaohai.model.vo.StatusPageQueryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
//提交记录表操作
public interface QuestionSubmitMapper extends BaseMapper<QuestionSubmit> {
    //状态分页查询
    Page<StatusPageQueryVO> pageQuery(StatusPageQueryDTO statusPageQueryDTO);
    //用户通过数分页查询
    List<RankVO> queryRankPage(int pageSize,int offset);

    @Select("select count(*) from question_submit where user_id = #{id} and result = 1")
    int queryPassNumberById(Integer id);
    @Select("select count(*) from question_submit where user_id = #{id} and result = 3")
    int queryWrongNumberById(Integer id);
    @Select("select count(*) from question_submit where user_id = #{id}")
    int querySubmitNumberById(Integer id);
    @Select("select distinct(question_number) from  question_submit where user_id = #{id}")
    List<Integer> queryPassQuestions(Integer id);

    @Select("select id from question_submit ")
    List<Integer> queryAllIds();
    @Select("select * from question_submit where id = #{number}")
    QuestionSubmit queryById(Integer number);

    @Select("select count(*) from question_submit where question_number = #{number} and result =1")
    int queryPassNumberByNumber(Integer number);
    @Select("select count(*) from question_submit where question_number = #{number}")
    int querySubmitNumberByNumber(Integer number);
}
