package com.zzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.zzy.entity.Homework;
import com.zzy.entity.Params;
import com.zzy.mapper.HomeWorkMapper;
import com.zzy.service.HomeWorkService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HomeWorkServiceImpl implements HomeWorkService {

    @Autowired
    HomeWorkMapper homeWorkMapper;
    @Override
    public List<Homework> queryAllHomeWorkById(Wrapper<Homework> wrapper) {
        return homeWorkMapper.selectList(wrapper);

    }

    @Override
    public Homework getHomework(Wrapper<Homework> wrapper) {
        return homeWorkMapper.selectOne(wrapper);
    }

    @Override
    public Integer updateHomeworkIsTrue(Integer hid) {
        return homeWorkMapper.updateHomeworkIsTrue(hid);
    }

    @Override
    public Integer updateHomeworkIsError(Integer hid) {
        return homeWorkMapper.updateHomeworkIsError(hid);
    }

    @Override
    public Integer updateEnd(int allCount ,int errorCount,int eId) {
        return homeWorkMapper.updateHomewordEnd(allCount,errorCount,eId);
    }
    @Override
    public boolean updateHomeworkCount(int hid,int allCount,int errorCount){
        Homework homework = homeWorkMapper.selectById(hid);
        if (!homework.isTrue()){
            homework.setErrorCount(homework.getErrorCount()+errorCount);
        }

        homework.setAllCount(homework.getAllCount()+allCount);


        int i = homeWorkMapper.updateById(homework);
        if (i==-1){
            return false;
        }

        return true;
    }



}
