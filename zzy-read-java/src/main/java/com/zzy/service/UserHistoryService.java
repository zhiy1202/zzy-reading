package com.zzy.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.zzy.entity.EssayUserLike;
import com.zzy.entity.UserHistory;

import java.util.List;

public interface UserHistoryService {
    UserHistory queryOneHistory(Wrapper<UserHistory> wrapper);
    Integer saveUserHistery(UserHistory userHistory);
    List<UserHistory> getAllUserHistory(Wrapper<UserHistory> wrapper);

}
