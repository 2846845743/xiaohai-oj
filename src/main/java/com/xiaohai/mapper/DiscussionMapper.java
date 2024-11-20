package com.xiaohai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaohai.model.dto.DiscussionPageDTO;
import com.xiaohai.model.po.Discussion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DiscussionMapper extends BaseMapper<Discussion> {



    List<Discussion> queryPage(DiscussionPageDTO discussionPageDTO);
}
