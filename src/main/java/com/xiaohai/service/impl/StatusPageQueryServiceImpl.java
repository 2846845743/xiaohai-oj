package com.xiaohai.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaohai.mapper.QuestionSubmitMapper;
import com.xiaohai.model.dto.StatusPageQueryDTO;
import com.xiaohai.model.po.QuestionSubmit;
import com.xiaohai.model.vo.StatusPageQueryVO;
import com.xiaohai.service.StatusPageQueryService;
import com.xiaohai.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusPageQueryServiceImpl implements StatusPageQueryService {

    @Autowired
    private QuestionSubmitMapper questionSubmitMapper;
    /**
     * 提交记录的分页查询
     * @param statusPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(StatusPageQueryDTO statusPageQueryDTO) {
        //开启分页查询
        PageHelper.startPage(statusPageQueryDTO.getPage(),statusPageQueryDTO.getPageSize());

        Page<StatusPageQueryVO> page = questionSubmitMapper.pageQuery(statusPageQueryDTO);

        return new PageResult(page.getTotal(),page.getResult());
    }
}
