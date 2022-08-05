package com.zzy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.common.State;
import com.zzy.common.Successful;
import com.zzy.entity.UserLock;
import com.zzy.mapper.UserLockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class UserLockController {
    @Autowired
    private UserLockMapper userLockMapper;
    @PostMapping("/userlock")
    public Successful<UserLock> addLock(@CookieValue("uid") Integer uid, String lockTime){
        Successful<UserLock> successful = new Successful<>();
        QueryWrapper<UserLock> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("u_id",uid);
        queryWrapper.eq("lock_time",lockTime);
        UserLock userLock = userLockMapper.selectOne(queryWrapper);
        if (userLock == null){
            UserLock lock = new UserLock(uid,lockTime);
            userLockMapper.insert(lock);
            successful.setState(State.SUCCEED);
            successful.setMessage("今日打卡成功");
            successful.setData(lock);
        }else{
            successful.setState(State.ERROR);
            successful.setMessage("今日以打卡");
        }

        return successful;
    }
    @GetMapping("/getAllUserLock")
    public Successful<List<UserLock>> getAllUserLock(@CookieValue("uid") Integer uid){
        Successful<List<UserLock>> successful = new Successful<>();
        QueryWrapper<UserLock> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("u_id",uid);
        List<UserLock> userLocks = userLockMapper.selectList(queryWrapper);

        if (userLocks.size() != 0){
            successful.setState(State.SUCCEED);
            successful.setMessage("查询成功");
            successful.setData(userLocks);
        }else {
            successful.setState(State.SUCCEED);
            successful.setMessage("无打卡记录");
        }
        return successful;
    }
}
