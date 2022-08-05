package com.zzy.controller;

import com.zzy.common.State;
import com.zzy.common.Successful;
import com.zzy.common.ZZYUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AdminController {

    @PostMapping("/admin/login")
    public Successful<Boolean> adminLogin(String username , String password, HttpServletResponse res){
        System.out.println(username+"=="+password);
        Successful<Boolean> successful = new Successful<>();
        if ("admin".equals(username) && "admin".equals(password)){
            successful.setState(State.SUCCEED);
            successful.setMessage("管理员登录成功");
            successful.setData(true);
            res.addCookie(ZZYUtils.addCookies("admin","admin"));
        }else {
            successful.setState(State.ERROR);
            successful.setMessage("用户名或密码错误");
            successful.setData(false);
        }
        return successful;
    }
}
