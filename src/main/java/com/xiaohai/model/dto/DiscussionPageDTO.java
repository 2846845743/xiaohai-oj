package com.xiaohai.model.dto;

import lombok.Data;

//讨论分页查询传输对象
@Data
public class DiscussionPageDTO {

    private Integer page=1;//默认第一页
    private Integer size=30;//默认查30条
    private Integer questionNumber;//可选项，如果有则根据这个查，否则不


}
