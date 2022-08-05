package com.zzy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLock {
    private Integer ulId;
    private int uId;
    private String lockTime;

    public UserLock(int uId, String lockTime) {
        this.uId = uId;
        this.lockTime = lockTime;
    }
}
