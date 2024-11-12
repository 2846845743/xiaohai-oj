package com.xiaohai.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Question {

    @TableId(type = IdType.AUTO)
    private Integer  id;
    private String  title;
    private int  number;

    private String inputDesc;
    private String outputDesc;//输入输出描述
    private int  isDelete;
    private String  description;
    @TableField("total_commit_num")
    private int  totalCommitNum;
    @TableField("pass_num")
    private int  passNum;
    @TableField("limit_time")
    private int  limitTime;
    @TableField("limit_memory")
    private int  limitMemory;

    @TableField("create_time")
    private Date  createTime;
    @TableField("update_time")
    private Date updateTime;
    @TableField("create_user")
    private int  createUser;
    @TableField("update_user")
    private int  updateUser;

}
