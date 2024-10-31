package com.xiaohai.service.impl;

import com.xiaohai.model.dto.ExecuteCodeRequest;
import com.xiaohai.model.dto.ExecuteCodeResponse;
import com.xiaohai.utils.JavaCodeSandboxTemplate;
import org.springframework.stereotype.Component;

/**
 * 原生Java代码沙箱 - 实现模板方法
 *
 * @author Shier 2023/9/4 17:18
 */
@Component
public class JavaNativeCodeSandbox extends JavaCodeSandboxTemplate {


    /**
     * 执行程序
     * @param executeCodeRequest
     * @return
     */
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        return super.executeCode(executeCodeRequest);
    }
}
