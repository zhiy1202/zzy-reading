package com.zzy.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.common.State;
import com.zzy.common.Successful;
import com.zzy.entity.Essay;
import com.zzy.entity.Medio;
import com.zzy.mapper.EssayMapper;
import com.zzy.mapper.MedioMapper;
import com.zzy.service.EssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@CrossOrigin
@RestController
public class EssayController {
    @Autowired
    private EssayService essayService;
    @Autowired
    private EssayMapper essayMapper;
    @Autowired
    private MedioMapper medioMapper;

    @GetMapping("/getlist/{lable}")
    public Successful<List<Essay>> getAllEssay(@PathVariable String lable){
        QueryWrapper<Essay> wrapper = new QueryWrapper<>();
        wrapper.eq("label",lable);
        List<Essay> listEssay = essayService.getListEssay(wrapper);
        Successful<List<Essay>> successful = new Successful<>();
        successful.setState(State.SUCCEED);
        successful.setMessage("请求成功");
        successful.setData(listEssay);
        return successful;
    }

    @GetMapping("/getessay/{id}")
    public Successful<Essay> getEssay(@PathVariable Integer id){
        System.out.println(id);

        Successful<Essay> successful = new Successful<>();
        Essay essay = essayService.queryEssay(id);
        if (essay != null){
            successful.setState(State.SUCCEED);
            successful.setMessage("查询成功");
            successful.setData(essay);
        }else {
            successful.setState(State.ERROR);
            successful.setMessage("查询失败");
        }
        return successful;
    }

    @PostMapping("/admin/insertessay")
    public Successful<Essay> insertEssay(Essay essay){
        Successful<Essay> successful = new Successful<>();
        System.out.println(essay);
        essay.setHId(0);
        essay.setMId(0);
        successful.setData(essay);
        successful.setMessage("添加文章+"+essay.getTitle()+"+成功");
        successful.setState(State.SUCCEED);
        int insert = essayMapper.insert(essay);
        return successful;
    }

    @GetMapping("/admin/getAllEssays")
    public Successful<List<Essay>> getAllEssays(){
        Successful<List<Essay>> successful = new Successful<>();

        List<Essay> essays = essayMapper.selectList(null);
        successful.setState(State.SUCCEED);
        successful.setMessage("请求成功");
        successful.setData(essays);

        return successful;
    }
    @DeleteMapping("/admin/deleteessay/{eid}")
    public Successful<String> deleteEssay(@PathVariable Integer eid){
        System.out.println(eid);
        Successful<String> successful = new Successful<>();
        essayMapper.deleteById(eid);
        successful.setState(State.SUCCEED);
        successful.setMessage("删除成功");
        successful.setData("删除成功");
        return successful;
    }

    @PutMapping("/admin/updateEssay/{eid}")
    public Successful<Essay> updateEssay(@PathVariable Integer eid,Essay essay){
        System.out.println(eid);
        System.out.println(essay);
        Successful<Essay> successful = new Successful<>();
        QueryWrapper<Essay> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("e_id",eid);
        essayMapper.update(essay,queryWrapper);
        successful.setState(State.SUCCEED);
        successful.setMessage("修改成功");
        successful.setData(essay);
        return successful;
    }

    @GetMapping("/search/{word}")
    public Successful<List<Essay>> getSearchByWord(@PathVariable String word){
        Successful<List<Essay>> successful = new Successful<>();
        System.out.println(word);

        List<Essay> essayByWord = essayMapper.getEssayByWord(word);
        successful.setState(State.SUCCEED);
        successful.setMessage("请求成功");
        successful.setData(essayByWord);
        return successful;
    }
}
