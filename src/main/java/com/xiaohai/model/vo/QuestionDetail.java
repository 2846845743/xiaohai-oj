package com.xiaohai.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

//问题详情页展示
@Data
public class QuestionDetail {

    @TableField("number")
    private int questionNumber;
    private String description;
    private String title;
    private int limitTime;
    private int limitMemory;
    private int passNumber;
    private int submitNumber;
    private String inputList;
    private String outputList;

}
