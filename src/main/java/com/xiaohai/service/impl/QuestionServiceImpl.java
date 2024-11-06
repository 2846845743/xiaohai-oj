package com.xiaohai.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaohai.mapper.QuestionMapper;
import com.xiaohai.model.dto.QuestionDTO;
import com.xiaohai.model.dto.QuestionPageQueryDTO;
import com.xiaohai.model.po.Question;
import com.xiaohai.model.vo.QuestionDetail;
import com.xiaohai.model.vo.QuestionPageQueryVO;
import com.xiaohai.service.QuestionService;
import com.xiaohai.utils.PageResult;
import com.xiaohai.utils.Result;
import com.xiaohai.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public boolean saveBatch(Collection<Question> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Question> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Question> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Question entity) {
        return false;
    }

    @Override
    public Question getOne(Wrapper<Question> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Question> queryWrapper) {
        return Collections.emptyMap();
    }

    @Override
    public <V> V getObj(Wrapper<Question> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<Question> getBaseMapper() {
        return null;
    }

    @Override
    public Class<Question> getEntityClass() {
        return null;
    }

    /**
     * 新增题目，应该直接插入就完事了
     * @param question
     * @return
     */
    @Override
    public Result addQuestion(QuestionDTO question) {
        //判断题目是否存在
        int number = question.getNumber();
        LambdaQueryWrapper<Question> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(Question::getId).eq(Question::getNumber, number);
        Question dbQuestion = questionMapper.selectOne(lambdaQueryWrapper);
        if(null != dbQuestion){
            //已经有相同编号题目
            return Result.fail("已有相同题目了！");
        }

        //todo 还没设置UserLocal
        Question questionEntity = BeanUtil.copyProperties(question, Question.class);

        questionEntity.setCreateUser(1);
        questionEntity.setUpdateUser(1);
        questionEntity.setCreateTime(new Date());
        questionEntity.setUpdateTime(new Date());

        questionMapper.insert(questionEntity);
        return Result.success(null);
    }

    @Override
    public Result getByNumber(Integer number) {
        //TODO 根据题目number信息，返回题目详情信息，包括题目描述，题目标题，题目number，输入输出样例，时限，内存限制，总提交数，通过数
        QuestionDetail questionDetail =  questionMapper.getQuestionByNumber(number);
        return  Result.success(questionDetail);
    }

    @Override
    public PageResult pageQuery(QuestionPageQueryDTO questionPQDto) {
        //根据title模糊查询
        PageHelper.startPage(questionPQDto.getPage(), questionPQDto.getPageSize());
        Integer userId = UserHolder.getUser().getId();
        Page<QuestionPageQueryVO> page = questionMapper.queryPage(questionPQDto.getTitle(),userId);
        return new PageResult(page.getTotal(),page.getResult());
    }
}
