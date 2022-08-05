package com.zzy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EssayUserLike {
    private int eId;
    private String author;
    private String title;
    private String intro;
    private String label;

    private Integer lId;
    private Integer uId;
    private Date createTime;
}
