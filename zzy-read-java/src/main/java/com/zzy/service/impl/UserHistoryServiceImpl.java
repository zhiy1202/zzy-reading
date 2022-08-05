package com.zzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.zzy.entity.EssayUserLike;
import com.zzy.entity.UserHistory;
import com.zzy.mapper.UserHistoryMapper;
import com.zzy.service.UserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserHistoryServiceImpl implements UserHistoryService {

    @Autowired
    private UserHistoryMapper historyMapper;
    @Override
    public UserHistory queryOneHistory(Wrapper<UserHistory> wrapper){
        return historyMapper.selectOne(wrapper);
    }

    @Override
    public Integer saveUserHistery(UserHistory userHistory) {
        return historyMapper.insert(userHistory);
    }

    @Override
    public List<UserHistory> getAllUserHistory(Wrapper<UserHistory> wrapper) {
        return historyMapper.selectList(wrapper);
    }
}
