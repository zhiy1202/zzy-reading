package com.zzy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzy.entity.Homework;
import com.zzy.entity.Params;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface HomeWorkMapper extends BaseMapper<Homework> {

    @Update("update homework set is_true='1'  where h_id=#{hid}")
    Integer updateHomeworkIsTrue(Integer hid);

    @Update("update homework set is_true='0'  where h_id=#{hid}")
    Integer updateHomeworkIsError(Integer hid);

    @Update("update homework set all_count=#{allCount} , error_count=#{errorCount} where e_id = #{eId}")
    Integer updateHomewordEnd(int allCount ,int errorCount,int eId);

    @Update("update homework set ${arg1} = #{arg2} where h_id = #{arg0}")
    public Integer updateOption( Integer hid, String option, String value);
}
