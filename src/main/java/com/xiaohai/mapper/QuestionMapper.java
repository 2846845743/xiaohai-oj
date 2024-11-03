package com.xiaohai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.xiaohai.model.po.Question;
import com.xiaohai.model.vo.QuestionPageQueryVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
    //根据题目标题模糊查询
    Page<QuestionPageQueryVO> queryPage(String title);
}
