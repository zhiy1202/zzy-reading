package com.zzy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Homework {
    @TableId
    private int hId;
    private int eId;
    private String topic;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
    private boolean isTrue;
    private boolean isUpdata;
    private int allCount;
    private int errorCount;
}
