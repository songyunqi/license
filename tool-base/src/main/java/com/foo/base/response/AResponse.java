package com.foo.base.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AResponse<T> {

    private T content;
    private int status = 0;
    private String msg = "0";
}
