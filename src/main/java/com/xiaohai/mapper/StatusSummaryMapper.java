package com.xiaohai.mapper;

import com.github.pagehelper.Page;
import com.xiaohai.model.dto.StatusPageQueryDTO;
import com.xiaohai.model.vo.StatusPageQueryVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StatusSummaryMapper {

    @Insert("insert into status_summary values (" +
            "#{runRecord},\n" +
            "#{username},\n" +
            "#{questionInfo},\n" +
            "#{runTime},\n" +
            "#{result},\n" +
            "#{runMemory},\n" +
            "#{length},\n" +
            "#{createTime}" +
            "" +
            ")")
    void insert(StatusPageQueryVO statusPageQueryVO);



    Page<StatusPageQueryVO> pageQuery(StatusPageQueryDTO statusPageQueryDTO);
}
