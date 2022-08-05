package com.zzy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Essay {
    @TableId
    private int eId;
    private int hId;
    private String author;
    private String title;
    private String intro;
    private String content;
    private String label;
    private int isRead;
    private int mId;
    @TableLogic
    private boolean isDel;
}
