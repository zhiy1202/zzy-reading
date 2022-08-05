package com.zzy.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Successful<T> {
    private int state;
    private String message;
    private T data;
}
