package com.xiaohai.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaohai.mapper.QuestionMapper;
import com.xiaohai.mapper.QuestionSubmitMapper;
import com.xiaohai.mapper.UserMapper;
import com.xiaohai.model.dto.StatusPageQueryDTO;
import com.xiaohai.model.po.Question;
import com.xiaohai.model.po.QuestionSubmit;
import com.xiaohai.model.vo.QuestionDetail;
import com.xiaohai.model.vo.StatusPageQueryVO;
import com.xiaohai.service.StatusPageQueryService;
import com.xiaohai.utils.Constants;
import com.xiaohai.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StatusPageQueryServiceImpl implements StatusPageQueryService {

    @Autowired
    private QuestionSubmitMapper questionSubmitMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;
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


    public PageResult pageQuery2(StatusPageQueryDTO statusPageQueryDTO) {
        //先查询数据库到Redis里。
        String cacheKey = Constants.QUESTION_SUBMIT_KEY;
        int page = statusPageQueryDTO.getPage();
        int pageSize = statusPageQueryDTO.getPageSize();
        int start = (page-1)*pageSize;
        int end = pageSize*page-1;

        Long size = stringRedisTemplate.opsForZSet().size(cacheKey);
        Set<String> ids
                = stringRedisTemplate.opsForZSet().reverseRange(cacheKey, start, end);
        if(ids==null || ids.isEmpty()){
            //缓存预热
            List<Integer> numbers = questionSubmitMapper.queryAllIds();
            for (Integer i : numbers) {
                stringRedisTemplate.opsForZSet().add(cacheKey, String.valueOf(i), i);
                if(ids ==null) {
                    ids = new HashSet<>();
                    ids.add(String.valueOf(i));
                }
            }
        }

        //根据id查询所有题目并封装结果
        List<StatusPageQueryVO> result = getByIds(ids);



        return new PageResult(size, result);
    }

    private List<StatusPageQueryVO> getByIds(Set<String> ids) {
        List<StatusPageQueryVO> result = new ArrayList<StatusPageQueryVO>();
        for(String id : ids){
            Integer number = Integer.valueOf(id);
            StatusPageQueryVO vo = new StatusPageQueryVO();
            //1.查询基本信息
            QuestionSubmit questionSubmit = questionSubmitMapper.queryById(number);
            vo.setCreateTime(questionSubmit.getCreateTime());
            vo.setRunTime(questionSubmit.getRunTime());
            vo.setLength(questionSubmit.getCode().length());
            vo.setResult(questionSubmit.getResult());
            vo.setRunMemory(questionSubmit.getRunMemory());
            vo.setRunRecord(Integer.parseInt(id));
            //查询联合信息

            vo.setUsername(userMapper.queryNameById(questionSubmit.getUserId()));
            Question question =  questionMapper.getNumberAndTitleByNumber(questionSubmit.getQuestionNumber());
            StringBuilder sb = new StringBuilder();
            String info =sb.append(question.getNumber()).append("-").append(question.getTitle()).toString();
            vo.setQuestionInfo(info);//题号加标

            result.add(vo);
        }
        return result;
    }


//    //通过新表优化
//    public PageResult pageQuery2(StatusPageQueryDTO statusPageQueryDTO) {
//        //开启分页查询
//        PageHelper.startPage(statusPageQueryDTO.getPage(),statusPageQueryDTO.getPageSize());
//
//        Page<StatusPageQueryVO> page = statusSummaryMapper.pageQuery(statusPageQueryDTO);
//
//        return new PageResult(page.getTotal(),page.getResult());
//    }
}
