package com.foo.base.user;

import com.foo.base.request.ARequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest extends ARequest {
    private String name;
}
