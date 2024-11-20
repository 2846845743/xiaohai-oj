package com.xiaohai.service;

import com.xiaohai.model.dto.DiscussSaveDTO;
import com.xiaohai.model.dto.DiscussionPageDTO;
import com.xiaohai.model.po.Discussion;
import com.xiaohai.utils.PageResult;
import com.xiaohai.utils.Result;
import org.springframework.stereotype.Service;

@Service
public interface DiscussionService {

    void save(DiscussSaveDTO discussSaveDTO);

    PageResult page(DiscussionPageDTO discussionPageDTO);

    Result<Discussion> getById(Long id);
}
