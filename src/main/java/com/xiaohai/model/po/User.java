package com.xiaohai.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class User {


   @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("user_name")
    private String username;
    private String password;
    private String email;
    @TableField("avatar_url")
    private String avatarUrl;
    private int role;//角色枚举，0为普通用户，1是管理员
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;

}
