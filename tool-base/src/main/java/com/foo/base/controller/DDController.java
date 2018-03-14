package com.foo.base.controller;

import com.foo.base.request.ARequest;
import com.foo.base.response.AResponse;

public interface DDController<TRequest extends ARequest, TResponse extends AResponse> {

    TResponse doSave(TRequest t);

    TResponse doDelete(TRequest ids);

    TResponse doUpdate(TRequest list);

    TResponse doList(TRequest request);

    TResponse doPage(TRequest request);

}
