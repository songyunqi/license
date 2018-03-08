package com.foo.base.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ARequest {

    private Integer pageSize = 10;
    private Integer pageNum = 1;
}
