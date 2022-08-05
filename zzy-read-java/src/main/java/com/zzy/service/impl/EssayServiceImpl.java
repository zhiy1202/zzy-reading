package com.zzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.zzy.common.Successful;
import com.zzy.entity.Essay;
import com.zzy.mapper.EssayMapper;
import com.zzy.service.EssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
@Service
public class EssayServiceImpl implements EssayService {
    @Autowired
    private EssayMapper essayMapper;
    @Override
    public List<Essay> getListEssay(Wrapper<Essay> wrapper) {
        return essayMapper.selectList(wrapper);
    }

    @Override
    public Essay queryEssay(Integer id) {
        return essayMapper.selectById(id);
    }


}
