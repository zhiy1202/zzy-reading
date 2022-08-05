package com.zzy.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.common.State;
import com.zzy.common.Successful;
import com.zzy.entity.Essay;
import com.zzy.entity.Homework;
import com.zzy.entity.HomeworkResult;
import com.zzy.entity.Params;
import com.zzy.mapper.EssayMapper;
import com.zzy.mapper.HomeWorkMapper;
import com.zzy.mapper.HomeworkResultMapper;
import com.zzy.service.HomeWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class HomeworkResultController {

    @Autowired
    private HomeworkResultMapper resultMapper;
    @Autowired
    private HomeWorkService homeWorkService;
    @Autowired
    private HomeWorkMapper homeWorkMapper;
    @Autowired
    private EssayMapper essayMapper;

    @PutMapping("/result/{eid}")
    public Successful<HomeworkResult> result(@PathVariable Integer eid ,@CookieValue("uid") String uid){

        HomeworkResult result = null;
        Successful<HomeworkResult> successful = new Successful<>();
        // 查看是否有这条数据 没有插入 有修改
        QueryWrapper<HomeworkResult> wrapper = new QueryWrapper<>();
        wrapper.eq("e_id",eid);
        wrapper.eq("u_id",new Integer(uid));
        HomeworkResult isHasData = resultMapper.selectOne(wrapper);

        if (isHasData == null){
            //查出文章所有的题
            QueryWrapper<Homework> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("e_id",eid);
            List<Homework> homework = homeWorkService.queryAllHomeWorkById(queryWrapper);
            int allCount = homework.size();
            //查出错误的题
            QueryWrapper<Homework> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("e_id",eid);
            queryWrapper1.eq("is_true",false);
            List<Homework> homework1 = homeWorkService.queryAllHomeWorkById(queryWrapper1);
            int errCount = homework1.size();
            // 每题的分数
            int i = 100 / allCount;
            // 用户在 id 为 eid的文章的得分
            double y = i*(allCount-errCount);
            // 生成结果集
            result = new HomeworkResult(eid,new Integer(uid),false,allCount,errCount,y);

            //查出homework表中的原数据
            for (Homework h : homework){
                //修改homework allcount errcount数据
                homeWorkService.updateHomeworkCount(h.getHId(),1,1);
            }
            // 插入数据库
            int insert = resultMapper.insert(result);
            if (insert != -1){
                //修改essay 的hid值 为eid
                essayMapper.updateEssayHId(0);
                successful.setState(State.SUCCEED);
                successful.setMessage("上传成功");
                successful.setData(result);
            }
        }else { // 有这条数据表示已经提交过 直接返回false
            successful.setState(State.ERROR);
            successful.setMessage("题目以做");
        }
        return successful;
    }

    @GetMapping("/result/{eid}")
    public Successful<HomeworkResult> getHomeResult(@PathVariable Integer eid,@CookieValue("uid") String uid){

        Successful<HomeworkResult> successful = new Successful<>();
        QueryWrapper<HomeworkResult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("e_id",eid);
        queryWrapper.eq("u_id",new Integer(uid));
        HomeworkResult result = resultMapper.selectOne(queryWrapper);
        if (result == null){
            successful.setState(State.SUCCEED);
            successful.setMessage("成功");
        }else {
            successful.setState(State.ERROR);
            successful.setMessage("题目以做");
        }
        return successful;
    }

    @GetMapping("/selectgrades/{uid}")
    public Successful<List<Params>> selectGrades(@PathVariable Integer uid){
        Successful<List<Params>> successful = new Successful<>();
        List<Params> list = new ArrayList<>();

        QueryWrapper<HomeworkResult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("u_id",uid);
        List<HomeworkResult> homeworkResults = resultMapper.selectList(queryWrapper);

        for (HomeworkResult result : homeworkResults){
            Essay essay = essayMapper.selectById(result.getEId());
            Params p = new Params();
            p.setP1(new String(essay.getTitle()));
            p.setP2(new String(essay.getAuthor()));
            p.setP3(new Integer(result.getAllCount()));
            p.setP4(new Double(result.getResult()));
            list.add(p);
        }
        successful.setState(State.SUCCEED);
        successful.setMessage("请求成功");
        successful.setData(list);
        return successful;
    }

    @GetMapping("/myerrors")
    public Successful<List<Homework>> getMyErrors(){
        Successful<List<Homework>> successful = new Successful<>();
        List<Homework> errors = resultMapper.getErrors();
        successful.setData(errors);
        successful.setState(State.SUCCEED);
        successful.setMessage("请求成功");
        return successful;

    }
}
