package com.xiaohai.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//状态分页查询的DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusPageQueryDTO {
    //作者姓名
    private String username;
    //题目编号
    private int questionNumber;
    //页码
    private  int page = 1;
    //每页条数
    private  int pageSize = 50;

}
