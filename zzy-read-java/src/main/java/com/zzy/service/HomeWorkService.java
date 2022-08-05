package com.zzy.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.zzy.entity.Homework;
import com.zzy.entity.Params;

import java.util.List;

public interface HomeWorkService {
    List<Homework> queryAllHomeWorkById(Wrapper<Homework> wrapper);
    Homework getHomework(Wrapper<Homework> wrapper);

    Integer updateHomeworkIsTrue(Integer hid);

    Integer updateHomeworkIsError(Integer hid);



    Integer updateEnd(int allCount ,int errorCount,int eId);

    public boolean updateHomeworkCount(int hid,int allCount,int errorCount);
}
