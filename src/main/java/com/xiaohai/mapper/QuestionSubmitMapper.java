package com.xiaohai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaohai.model.po.QuestionSubmit;
import org.apache.ibatis.annotations.Mapper;

@Mapper
//提交记录表操作
public interface QuestionSubmitMapper extends BaseMapper<QuestionSubmit> {
}
