package com.xiaohai.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaohai.mapper.QuestionSubmitMapper;
import com.xiaohai.model.dto.RankDTO;
import com.xiaohai.model.vo.RankVO;
import com.xiaohai.utils.PageResult;
import com.xiaohai.utils.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//通过题目总量排行榜接口
@Slf4j
@RestController
@RequestMapping("/rank")
@ApiOperation("排行榜接口")
public class RankController {

    @Autowired
    private QuestionSubmitMapper questionSubmitMapper;

    //分页查询--手动分页
    @ApiOperation("分页查询排行榜")
    @GetMapping("/page")
    public Result<PageResult> page(RankDTO rankDTO){
        log.info("---排行榜接口正在执行---,{}",rankDTO);
        //因接口太简单我就不分Service层了
        int pageSize = rankDTO.getPageSize();
        int offset = (rankDTO.getPage()-1)* rankDTO.getPageSize();

//        Page<RankVO> page =  questionSubmitMapper.queryRankPage();
        List<RankVO> list = questionSubmitMapper.queryRankPage(pageSize,offset);
        PageResult pageResult = new PageResult(list.size(),list);
        return Result.success(pageResult);
    }
}
