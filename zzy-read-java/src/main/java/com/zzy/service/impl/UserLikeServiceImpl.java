package com.zzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.zzy.entity.User;
import com.zzy.entity.UserLike;
import com.zzy.mapper.UserLikeMapper;
import com.zzy.service.UserLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLikeServiceImpl implements UserLikeService {

    @Autowired
    private UserLikeMapper userLikeMapper;
    @Override
    public Integer saveUserLike(UserLike userLike) {
        return userLikeMapper.insert(userLike);
    }

    @Override
    public UserLike selectOneUserLike(Wrapper<UserLike> wrapper) {
        return userLikeMapper.selectOne(wrapper);
    }
    @Override
    public List<UserLike> selectAllUserLikes(Wrapper<UserLike> wrapper){
        return userLikeMapper.selectList(wrapper);
    }
}
