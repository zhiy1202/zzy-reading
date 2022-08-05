package com.zzy.compoent;

import com.zzy.common.State;
import com.zzy.common.ZZYUtils;
import com.zzy.entity.User;
import com.zzy.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //忽略预检请求
        if (State.OPTIONS.equalsIgnoreCase(request.getMethod())){
            return true;
        }
        String uid = ZZYUtils.getCookiesValue(request, "uid");
        String username = ZZYUtils.getCookiesValue(request, "user");
        String admin = ZZYUtils.getCookiesValue(request, "admin");
        if (uid != null && username != null && !"".equals(uid) && !"".equals(username) || admin != null && !"".equals(admin)){
            return true;
        }else {
            System.out.println("user未登录");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
