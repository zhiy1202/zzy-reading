package com.zzy.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzy.common.State;
import com.zzy.common.Successful;
import com.zzy.entity.Essay;
import com.zzy.entity.EssayUserLike;
import com.zzy.entity.UserHistory;
import com.zzy.entity.UserLike;
import com.zzy.mapper.EssayMapper;
import com.zzy.mapper.UserHistoryMapper;
import com.zzy.service.EssayService;
import com.zzy.service.UserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class UserHistoryController {

    @Autowired
    private EssayService essayService;
    @Autowired
    private UserHistoryMapper userHistoryMapper;
    @Autowired
    private UserHistoryService userHistoryService;
    @Autowired
    private EssayMapper essayMapper;
    @PostMapping("/adduserhistory/{essayId}")
    public Successful<Integer> successful(@PathVariable Integer essayId, @CookieValue("uid") String UId){
        Successful<Integer> successful = new Successful<>();
        Essay essay = essayService.queryEssay(essayId);
        QueryWrapper<UserHistory> wrapper = new QueryWrapper<>();
        wrapper.eq("u_id",new Integer(UId));
        wrapper.eq("e_id",essayId);
        UserHistory userHistory = userHistoryService.queryOneHistory(wrapper);

        if (essay != null && userHistory == null){
            UserHistory userHistory1 = new UserHistory();
            userHistory1.setCreateTime(new Date());
            userHistory1.setUId(new Integer(UId));
            userHistory1.setEId(essayId);

            userHistoryService.saveUserHistery(userHistory1);
            Essay essay1 = essayMapper.selectById(essayId);
            essayMapper.updateEssayIsRead(essay1.getIsRead()+1,essayId);

            successful.setMessage("插入浏览记录成功");
            successful.setState(State.SUCCEED);
            successful.setData(userHistory1.getHId());
        }else {
            userHistoryMapper.updateHistoryTime(new Date(),new Integer(UId),essayId);
            successful.setMessage("更新浏览记录成功");
            successful.setState(State.ERROR);
        }

        return successful;
    }


    @GetMapping("/getuserhistorys/{uid}")
    public Successful<List<EssayUserLike>> getuserhistorys(@PathVariable Integer uid){
        System.out.println(uid);
        Successful<List<EssayUserLike>> successful = new Successful<>();
        //获取所有的用户id 为 uid的所有记录
        QueryWrapper<UserHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("u_id",uid);
        queryWrapper.orderByDesc("create_time");
        List<UserHistory> userLikes = userHistoryService.getAllUserHistory(queryWrapper);
        //储存记录容器
        List<EssayUserLike> essayUserLikes = new ArrayList<>();
        if (userLikes.size() == 0){
            successful.setState(State.ERROR);
            successful.setMessage("该用户没有记录");
        }else {
            for (UserHistory userLike :userLikes){
                Essay essay = essayService.queryEssay(userLike.getEId());
                EssayUserLike essayUserLike = new EssayUserLike();
                essayUserLike.setCreateTime(userLike.getCreateTime());
                essayUserLike.setTitle(essay.getTitle());
                essayUserLike.setAuthor(essay.getAuthor());
                essayUserLike.setIntro(essay.getIntro());
                essayUserLike.setEId(essay.getEId());
                essayUserLike.setUId(uid);

                essayUserLikes.add(essayUserLike);
            }
            successful.setData(essayUserLikes);
            successful.setState(State.SUCCEED);
            successful.setMessage("查找成功");
        }


        return successful;
    }

    @DeleteMapping("/delHistory/{eid}")
    public Successful<String> delHistory(@CookieValue("uid") Integer uid,@PathVariable Integer eid){
        Successful<String> successful = new Successful<>();
        QueryWrapper<UserHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("e_id",eid);
        queryWrapper.eq("u_id",uid);
        userHistoryMapper.delete(queryWrapper);
        successful.setState(State.SUCCEED);
        successful.setMessage("删除浏览记录成功");
        successful.setData("isOk");
        return successful;
    }
}
