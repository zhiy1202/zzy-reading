package com.zzy.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.zzy.entity.Essay;

import java.util.List;

public interface EssayService {
    List<Essay> getListEssay(Wrapper<Essay> wrapper);
    Essay queryEssay(Integer id);

}
