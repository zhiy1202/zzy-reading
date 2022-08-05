package com.zzy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.common.State;
import com.zzy.common.Successful;
import com.zzy.entity.Homework;
import com.zzy.entity.Params;
import com.zzy.mapper.EssayMapper;
import com.zzy.mapper.HomeWorkMapper;
import com.zzy.service.HomeWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class HomeWorkController {

    @Autowired
    private HomeWorkService homeWorkService;
    @Autowired
    private HomeWorkMapper homeWorkMapper;
    @Autowired
    private EssayMapper essayMapper;
    @GetMapping("/homework/{eid}")
    public Successful<List<Homework>> getHomeWork(@PathVariable Integer eid){
        Successful<List<Homework>> successful = new Successful<>();
        QueryWrapper<Homework> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("e_id",eid);
        List<Homework> homeWork = homeWorkService.queryAllHomeWorkById(queryWrapper);
        if (homeWork.size() != 0){
            successful.setState(State.SUCCEED);
            successful.setMessage("查找成功");
            successful.setData(homeWork);
        }else {
            successful.setState(State.NOTFOUND);
            successful.setMessage("不存在该文章题目");
        }
        return successful;

    }

    @GetMapping("/homework/{hid}/{radio}")
    public Successful<Homework> getAnswer(@PathVariable Integer hid , @PathVariable String radio){
        Successful<Homework> successful = new Successful<>();
        QueryWrapper<Homework> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("h_id",hid);
        Homework homework = homeWorkService.getHomework(queryWrapper);
        if (homework.getAnswer().equals(radio)){
            //修改isture 为true
            homeWorkService.updateHomeworkIsTrue(hid);
        }else {
            //修改istrue 为false
            homeWorkService.updateHomeworkIsError(hid);
        }
        Homework homework1 = homeWorkService.getHomework(queryWrapper);
        successful.setData(homework1);
        successful.setMessage("修改成功");
        successful.setState(State.SUCCEED);
        return successful;
    }

    @PostMapping("/admin/inserthomework")
    public Successful<Homework> insertHomework(Homework homework){
        Successful<Homework> successful = new Successful<>();
        System.out.println(homework);
        homework.setUpdata(false);
        homeWorkMapper.insert(homework);
        //插入文章 说明有题目 前端展示答题按钮
        essayMapper.updateEssayHId(homework.getEId());
        successful.setState(State.SUCCEED);
        successful.setMessage("添加题目成功");
        successful.setData(homework);
        return successful;
    }
    @GetMapping("/admin/getAllTopic")
    public Successful<List<Homework>> getAllTopic(){
        Successful<List<Homework>> successful = new Successful<>();
        List<Homework> homework = homeWorkMapper.selectList(null);
        successful.setState(State.SUCCEED);
        successful.setMessage("查找题目成功");
        successful.setData(homework);
        return successful;
    }
    @DeleteMapping("/admin/deletetopic/{hid}")
    public Successful<String> delTopic(@PathVariable Integer hid){
        Successful<String> successful = new Successful<>();
        homeWorkMapper.deleteById(hid);
        successful.setState(State.SUCCEED);
        successful.setMessage("删除题目成功");
        successful.setData("");
        return successful;
    }

    @PutMapping("/admin/updateOption/{hid}/{option}/{value}")
    public Successful<String> updateOption(@PathVariable Integer hid,@PathVariable String option,@PathVariable String value){
        Successful<String> successful =new Successful<>();
        System.out.println(hid);
        System.out.println(option);
        System.out.println(value);

        homeWorkMapper.updateOption(hid,option,value);
        return successful;
    }

}
