package com.xiaohai.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {
    private String  title;
    private int  number;
    private List<String>  inputList;
    private List<String > outputList;
    private String  description;
    private int  limitTime;
    private int  limitMemory;
}
