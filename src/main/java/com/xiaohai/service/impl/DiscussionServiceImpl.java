package com.xiaohai.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiaohai.mapper.DiscussionMapper;
import com.xiaohai.mapper.QuestionMapper;
import com.xiaohai.mapper.UserMapper;
import com.xiaohai.model.dto.DiscussSaveDTO;
import com.xiaohai.model.dto.DiscussionPageDTO;
import com.xiaohai.model.po.Discussion;
import com.xiaohai.model.po.Question;
import com.xiaohai.model.po.User;
import com.xiaohai.service.DiscussionService;
import com.xiaohai.utils.PageResult;
import com.xiaohai.utils.Result;
import com.xiaohai.utils.UserHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DiscussionServiceImpl implements DiscussionService {

    @Autowired
    private DiscussionMapper discussionMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 保存讨论信息
     * @param discussSaveDTO
     */
    @Override
    public void save(DiscussSaveDTO discussSaveDTO) {
        if(discussSaveDTO==null)throw new RuntimeException("系统异常-discussionDTO表单为空");
        Integer questionNumber = discussSaveDTO.getQuestionNumber();
        String discussionTitle = discussSaveDTO.getDiscussionTitle();
        String discussionContent = discussSaveDTO.getDiscussionContent();
        if(questionNumber==null || StringUtils.isBlank(discussionTitle)){
            throw new RuntimeException("参数异常");
        }
        //直接插入数据
        Discussion discussion = new Discussion();
        discussion.setTitle(discussionTitle);
        discussion.setContent(discussionContent);
        discussion.setQuestionNumber(questionNumber);
        discussion.setCreateTime(new Date());
        discussion.setUserId(UserHolder.getUser().getId());
        int insert = discussionMapper.insert(discussion);//插入
        if(insert<=0){
            throw new RuntimeException("数据插入失败-检查sql");
        }

    }

    @Override
    public PageResult page(DiscussionPageDTO discussionPageDTO) {
        PageHelper.startPage(discussionPageDTO.getPage(),discussionPageDTO.getSize());
        List<Discussion> discussionList =  discussionMapper.queryPage(discussionPageDTO);
        //根据id查询名字
        for(Discussion discussion:discussionList){
            User byId = userMapper.getById(discussion.getUserId());
            discussion.setUsername(byId.getUsername());
        }
        return new PageResult(discussionList.size(),discussionList);
    }

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public Result<Discussion> getById(Long id) {
        Discussion discussion = discussionMapper.selectById(id);
        User byId = userMapper.getById(discussion.getUserId());
        discussion.setUsername(byId.getUsername());
        Question question = questionMapper.getNumberAndTitleByNumber(discussion.getQuestionNumber());
        discussion.setQuestionTitle(question.getTitle());
        return Result.success(discussion);
    }
}
