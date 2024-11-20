package com.xiaohai.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@TableName("discussions")
@Data
public class Discussion {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String content;
    private Integer questionNumber;
    @TableField("create_user")
    @JsonIgnore//不返回给前端
    private Integer userId;
    private Date createTime;

    @TableField(exist = false)
    private String username;
}
