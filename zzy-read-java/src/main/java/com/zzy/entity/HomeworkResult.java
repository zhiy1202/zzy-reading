package com.zzy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkResult {
    private int hrId;
    private Integer eId;
    private Integer uId;
    private boolean isUpdate;
    private int allCount;
    private int errCount;
    private double result;

    public HomeworkResult(Integer eId, Integer uId, boolean isUpdate, int allCount, int errCount, double result) {
        this.eId = eId;
        this.uId = uId;
        this.isUpdate = isUpdate;
        this.allCount = allCount;
        this.errCount = errCount;
        this.result = result;
    }
}
