package com.zzy.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.zzy.entity.Essay;
import com.zzy.entity.User;

public interface UserService {
    User queryUser(Wrapper<User> wrapper);
    Integer saveUser(User user);

}
