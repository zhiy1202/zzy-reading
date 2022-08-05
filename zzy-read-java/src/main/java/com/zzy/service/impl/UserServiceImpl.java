package com.zzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.zzy.entity.User;
import com.zzy.mapper.UserMapper;
import com.zzy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public User queryUser(Wrapper<User> wrapper) {
        return userMapper.selectOne(wrapper);
    }

    @Override
    public Integer saveUser(User user) {
        return userMapper.insert(user);
    }
}
