package com.xiaohai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.xiaohai.model.dto.RankDTO;
import com.xiaohai.model.dto.StatusPageQueryDTO;
import com.xiaohai.model.po.QuestionSubmit;
import com.xiaohai.model.vo.RankVO;
import com.xiaohai.model.vo.StatusPageQueryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
//提交记录表操作
public interface QuestionSubmitMapper extends BaseMapper<QuestionSubmit> {
    //状态分页查询
    Page<StatusPageQueryVO> pageQuery(StatusPageQueryDTO statusPageQueryDTO);
    //用户通过数分页查询
    List<RankVO> queryRankPage(int pageSize,int offset);
}
