package com.xiaohai.model.dto;

import lombok.Data;

@Data
public class QuestionDTO {
    private String  title;
    private int  number;
    private String  inputList;
    private String  outputList;
    private String  description;
    private int  limitTime;
    private int  limitMemory;
}
