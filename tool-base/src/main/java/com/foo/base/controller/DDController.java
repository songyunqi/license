package com.foo.base.controller;

public interface DDController<T, ID, TRequest, TResponse> {

    TResponse doSave(TRequest t);

    TResponse doDelete(TRequest ids);

    TResponse doUpdate(TRequest list);

    TResponse doList(TRequest request);

    TResponse doPage(TRequest request);

}
