package com.zzy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzy.entity.Homework;
import com.zzy.entity.HomeworkResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HomeworkResultMapper  extends BaseMapper<HomeworkResult> {

    @Select("select * from homework order by error_count desc")
    List<Homework> getErrors();


}
