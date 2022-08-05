package com.zzy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medio {
    private int mId;
    private int eId;
    private String medioPath;
    private Date createTime;

    public Medio(int eId, String medioPath, Date createTime) {
        this.eId = eId;
        this.medioPath = medioPath;
        this.createTime = createTime;
    }
}
