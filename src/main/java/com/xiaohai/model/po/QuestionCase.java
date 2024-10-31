package com.xiaohai.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class QuestionCase {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("question_number")
    private int questionNumber;
    @TableField("input_list")
    private String inputList;

    @TableField("output_list")
    private String outputList;

}
