package com.zzy.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.common.State;
import com.zzy.common.Successful;
import com.zzy.common.ZZYUtils;
import com.zzy.entity.Essay;
import com.zzy.entity.EssayUserLike;
import com.zzy.entity.UserLike;
import com.zzy.mapper.EssayMapper;
import com.zzy.mapper.UserLikeMapper;
import com.zzy.service.EssayService;
import com.zzy.service.UserLikeService;
import com.zzy.service.impl.EssayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class UserLikeController {

    @Autowired
    private UserLikeService userLikeService;
    @Autowired
    private EssayService essayService;
    @Autowired
    private EssayMapper essayMapper;
    @Autowired
    private UserLikeMapper userLikeMapper;
    @PostMapping("/setlike/{eid}")
    public Successful<Boolean> setLike(@PathVariable Integer eid,@CookieValue("uid") String UId){
        Successful<Boolean> successful = new Successful<>();
        System.out.println("用户Id"+UId);

        //查找文章id 文章是否存在
        Essay essay = essayService.queryEssay(eid);
        // 查找用户收藏列表 是否收藏
        QueryWrapper<UserLike> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("u_id",ZZYUtils.getEId(UId));
        queryWrapper.eq("e_id",eid);
        UserLike userLike1 = userLikeService.selectOneUserLike(queryWrapper);

        if (essay!=null && userLike1 == null ){
            UserLike userLike = new UserLike();
            userLike.setEId(eid);
            userLike.setUId(ZZYUtils.getEId(UId));
            userLike.setCreateTime(new Date());

            userLikeService.saveUserLike(userLike);
            successful.setState(State.SUCCEED);
            successful.setMessage("收藏成功");
            successful.setData(false);
        }else {
            successful.setState(State.ERROR);
            successful.setMessage("收藏失败 已被收藏");
            successful.setData(true);
        }

        return successful;
    }

    @GetMapping("/getuserlikes/{uid}")
    public Successful<List<EssayUserLike>> getUserLikes(@PathVariable String uid){
        System.out.println(uid);
        Successful<List<EssayUserLike>> successful = new Successful<>();
        QueryWrapper<UserLike> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("u_id",new Integer(uid));
        queryWrapper.orderByDesc("create_time");
        List<UserLike> userLikes = userLikeService.selectAllUserLikes(queryWrapper);
        List<EssayUserLike> essayUserLikes = new ArrayList<>();
        for (UserLike userLike :userLikes){
            QueryWrapper<Essay> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("e_id",userLike.getEId());
            Essay essay = essayMapper.selectOne(queryWrapper1);
            System.out.println(essay);
            EssayUserLike essayUserLike = new EssayUserLike();
            essayUserLike.setCreateTime(userLike.getCreateTime());
            essayUserLike.setTitle(essay.getTitle());
            essayUserLike.setAuthor(essay.getAuthor());
            essayUserLike.setIntro(essay.getIntro());
            essayUserLike.setEId(essay.getEId());
            essayUserLikes.add(essayUserLike);
        }

        if (userLikes.size() != 0){
            successful.setData(essayUserLikes);
            successful.setState(State.SUCCEED);
            successful.setMessage("查找成功");
        }else {
            successful.setState(State.ERROR);
            successful.setMessage("该用户没有收藏");
        }

        return successful;
    }

    @DeleteMapping("/delUserLike/{eid}")
    public Successful<String> delUserLike(@PathVariable Integer eid,@CookieValue("uid") Integer uid){
        Successful<String> successful = new Successful<>();
        QueryWrapper<UserLike> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("e_id",eid);
        queryWrapper.eq("u_id",uid);
        userLikeMapper.delete(queryWrapper);
        successful.setState(State.SUCCEED);
        successful.setMessage("取消收藏成功");
        successful.setData("删除成功");
        return successful;
    }

}
