package com.xiaohai.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class QuestionSaveDTO {
    //新增题目传输对象
    private int number;
    private String  title;//标题
    private String  description;//题目正文
    private int  limitTime;
    private int  limitMemory;
    private String inputDesc;
    private String outputDesc;//输入输出描述
//    private Map<String,String> testCase;//输入输出用例;
    private List<String> inputList;
    private List<String> outputList;
    private String typeList;//标签，以逗号分割。
    private Date createTime;
    private Date updateTime;
    private int  createUser;
    private int  updateUser;
}
