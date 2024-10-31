package com.xiaohai.model.dto;

import lombok.Data;

/**
 * 进程执行信息
 *
 * @author Shier 2023/9/1 14:39
 */
@Data
public class ExecuteMessage {

    private Integer exitValue;

    private String ouput;

    private String errorMessage;

    private Long time;

    private Long memory;

}
