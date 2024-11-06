package com.xiaohai.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionPageQueryVO {
    //1.题号
    private int questionNumber;
    //2.标题
    private String  questionTitle;
    //todo 3.标题标签，暂时不管

    //4.已通过的记录数
    private int passNumber = 0;
    //5.题目提交总数
    private  int submitNumber = 0;

    private int hasUserPassed;
}
