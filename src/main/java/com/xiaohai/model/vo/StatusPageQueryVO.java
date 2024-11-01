package com.xiaohai.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusPageQueryVO {
    private int runRecord;//运行记录号
    private String username;//作者名称
    private String questionInfo;//题号加标题
    private int runTime;//耗时
    private int result;//结果
    private int runMemory;//内存
    private int length;//代码长度
    private Date createTime;//提交日期距离今天的天数
}
