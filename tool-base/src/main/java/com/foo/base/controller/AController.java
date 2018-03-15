package com.foo.base.controller;

import javax.servlet.http.HttpServletRequest;

public interface AController<TRequest, TResponse> {

    TResponse doStatics(TRequest request);

    TResponse doBatchImport(TRequest request);

    void doBatchExport(TRequest request, HttpServletRequest httpServletRequest);

}
