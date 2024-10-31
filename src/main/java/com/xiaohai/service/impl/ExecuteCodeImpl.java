package com.xiaohai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaohai.mapper.QuestionCaseMapper;
import com.xiaohai.mapper.QuestionSubmitMapper;
import com.xiaohai.model.dto.ExecuteCodeDTO;
import com.xiaohai.model.dto.ExecuteCodeRequest;
import com.xiaohai.model.dto.ExecuteCodeResponse;
import com.xiaohai.model.po.Question;
import com.xiaohai.model.po.QuestionCase;
import com.xiaohai.model.po.QuestionSubmit;
import com.xiaohai.service.ExecuteCodeService;
import com.xiaohai.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ExecuteCodeImpl implements ExecuteCodeService {
    @Autowired
    private QuestionCaseMapper questionCaseMapper;
    @Autowired
    private JavaNativeCodeSandbox javaNativeCodeSandbox;
    @Autowired
    private QuestionSubmitMapper questionSubmitMapper;

    //执行用户提交的代码
    @Override
    public Result executeCode(ExecuteCodeDTO executeCodeDTO) {
        if(StringUtils.isBlank(executeCodeDTO.getCode())){
            return Result.fail("代码不对！");
        }
        //1.先查询数据库中所有输入输出用例
        List<QuestionCase> questionCases = questionCaseMapper.selectByQuestionNum(executeCodeDTO.getQuestionNumber());
        //对每一个用例都进行代码执行
        ExecuteCodeResponse lastExecuteCodeResponse =null;//取某一次的执行情况即可
        try{
            for(QuestionCase questionCase : questionCases){
                //todo修改这个inputList类型为String
                List<String> inputList = new ArrayList<>();
                inputList.add(questionCase.getInputList()) ;
                ExecuteCodeRequest executeCodeRequest = new ExecuteCodeRequest();
                executeCodeRequest.setInputList(inputList);
                executeCodeRequest.setCode(executeCodeDTO.getCode());
                executeCodeRequest.setLanguage("java");
                ExecuteCodeResponse executeCodeResponse = javaNativeCodeSandbox.executeCode(executeCodeRequest);
                String codeOutPut = executeCodeResponse.getOutputList().get(0);
                codeOutPut = removeTrailingSpace(codeOutPut);
                lastExecuteCodeResponse = executeCodeResponse;
                if(!questionCase.getOutputList().equals(codeOutPut)){
                    log.info("解答错误,{}",executeCodeResponse);
                    executeCodeResponse.setStatus(0);
                    insertQuestionSubmit(executeCodeDTO,lastExecuteCodeResponse);
                    return Result.success(executeCodeResponse);//你这个执行成功了，但是解答错误
                }
            }
        }catch (Exception e){
            //如果发生编译错误或者运行错误就会走到这里，返回一个错误信息
            lastExecuteCodeResponse=new ExecuteCodeResponse();
            lastExecuteCodeResponse.setMessage(e.getMessage());
            lastExecuteCodeResponse.setStatus(3);
            log.info("运行出错或者编译错误");
            insertQuestionSubmit(executeCodeDTO,lastExecuteCodeResponse);
            return Result.success(lastExecuteCodeResponse);
        }
        if(lastExecuteCodeResponse.getJudgeInfo().getTime()>1000){
            //超时
            lastExecuteCodeResponse.setStatus(2);
            insertQuestionSubmit(executeCodeDTO,lastExecuteCodeResponse);
            return Result.success(lastExecuteCodeResponse);
        }
        //todo:无论运行成功还是失败，根据这个返回的执行信息，插入一条提交记录
        insertQuestionSubmit(executeCodeDTO, lastExecuteCodeResponse);


        log.info("解答正确,{}",lastExecuteCodeResponse);
        return Result.success(lastExecuteCodeResponse);
    }

//    封装插入方法
    private void insertQuestionSubmit(ExecuteCodeDTO executeCodeDTO, ExecuteCodeResponse lastExecuteCodeResponse) {
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setQuestionNumber(executeCodeDTO.getQuestionNumber());
        questionSubmit.setResult(lastExecuteCodeResponse.getStatus());
        questionSubmit.setCode(executeCodeDTO.getCode());
        questionSubmit.setCreateTime(new Date());
        questionSubmit.setRunMemory(100);
        questionSubmit.setUserId(1);//todo:假数据，之后需要联合登录
        questionSubmitMapper.insert(questionSubmit);
    }

    public static String removeTrailingSpace(String str) {
        int end = str.length() - 1;
        while (end >= 0 && str.charAt(end) == ' ') {
            end--;
        }
        return str.substring(0, end + 1);
    }
}
