package com.xiaohai.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class QuestionSubmit {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private int runRecord;//运行记录
    private Integer userId;
    private int questionNumber;
    private int result;
    private int runTime=0;
    private int runMemory = 100;
    private String code;//代码
    private Date createTime;

}
