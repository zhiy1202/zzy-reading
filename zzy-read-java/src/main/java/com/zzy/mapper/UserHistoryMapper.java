package com.zzy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzy.entity.UserHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

@Mapper
public interface UserHistoryMapper extends BaseMapper<UserHistory> {

    @Update("update user_history set create_time = #{arg0} where u_id = #{arg1} and e_id = #{arg2}")
    public Integer updateHistoryTime(Date newTime, Integer uid, Integer eid);
}
