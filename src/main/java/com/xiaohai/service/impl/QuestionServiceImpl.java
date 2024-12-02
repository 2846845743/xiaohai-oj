package com.xiaohai.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaohai.mapper.QuestionCaseMapper;
import com.xiaohai.mapper.QuestionMapper;
import com.xiaohai.mapper.QuestionSubmitMapper;
import com.xiaohai.mapper.TypeMapper;
import com.xiaohai.model.dto.QuestionDTO;
import com.xiaohai.model.dto.QuestionPageQueryDTO;
import com.xiaohai.model.dto.QuestionSaveDTO;
import com.xiaohai.model.po.Question;
import com.xiaohai.model.po.QuestionCase;
import com.xiaohai.model.po.Type;
import com.xiaohai.model.vo.QuestionDetail;
import com.xiaohai.model.vo.QuestionPageQueryVO;
import com.xiaohai.service.QuestionService;
import com.xiaohai.utils.PageResult;
import com.xiaohai.utils.Result;
import com.xiaohai.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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
        //1.插入基本信息
        Question questionEntity = BeanUtil.copyProperties(question, Question.class);
        questionEntity.setDescription(question.getDescription());
        questionEntity.setCreateUser(UserHolder.getUser().getId());
        questionEntity.setUpdateUser(UserHolder.getUser().getId());
        questionEntity.setCreateTime(new Date());
        questionEntity.setUpdateTime(new Date());
        List<String> inputList = question.getInputList();
        List<String> outputList = question.getOutputList();
        questionMapper.insert(questionEntity);
        //2.插入输入输出信息
        for(int i=0;i<question.getInputList().size();i++){
            questionCaseMapper.insert(question.getNumber(),inputList.get(i),outputList.get(i));
        }
        //插入标签信息
        //分割出所有标签
        String typeList = question.getTypeList();
        String[] types = typeList.split(",");
        //查找已有所有标签
        List<String> dbType = typeMapper.selectAllType();
        for(String type:types){
            if(!dbType.contains(type)){
                Type typeEntity = new Type();
                typeEntity.setName(type);
                //db里面不含有这个type则添加
                typeMapper.insert(typeEntity);
            }
        }

        return Result.success(null);
    }

    @Autowired
    private QuestionSubmitMapper questionSubmitMapper;

    @Override
    public Result getByNumber(Integer number) {
        //TODO 根据题目number信息，返回题目详情信息，包括题目描述，题目标题，题目number，输入输出样例，时限，内存限制，总提交数，通过数
//        QuestionDetail questionDetail =  questionMapper.getQuestionByNumber(number);
        QuestionDetail questionDetail = new QuestionDetail();
        questionDetail.setQuestionNumber(number);
        //根据题号获取基本信息
        Question question =  questionMapper.queryQuestion(number);
        questionDetail.setDescription(question.getDescription());
        questionDetail.setInputDesc(question.getInputDesc());
        questionDetail.setOutputDesc(question.getOutputDesc());
        questionDetail.setTitle(question.getTitle());
        questionDetail.setLimitMemory(question.getLimitMemory());
        questionDetail.setLimitTime(question.getLimitTime());
        //获取提交数量
        int passCount = questionSubmitMapper.queryPassNumberByNumber(number);
        int submitNumber = questionSubmitMapper.querySubmitNumberByNumber(number);
        questionDetail.setPassNumber(passCount);questionDetail.setSubmitNumber(submitNumber);
        //获取1个输入输出列表
        List<QuestionCase> questionCases = questionCaseMapper.selectByQuestionNum(number);
        if(questionCases!=null && questionCases.size()>0){
            QuestionCase questionCase = questionCases.get(0);
            questionDetail.setInputList(questionCase.getInputList());
            questionDetail.setOutputList(questionCase.getOutputList());
        }

        return  Result.success(questionDetail);
    }

    @Override
    public PageResult pageQuery(QuestionPageQueryDTO questionPQDto) {
//        先查询缓存
        String redisPage = stringRedisTemplate.opsForValue().get("question-1");
        if(redisPage!=null && questionPQDto.getPage()==1 && (questionPQDto.getTitle()==null || questionPQDto.getTitle().isEmpty())){
            //第一页并且有数据，则返回
            log.info("走redis了");
            return new PageResult(JSON.parseArray(redisPage,QuestionPageQueryVO.class).size(),JSON.parseArray(redisPage,QuestionPageQueryVO.class));
        }

        //根据title模糊查询
        PageHelper.startPage(questionPQDto.getPage(), questionPQDto.getPageSize());
        Integer userId = UserHolder.getUser().getId();
        Page<QuestionPageQueryVO> page = questionMapper.queryPage(questionPQDto.getTitle(),userId);
        List<QuestionPageQueryVO> result = page.getResult();//得到的结果没有标签集
        //根据题号number查询type类型名字，并拼接
        for (QuestionPageQueryVO questionPageQueryVO : result) {
            List<String> typeNames =  questionMapper.selectTypeNameByQuestionNumber(questionPageQueryVO.getQuestionNumber());
            log.info("查询到的分类：{}",typeNames);
            questionPageQueryVO.setTypeList(typeNames);
        }


        //如果第一页将查询到的结果存入redis
        if(questionPQDto.getPage()==1){
            stringRedisTemplate.opsForValue().set("question-1", JSON.toJSONString(page.getResult()));
        }
        return new PageResult(page.getTotal(),page.getResult());
    }



    //把标签也一起查出来
    public PageResult pageQuery3(QuestionPageQueryDTO questionPQDto) {
        log.info("走内存分开查题目");

        //根据title模糊查询
        PageHelper.startPage(questionPQDto.getPage(), questionPQDto.getPageSize());
        Integer userId = UserHolder.getUser().getId();
        Page<QuestionPageQueryVO> page = questionMapper.queryPage(questionPQDto.getTitle(),userId);
        List<QuestionPageQueryVO> result = page.getResult();//得到的结果没有标签集
        //根据题号number查询type类型名字，并拼接
        for (QuestionPageQueryVO questionPageQueryVO : result) {
            List<String> typeNames =  questionMapper.selectTypeNameByQuestionNumber(questionPageQueryVO.getQuestionNumber());
            log.info("查询到的分类：{}",typeNames);
            questionPageQueryVO.setTypeList(typeNames);
        }

        return new PageResult(page.getTotal(),page.getResult());
    }
    @Autowired
    private QuestionCaseMapper questionCaseMapper;
    @Override
    public void saveQuestion(QuestionSaveDTO questionSaveDTO) {
        //判断题目是否存在
        int number = questionSaveDTO.getNumber();
        LambdaQueryWrapper<Question> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(Question::getId).eq(Question::getNumber, number);
        Question dbQuestion = questionMapper.selectOne(lambdaQueryWrapper);
        if(null != dbQuestion){
            //已经有相同编号题目
            throw new RuntimeException("已有相同题目");
        }
        //1.插入基本信息
        questionSaveDTO.setCreateTime(new Date());
        questionSaveDTO.setUpdateTime(new Date());
        questionSaveDTO.setCreateUser(UserHolder.getUser()==null?8:UserHolder.getUser().getId());
        questionSaveDTO.setUpdateUser(UserHolder.getUser()==null?8:UserHolder.getUser().getId());
        Question questionEntity = BeanUtil.copyProperties(questionSaveDTO, Question.class);
        questionMapper.insert(questionEntity);
        //2.插入测试用例信息
        List<String> inputList = questionSaveDTO.getInputList();
        List<String> outputList = questionSaveDTO.getOutputList();
        for(int i=0;i<questionSaveDTO.getInputList().size();i++){
            questionCaseMapper.insert(questionSaveDTO.getNumber(),inputList.get(i),outputList.get(i));
        }

        //3.插入标签（type）信息，首先查询标签中所有名字，然后进行去重。
        String originTypeList = questionSaveDTO.getTypeList();
        if(originTypeList==null || originTypeList.isEmpty()){
            return;//不需要再插入了
        }

        String[] typeNames = originTypeList.split(",");
        List<String > dbType = typeMapper.selectAllType();
        List<Type> newTypeList = new ArrayList<>();
        //根据查询出来的dbType去重，把没有的加入。最后插入没有的
        for(String typeName : typeNames){
            if(!dbType.contains(typeName)){
                Type type = new Type();
                type.setName(typeName);
                newTypeList.add(type);
            }
        }
        for(Type newType : newTypeList){
            typeMapper.insert(newType);
            int typeId = newType.getId();
            //根据id插入question的关系
            typeMapper.insertWithQuestionNumber(number,typeId,UserHolder.getUser()==null?8:UserHolder.getUser().getId());
        }
    }
@Autowired
private TypeMapper typeMapper;




    public PageResult pageQuery2(QuestionPageQueryDTO questionPQDto) {

//        log.info("走新表优化");
//        //根据title模糊查询
//        PageHelper.startPage(questionPQDto.getPage(), questionPQDto.getPageSize());
//        Integer userId = UserHolder.getUser().getId();
////        Page<QuestionPageQueryVO> page = questionSummaryMapper.queryPage(questionPQDto.getTitle(),userId);
//
//        return new PageResult(page.getTotal(),page.getResult());

        return null;
    }
}
