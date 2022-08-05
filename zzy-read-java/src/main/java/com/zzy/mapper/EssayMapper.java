package com.zzy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzy.entity.Essay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface EssayMapper extends BaseMapper<Essay> {
    @Update("update essay set m_id = 1 where e_id = #{eid}")
    Integer updateEssaymHas(int eid);

    @Update("update essay set m_id = 0 where e_id = #{eid}")
    Integer updateEssayIdNot(int eid);

    @Update("update essay set h_id = #{hid}")
    Integer updateEssayHId(int hid);

    @Update("update essay set is_read = #{arg0} where e_id = #{arg1}")
    Integer updateEssayIsRead(Integer isRead , Integer essayId);

    @Select("SELECT * FROM essay where is_del = 0 and title like CONCAT('%',#{word},'%' ) limit 5")
    List<Essay> getEssayByWord(String word);
}
