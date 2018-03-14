package com.foo.base.service;

import com.foo.base.mapper.DDMapper;
import com.foo.base.request.ARequest;

public interface DDService<T, ID, TRequest extends ARequest> extends DDMapper<T, ID, TRequest> {


}
