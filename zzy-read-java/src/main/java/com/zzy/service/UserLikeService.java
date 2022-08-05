package com.zzy.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.zzy.common.Successful;
import com.zzy.entity.UserLike;

import java.util.List;

public interface UserLikeService {
    Integer saveUserLike(UserLike userLike);
    UserLike selectOneUserLike(Wrapper<UserLike> wrapper);
    List<UserLike> selectAllUserLikes(Wrapper<UserLike> wrapper);
}
