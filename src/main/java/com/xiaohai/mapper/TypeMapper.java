package com.xiaohai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaohai.model.po.Type;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TypeMapper extends BaseMapper<Type> {
    @Select("select name from type;")
    List<String> selectAllType();

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into ")
    void saveWithQuestion();

    @Insert("insert into question_type values (null,#{number},#{typeId},now(),#{userId})")
    void insertWithQuestionNumber(int number, int typeId,int userId);
}
