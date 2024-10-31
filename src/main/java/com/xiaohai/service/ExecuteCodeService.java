package com.xiaohai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaohai.model.dto.ExecuteCodeDTO;
import com.xiaohai.utils.Result;

public interface ExecuteCodeService{


    Result executeCode(ExecuteCodeDTO code);
}
