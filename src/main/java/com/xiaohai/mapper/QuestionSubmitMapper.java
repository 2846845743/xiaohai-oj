package com.xiaohai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.xiaohai.model.dto.StatusPageQueryDTO;
import com.xiaohai.model.po.QuestionSubmit;
import com.xiaohai.model.vo.StatusPageQueryVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
//提交记录表操作
public interface QuestionSubmitMapper extends BaseMapper<QuestionSubmit> {

    Page<StatusPageQueryVO> pageQuery(StatusPageQueryDTO statusPageQueryDTO);
}
