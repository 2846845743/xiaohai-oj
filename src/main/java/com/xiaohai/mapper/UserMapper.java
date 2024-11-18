package com.xiaohai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaohai.model.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where  id = #{id}")
    User getById(Integer id);
    @Select("select user_name from user where id = #{id};")
    String queryNameById(Integer userId);
}
