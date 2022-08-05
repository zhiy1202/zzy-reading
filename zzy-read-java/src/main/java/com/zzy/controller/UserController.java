package com.zzy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.common.State;
import com.zzy.common.Successful;
import com.zzy.common.ZZYUtils;
import com.zzy.entity.Essay;
import com.zzy.entity.User;
import com.zzy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Successful<String> login(String username, String password, HttpServletResponse response){
        Successful<String> successful = new Successful<>();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        wrapper.eq("password",password);
        User user = userService.queryUser(wrapper);
        if (user == null){
            successful.setState(State.ERROR);
            successful.setMessage("用户不存在");
        }else {
            successful.setState(State.SUCCEED);
            successful.setMessage("登录成功");
            successful.setData(user.getUsername());
            response.addCookie(ZZYUtils.addCookies("user", user.getUsername()));
            response.addCookie(ZZYUtils.addCookies("uid", String.valueOf(user.getUId())));
        }
        return successful;
    }
    @PostMapping("/register")
    public Successful<String> register(User u){
        System.out.println(u.getUsername()+"==="+u.getPassword());
        Successful<String> successful = new Successful<>();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",u.getUsername());
        User user = userService.queryUser(wrapper);
        if (user == null){
            userService.saveUser(u);
            successful.setState(State.SUCCEED);
            successful.setMessage("创建用户成功");
            successful.setData(u.getUsername());
        }else {
            successful.setState(State.ERROR);
            successful.setMessage("用户存在 请重试");
            successful.setData(null);
        }

        return successful;
    }

    @GetMapping("/exit")
    public Successful<String> exit(HttpServletRequest req){
        ZZYUtils.exitCookies(req);
        Successful<String> successful = new Successful<>(State.SUCCEED,"请求成功","退出账户成功");
        return  successful;
    }




}
