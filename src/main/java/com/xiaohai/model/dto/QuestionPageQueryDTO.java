package com.xiaohai.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionPageQueryDTO {
    //根据标题模糊查询
    private String title;
    //页码
    private  int page = 1;
    //每页条数
    private  int pageSize = 50;
}
