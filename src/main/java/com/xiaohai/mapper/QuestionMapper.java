package com.xiaohai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.xiaohai.model.po.Question;
import com.xiaohai.model.vo.QuestionDetail;
import com.xiaohai.model.vo.QuestionPageQueryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
    //根据题目标题模糊查询
    Page<QuestionPageQueryVO> queryPage(String title,Integer userId);


    @Select("\n" +
            "SELECT\n" +
            "    q.number as questionNumber,\n" +
            "    q.description,\n" +
            "    q.title,q.input_desc as inputDesc ,q.output_desc as outputDesc ,\n " +
            "    q.limit_memory,\n" +
            "    q.limit_time,\n" +
            "    qs.submitNumber,\n" +
            "    qs.passNumber,\n" +
            "    qc.input_list,\n" +
            "    qc.output_list\n" +
            "FROM\n" +
            "    question q\n" +
            "INNER JOIN (\n" +
            "    SELECT\n" +
            "        question_number,\n" +
            "        COUNT(*) AS submitNumber,\n" +
            "        SUM(CASE WHEN result = 1 THEN 1 ELSE 0 END) AS passNumber\n" +
            "    FROM\n" +
            "        question_submit\n" +
            "    GROUP BY\n" +
            "        question_number\n" +
            ") qs ON q.number = qs.question_number\n" +
            "LEFT JOIN (\n" +
            "    SELECT\n" +
            "        question_number,\n" +
            "        MIN(id) AS first_case_id\n" +
            "    FROM\n" +
            "        question_case\n" +
            "    GROUP BY\n" +
            "        question_number\n" +
            ") qc_min ON q.number = qc_min.question_number\n" +
            "LEFT JOIN question_case qc ON qc.id = qc_min.first_case_id where q.number=#{number};")
    QuestionDetail getQuestionByNumber(Integer number);


    @Select("SELECT t.name\n" +
            "FROM question q\n" +
            "JOIN question_type qt ON q.number = qt.question_number\n" +
            "JOIN type t ON qt.type_id = t.id\n" +
            "WHERE q.number = #{questionNumber};\n")
    List<String> selectTypeNameByQuestionNumber(int questionNumber);
}
