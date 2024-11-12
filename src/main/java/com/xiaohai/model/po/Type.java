package com.xiaohai.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Type {

    @TableId(type = IdType.AUTO)
    private int id;
    private String name;
}
