package com.xiaohai.service;

import com.xiaohai.model.dto.StatusPageQueryDTO;
import com.xiaohai.utils.PageResult;

public interface StatusPageQueryService {

    PageResult pageQuery(StatusPageQueryDTO statusPageQueryDTO);
    PageResult pageQuery2(StatusPageQueryDTO statusPageQueryDTO);
}
